package es.unican.appriegospersonales.activities.perfil.tabs;

import java.util.List;

import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class TabDElementsPresenter {
    private final TabDElementsView view;
    private PerfilDao perfilDao;
    private Perfil perfil;

    public TabDElementsPresenter(TabDElementsView view) {
        this.view = view;
    }

    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();
        perfil = Perfil.getInstance(perfilDao);
    }

    public List<ElementoDigital> getAppsAnhadidas() {
        return perfil.getElementosDigitalesAnhadidos();
    }
}
