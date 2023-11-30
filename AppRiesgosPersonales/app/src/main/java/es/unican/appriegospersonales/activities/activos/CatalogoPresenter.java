package es.unican.appriegospersonales.activities.activos;

import android.database.sqlite.SQLiteException;

import java.util.List;

import es.unican.appriegospersonales.common.Callback;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.Tipo;
import es.unican.appriegospersonales.model.Vulnerabilidad;
import es.unican.appriegospersonales.repository.cibel.CibelRepository;
import es.unican.appriegospersonales.repository.cibel.ICibelRepository;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.db.TipoDao;

public class CatalogoPresenter implements ICatalogoContract.Presenter {

    private final ICatalogoContract.View view;
    private ActivoDao activoDao;
    private CategoriaDao categoriaDao;
    private TipoDao tipoDao;
    private PerfilDao perfilDao;
    private ICibelRepository cibelRepository;
    private Perfil perfil;

    public CatalogoPresenter(ICatalogoContract.View view) {
        this.view = view;
    }

    // Para tests
    public CatalogoPresenter(ICatalogoContract.View view, ICibelRepository cibelRepository) {
        this.view = view;
        this.cibelRepository = cibelRepository;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        activoDao = daoSession.getActivoDao();
        categoriaDao = daoSession.getCategoriaDao();
        tipoDao = daoSession.getTipoDao();
        perfilDao = daoSession.getPerfilDao();
        perfil = Perfil.getInstance(perfilDao);

        cibelRepository = new CibelRepository(view.getMyApplication());
        if (cibelRepository.lastDownloadOlderThan(30, CibelRepository.KEY_LAST_SAVED_A)) {
            doSyncInit();
        }
    }

    private void doAsyncInit() {
        cibelRepository.requestCategorias(new Callback<Categoria[]>() {
            @Override
            public void onSuccess(Categoria[] categorias) {
                cibelRepository.requestTipos(new Callback<Tipo[]>() {
                    @Override
                    public void onSuccess(Tipo[] data) {

                        cibelRepository.requestVulnerabilidades(new Callback<Vulnerabilidad[]>() {
                            @Override
                            public void onSuccess(Vulnerabilidad[] data) {
                                cibelRepository.requestActivos(new Callback<Activo[]>() {
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
        if (cibelRepository.getCategorias() == null ||
                cibelRepository.getTipos() == null ||
                cibelRepository.getVulnerabilidades() == null ||
                cibelRepository.getActivos(null) == null) {
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
    public List<Activo> getAllActivos() {
        return activoDao.loadAll();
    }

    @Override
    public List<Activo> getPerfilAssets() {
        List<Activo> result = null;
        try {
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
