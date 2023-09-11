package es.unican.appriegospersonales.activities.perfil.tabs;

import java.util.List;

import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class TabAppsPresenter {
    private final TabAppsView view;
    private PerfilDao perfilDao;
    private Perfil perfil;

    public TabAppsPresenter(TabAppsView view) {
        this.view = view;
    }

    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();
        perfil = Perfil.getInstance(perfilDao);
    }

    public List<Aplicacion> getAppsAnhadidas() {
        return perfil.getAppsAnhadidas();
    }
}
