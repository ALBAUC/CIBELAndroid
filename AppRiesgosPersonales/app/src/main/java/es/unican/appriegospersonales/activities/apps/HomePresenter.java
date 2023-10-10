package es.unican.appriegospersonales.activities.apps;

import android.database.sqlite.SQLiteException;

import java.util.List;

import es.unican.appriegospersonales.common.Callback;
import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Tipo;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.cibel.CibelRepository;
import es.unican.appriegospersonales.repository.cibel.ICibelRepository;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class HomePresenter implements IHomeContract.Presenter {

    private final IHomeContract.View view;
    private ActivoDao activoDao;
    private CategoriaDao categoriaDao;
    private PerfilDao perfilDao;
    private ICibelRepository repository;

    public HomePresenter(IHomeContract.View view) {
        this.view = view;
    }

    // Para tests
    public HomePresenter(IHomeContract.View view, ICibelRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        activoDao = daoSession.getActivoDao();
        categoriaDao = daoSession.getCategoriaDao();
        perfilDao = daoSession.getPerfilDao();
        repository = new CibelRepository(view.getMyApplication());

        if (repository.lastDownloadOlderThan(30, CibelRepository.KEY_LAST_SAVED_A)) {
            doSyncInit();
        }
    }

    private void doAsyncInit() {
        repository.requestControles(new Callback<Control[]>() {
            @Override
            public void onSuccess(Control[] controles) {
                repository.requestAmenazas(new Callback<Amenaza[]>() {
                    @Override
                    public void onSuccess(Amenaza[] riesgos) {
                        repository.requestCategorias(new Callback<Tipo[]>() {
                            @Override
                            public void onSuccess(Tipo[] tipos) {
                                repository.requestActivos(new Callback<Activo[]>() {
                                    @Override
                                    public void onSuccess(Activo[] activos) {
                                        view.showLoadCorrect(activos.length);
                                    }

                                    @Override
                                    public void onFailure() {
                                        view.showLoadError();
                                    }
                                }, null);
                            }
                            @Override
                            public void onFailure() {
                                view.showLoadError();
                            }
                        });
                    }

                    @Override
                    public void onFailure() {
                        view.showLoadError();
                    }
                });
            }

            @Override
            public void onFailure() {
                view.showLoadError();
            }
        });
    }

    private void doSyncInit() {
        if (repository.getControles() == null ||
                repository.getAmenazas() == null ||
                repository.getCategorias() == null ||
                repository.getActivos(null) == null) {
            view.showLoadError();
        } else {
            view.showLoadCorrect((int) activoDao.count());
        }
    }

    @Override
    public List<Tipo> getCategorias() {
        List<Tipo> result = null;
        try {
            result = categoriaDao.loadAll();
        } catch (SQLiteException e) {
            view.showLoadError();
        }
        return result;
    }

    @Override
    public List<Activo> getPerfilAssets() {
        List<Activo> result = null;
        try {
            Perfil perfil = Perfil.getInstance(perfilDao);
            result = perfil.getActivosAnhadidos();
        } catch (SQLiteException e) {
            view.showLoadError();
        }
        return result;
    }

    @Override
    public Activo getAssetByName(String appName) {
        return activoDao.queryBuilder().where(ActivoDao.Properties.Nombre.like(appName)).unique();
    }
}
