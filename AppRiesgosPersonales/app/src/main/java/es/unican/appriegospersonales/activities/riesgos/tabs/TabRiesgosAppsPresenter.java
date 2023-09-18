package es.unican.appriegospersonales.activities.riesgos.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import es.unican.appriegospersonales.common.Callback;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.Riesgo;
import es.unican.appriegospersonales.repository.CibelRepository;
import es.unican.appriegospersonales.repository.ICibelRepository;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.db.RiesgoDao;

public class TabRiesgosAppsPresenter implements ITabRiesgosAppsContract.Presenter {
    private final ITabRiesgosAppsContract.View view;
    private PerfilDao perfilDao;
    private ICibelRepository repository;

    public TabRiesgosAppsPresenter(ITabRiesgosAppsContract.View view) {
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
    public List<Riesgo> getRiesgosApps() {
        List<Riesgo> riesgosDeApps = List.of(repository.getRiesgosDeApps());
        if (riesgosDeApps == null) riesgosDeApps = Collections.emptyList();
        return riesgosDeApps;
    }

    @Override
    public List<Control> getPerfilControls() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        return perfil.getControlesAnhadidos();
    }
}
