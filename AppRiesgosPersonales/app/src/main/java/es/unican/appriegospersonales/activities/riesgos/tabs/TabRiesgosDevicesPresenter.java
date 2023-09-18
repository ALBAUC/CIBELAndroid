package es.unican.appriegospersonales.activities.riesgos.tabs;

import java.util.Collections;
import java.util.List;

import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.Riesgo;
import es.unican.appriegospersonales.repository.CibelRepository;
import es.unican.appriegospersonales.repository.ICibelRepository;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.db.RiesgoDao;

public class TabRiesgosDevicesPresenter implements  ITabRiesgosDevicesContract.Presenter {
    private final ITabRiesgosDevicesContract.View view;
    private PerfilDao perfilDao;
    private ICibelRepository repository;

    public TabRiesgosDevicesPresenter(ITabRiesgosDevicesContract.View view) {
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
    public List<Riesgo> getRiesgosDevices() {
        List<Riesgo> riesgosDeDispositivos = List.of(repository.getRiesgosDeDispositivos());
        if (riesgosDeDispositivos == null) riesgosDeDispositivos = Collections.emptyList();
        return riesgosDeDispositivos;
    }

    @Override
    public List<Control> getPerfilControls() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        return perfil.getControlesAnhadidos();
    }
}
