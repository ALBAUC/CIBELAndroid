package es.unican.appriegospersonales.activities.perfil.tabs;

import java.util.List;

import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class TabControlesPresenter {
    private final TabControlesView view;
    private PerfilDao perfilDao;
    private Perfil perfil;

    public TabControlesPresenter(TabControlesView view) {
        this.view = view;
    }

    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();
        perfil = Perfil.getInstance(perfilDao);
    }

    public List<Control> getControlesAnhadidos() {
        return perfil.getControlesAnhadidos();
    }
}
