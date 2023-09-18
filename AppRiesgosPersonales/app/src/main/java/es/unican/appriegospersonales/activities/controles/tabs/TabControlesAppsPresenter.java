package es.unican.appriegospersonales.activities.controles.tabs;

import java.util.Collections;
import java.util.List;

import es.unican.appriegospersonales.activities.riesgos.tabs.ITabRiesgosAppsContract;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.CibelRepository;
import es.unican.appriegospersonales.repository.ICibelRepository;
import es.unican.appriegospersonales.repository.db.ControlDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class TabControlesAppsPresenter implements ITabControlesAppsContract.Presenter {

    private final ITabControlesAppsContract.View view;
    private PerfilDao perfilDao;
    private ICibelRepository repository;

    public TabControlesAppsPresenter(ITabControlesAppsContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();

        if (repository == null) {
            repository = new CibelRepository(view.getMyApplication());
        }
    }

    @Override
    public List<Control> getControlesApps() {
        List<Control> controlesDeApps = List.of(repository.getControlesDeApps());
        if (controlesDeApps == null) controlesDeApps = Collections.emptyList();
        return controlesDeApps;
    }

    @Override
    public List<Control> getPerfilControls() {
            Perfil perfil = Perfil.getInstance(perfilDao);
            return perfil.getControlesAnhadidos();
    }
}
