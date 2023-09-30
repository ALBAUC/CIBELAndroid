package es.unican.appriegospersonales.activities.apps.tabs;

import android.database.sqlite.SQLiteException;

import java.util.Collections;
import java.util.List;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.CibelRepository;
import es.unican.appriegospersonales.repository.ICibelRepository;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class TabHomeDevicesPresenter implements ITabHomeDevicesContract.Presenter {

    private final ITabHomeDevicesContract.View view;
    private PerfilDao perfilDao;
    private ICibelRepository repository;

    public TabHomeDevicesPresenter(ITabHomeDevicesContract.View view) {
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
    public List<Categoria> getCategoriasDeDispositivos() {
        List<Categoria> categoriasDeDispositivos = List.of(repository.getCategoriasDeDispositivos());
        if (categoriasDeDispositivos == null) categoriasDeDispositivos = Collections.emptyList();
        return categoriasDeDispositivos;
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
