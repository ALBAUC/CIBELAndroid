package es.unican.appriegospersonales.activities.apps;

import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

import es.unican.appriegospersonales.common.Callback;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.Riesgo;
import es.unican.appriegospersonales.repository.AppWiseRepository;
import es.unican.appriegospersonales.repository.IAppWiseRepository;
import es.unican.appriegospersonales.repository.db.AplicacionDao;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class AppsPresenter implements IAppsContract.Presenter {

    private final IAppsContract.View view;
    private AplicacionDao aplicacionDao;
    private CategoriaDao categoriaDao;
    private PerfilDao perfilDao;
    private IAppWiseRepository repository;

    public AppsPresenter(IAppsContract.View view) {
        this.view = view;
    }

    public AppsPresenter(IAppsContract.View view, IAppWiseRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        aplicacionDao = daoSession.getAplicacionDao();
        categoriaDao = daoSession.getCategoriaDao();
        perfilDao = daoSession.getPerfilDao();

        if (repository == null) {
            repository = new AppWiseRepository(view.getMyApplication());
        }

        doAsyncInit();
    }

    private void doAsyncInit() {
        repository.requestControles(new Callback<Control[]>() {
            @Override
            public void onSuccess(Control[] controles) {
                repository.requestRiesgos(new Callback<Riesgo[]>() {
                    @Override
                    public void onSuccess(Riesgo[] riesgos) {
                        repository.requestCategorias(new Callback<Categoria[]>() {
                            @Override
                            public void onSuccess(Categoria[] categorias) {
                                repository.requestAplicaciones(new Callback<Aplicacion[]>() {
                                    @Override
                                    public void onSuccess(Aplicacion[] aplicaciones) {
                                        view.showLoadCorrect(aplicaciones.length);
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
                repository.getRiesgos() == null ||
                repository.getCategorias() == null ||
                repository.getAplicaciones(null) == null) {
            view.showLoadError();
        } else {
            view.showLoadCorrect((int) aplicacionDao.count());
        }
    }

    @Override
    public List<Categoria> getCategorias() {
        List<Categoria> result = null;
        try {
            result = categoriaDao.loadAll();
        } catch (SQLiteException e) {
            view.showLoadError();
        }
        return result;
    }

    @Override
    public List<Aplicacion> getPerfilApps() {
        List<Aplicacion> result = null;
        try {
            Perfil perfil = Perfil.getInstance(perfilDao);
            result = perfil.getAppsAnhadidas();
        } catch (SQLiteException e) {
            view.showLoadError();
        }
        return result;
    }

    @Override
    public Aplicacion getAppByName(String appName) {
        return aplicacionDao.queryBuilder().where(AplicacionDao.Properties.Nombre.like(appName)).unique();
    }
}
