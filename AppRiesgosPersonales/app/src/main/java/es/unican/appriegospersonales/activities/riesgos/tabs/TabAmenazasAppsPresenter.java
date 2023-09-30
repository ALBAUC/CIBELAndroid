package es.unican.appriegospersonales.activities.riesgos.tabs;

import java.util.Collections;
import java.util.List;

import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.CibelRepository;
import es.unican.appriegospersonales.repository.ICibelRepository;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class TabAmenazasAppsPresenter implements ITabAmenazasAppsContract.Presenter {
    private final ITabAmenazasAppsContract.View view;
    private PerfilDao perfilDao;
    private ICibelRepository repository;

    public TabAmenazasAppsPresenter(ITabAmenazasAppsContract.View view) {
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
    public List<Amenaza> getAmenazasApps() {
        List<Amenaza> amenazasDeApps = List.of(repository.getAmenazasDeApps());
        if (amenazasDeApps == null) amenazasDeApps = Collections.emptyList();
        return amenazasDeApps;
    }

    @Override
    public List<Control> getPerfilControls() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        return perfil.getControlesAnhadidos();
    }
}
