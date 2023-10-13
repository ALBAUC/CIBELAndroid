package es.unican.appriegospersonales.activities.apps;

import android.database.sqlite.SQLiteException;

import java.util.List;

import es.unican.appriegospersonales.common.Callback;
import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.Tipo;
import es.unican.appriegospersonales.repository.cibel.CibelRepository;
import es.unican.appriegospersonales.repository.cibel.ICibelRepository;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.db.TipoDao;

public class HomePresenter implements IHomeContract.Presenter {

    private final IHomeContract.View view;
    private ActivoDao activoDao;
    private CategoriaDao categoriaDao;
    private TipoDao tipoDao;
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
        tipoDao = daoSession.getTipoDao();
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
                        repository.requestCategorias(new Callback<Categoria[]>() {
                            @Override
                            public void onSuccess(Categoria[] categorias) {
                                repository.requestTipos(new Callback<Tipo[]>() {
                                    @Override
                                    public void onSuccess(Tipo[] data) {
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
                repository.getTipos() == null||
                repository.getActivos(null) == null) {
            view.showLoadError();
        } else {
            view.showLoadCorrect((int) activoDao.count());
        }
    }

    @Override
    public List<Categoria> getCategorias() {
        return categoriaDao.loadAll();
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

    @Override
    public Activo getAssetByName(String appName) {
        return activoDao.queryBuilder().where(ActivoDao.Properties.Nombre.like(appName)).unique();
    }

    @Override
    public List<Tipo> getTipos() {
        return tipoDao.loadAll();
    }
}
