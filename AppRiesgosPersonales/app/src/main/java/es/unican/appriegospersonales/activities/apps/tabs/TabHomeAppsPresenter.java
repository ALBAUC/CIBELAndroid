package es.unican.appriegospersonales.activities.apps.tabs;

import android.database.sqlite.SQLiteException;

import java.util.Collections;
import java.util.List;

import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.CibelRepository;
import es.unican.appriegospersonales.repository.ICibelRepository;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class TabHomeAppsPresenter implements ITabHomeAppsContract.Presenter {
    private final ITabHomeAppsContract.View view;
    private PerfilDao perfilDao;
    private ICibelRepository repository;

    public TabHomeAppsPresenter(ITabHomeAppsContract.View view) {
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
    public List<Categoria> getCategoriasDeApps() {
        List<Categoria> categoriasDeApps = List.of(repository.getCategoriasDeApps());
        if (categoriasDeApps == null) categoriasDeApps = Collections.emptyList();
        return categoriasDeApps;
    }

    @Override
    public List<ElementoDigital> getPerfilDElements() {
        List<ElementoDigital> result = null;
        try {
            Perfil perfil = Perfil.getInstance(perfilDao);
            result = perfil.getElementosDigitalesAnhadidos();
        } catch (SQLiteException e) {
        }
        return result;
    }
}
