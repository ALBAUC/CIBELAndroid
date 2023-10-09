package es.unican.appriegospersonales.activities.apps.tabs;

import android.database.sqlite.SQLiteException;

import java.util.Collections;
import java.util.List;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.cibel.rest.CibelServiceConstants;

public class TabHomeDevicesPresenter implements ITabHomeDevicesContract.Presenter {

    private final ITabHomeDevicesContract.View view;
    private PerfilDao perfilDao;
    private CategoriaDao categoriaDao;
    private ActivoDao activoDao;

    public TabHomeDevicesPresenter(ITabHomeDevicesContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();
        categoriaDao = daoSession.getCategoriaDao();
        activoDao = daoSession.getActivoDao();
    }

    @Override
    public List<Categoria> getCategoriasDeDispositivos() {
        List<Categoria> categoriasDeDispositivos = categoriaDao.queryBuilder().where(CategoriaDao.Properties.Tipo
                .like(CibelServiceConstants.DISPOSITIVO_IOT)).list();
        if (categoriasDeDispositivos == null) categoriasDeDispositivos = Collections.emptyList();
        return categoriasDeDispositivos;
    }

    @Override
    public List<Activo> getPerfilAssets() {
        List<Activo> result = null;
        try {
            Perfil perfil = Perfil.getInstance(perfilDao);
            result = activoDao._queryPerfil_ActivosAnhadidos(perfil.getId());
        } catch (SQLiteException e) {
        }
        return result;
    }
}
