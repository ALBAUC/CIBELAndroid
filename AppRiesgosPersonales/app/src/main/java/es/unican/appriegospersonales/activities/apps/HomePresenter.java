package es.unican.appriegospersonales.activities.apps;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.List;

import es.unican.appriegospersonales.common.Callback;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.Riesgo;
import es.unican.appriegospersonales.repository.CibelRepository;
import es.unican.appriegospersonales.repository.ICibelRepository;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.ElementoDigitalDao;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class HomePresenter implements IHomeContract.Presenter {

    private final IHomeContract.View view;
    private ElementoDigitalDao elementoDigitalDao;
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
        elementoDigitalDao = daoSession.getElementoDigitalDao();
        categoriaDao = daoSession.getCategoriaDao();
        perfilDao = daoSession.getPerfilDao();

        if (repository == null) {
            repository = new CibelRepository(view.getMyApplication());
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
                                repository.requestElementosDigitales(new Callback<ElementoDigital[]>() {
                                    @Override
                                    public void onSuccess(ElementoDigital[] elementosDigitales) {
                                        view.showLoadCorrect(elementosDigitales.length);
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
                Log.d(ContentValues.TAG, "Falla aqui");
                view.showLoadError();
            }
        });
    }

    private void doSyncInit() {
        if (repository.getControles() == null ||
                repository.getRiesgos() == null ||
                repository.getCategorias() == null ||
                repository.getElementosDigitales(null) == null) {
            view.showLoadError();
        } else {
            view.showLoadCorrect((int) elementoDigitalDao.count());
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
    public List<ElementoDigital> getPerfilDElements() {
        List<ElementoDigital> result = null;
        try {
            Perfil perfil = Perfil.getInstance(perfilDao);
            result = perfil.getElementosDigitalesAnhadidos();
        } catch (SQLiteException e) {
            view.showLoadError();
        }
        return result;
    }

    @Override
    public ElementoDigital getDElementByName(String appName) {
        return elementoDigitalDao.queryBuilder().where(ElementoDigitalDao.Properties.Nombre.like(appName)).unique();
    }
}
