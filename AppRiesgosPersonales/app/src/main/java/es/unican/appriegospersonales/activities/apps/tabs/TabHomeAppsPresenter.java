package es.unican.appriegospersonales.activities.apps.tabs;

import android.database.sqlite.SQLiteException;

import java.util.Collections;
import java.util.List;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Tipo;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.cibel.rest.CibelServiceConstants;

public class TabHomeAppsPresenter implements ITabHomeAppsContract.Presenter {
    private final ITabHomeAppsContract.View view;
    private PerfilDao perfilDao;
    private CategoriaDao categoriaDao;

    public TabHomeAppsPresenter(ITabHomeAppsContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();
        categoriaDao = daoSession.getCategoriaDao();
    }

    @Override
    public List<Tipo> getCategoriasDeApps() {
        List<Tipo> categoriasDeApps = categoriaDao.queryBuilder().where(CategoriaDao.Properties.Tipo
                .like(CibelServiceConstants.APP)).list();
        if (categoriasDeApps == null) categoriasDeApps = Collections.emptyList();
        return categoriasDeApps;
    }

    @Override
    public List<Activo> getPerfilDElements() {
        List<Activo> result = null;
        try {
            Perfil perfil = Perfil.getInstance(perfilDao);
            result = perfil.getActivosAnhadidos();
        } catch (SQLiteException e) {
        }
        return result;
    }
}
