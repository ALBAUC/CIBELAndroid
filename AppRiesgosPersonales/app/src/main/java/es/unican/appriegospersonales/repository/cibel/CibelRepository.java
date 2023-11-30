package es.unican.appriegospersonales.repository.cibel;

import android.content.ContentValues;
import android.util.Log;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import es.unican.appriegospersonales.common.Callback;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.prefs.Prefs;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.JoinActivosWithVulnerabilidades;
import es.unican.appriegospersonales.model.Tipo;
import es.unican.appriegospersonales.model.Vulnerabilidad;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.JoinActivosWithVulnerabilidadesDao;
import es.unican.appriegospersonales.repository.cibel.rest.CibelService;
import es.unican.appriegospersonales.repository.db.TipoDao;
import es.unican.appriegospersonales.repository.db.VulnerabilidadDao;

/**
 * Implementacion de un repositorio de los recursos de CibelService.
 * El repositorio tambien persiste en una base de datos local los datos.
 */
public class CibelRepository implements ICibelRepository {

    public static final String KEY_LAST_SAVED_A = "KEY_LAST_SAVED_A";
    private static final String KEY_LAST_SAVED_T = "KEY_LAST_SAVED_T";
    private static final String KEY_LAST_SAVED_C = "KEY_LAST_SAVED_C";
    private static final String KEY_LAST_SAVED_CA = "KEY_LAST_SAVED_CA";

    private final MyApplication application;
    private final DaoSession daoSession;

    public CibelRepository(MyApplication application) {
        this.application = application;
        this.daoSession = application.getDaoSession();
    }

    @Override
    public void requestActivos(Callback<Activo[]> cb, String tipo) {
        CibelService.requestActivos(new Callback<Activo[]>() {
            @Override
            public void onSuccess(Activo[] data) {
                persistToDBActivos(data);
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        }, tipo);
    }

    @Override
    public Activo[] getActivos(String tipo) {
        Activo[] response = CibelService.getActivos(tipo);
        persistToDBActivos(response);
        return response;
    }

    private void persistToDBActivos(Activo[] activos) {
        if (activos != null) {
            ActivoDao activoDao = daoSession.getActivoDao();
            CategoriaDao categoriaDao = daoSession.getCategoriaDao();
            TipoDao tipoDao = daoSession.getTipoDao();
            JoinActivosWithVulnerabilidadesDao avDao = daoSession.getJoinActivosWithVulnerabilidadesDao();
            for (Activo a : activos) {
                Activo aBD = activoDao.load(a.getIdActivo());
                if (aBD == null) {
                    // Nuevo activo, se inserta en la bd
//                    Categoria cat = categoriaDao.load(a.getCategoriaTrampa().getIdCategoria());
//                    a.setFk_categoria(cat.getIdCategoria());
                    Tipo t = tipoDao.load(a.getTipoTrampa().getIdTipo());
                    a.setFk_tipo(t.getIdTipo());
                    for (Vulnerabilidad v : a.getVulnerabilidades()) {
                        JoinActivosWithVulnerabilidades av = new JoinActivosWithVulnerabilidades();
                        av.setActivoId(a.getIdActivo());
                        av.setVulnerabilidadId(v.getIdCVE());
                        avDao.insert(av);
                    }
                    activoDao.insert(a);
                } else if (!aBD.equals(a)) {
                    // Ya estaba en la bd, se actualiza
                    aBD.setNombre(a.getNombre());
                    aBD.setIcono(a.getIcono());
//                    aBD.setFk_categoria(a.getCategoriaTrampa().getIdCategoria());
                    aBD.setFk_tipo(a.getTipoTrampa().getIdTipo());
                    activoDao.update(aBD);
                }
            }
            Prefs.from(application).putInstant(KEY_LAST_SAVED_A, Instant.now());
        }
    }

    @Override
    public void requestCategorias(Callback<Categoria[]> cb) {
        CibelService.requestCategorias(new Callback<Categoria[]>() {
            @Override
            public void onSuccess(Categoria[] data) {
                persistToDBCategorias(data);
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        });
    }

    @Override
    public Categoria[] getCategorias() {
        Categoria[] response = CibelService.getCategorias();
        persistToDBCategorias(response);
        return response;
    }

    private void persistToDBCategorias(Categoria[] categorias) {
        if (categorias != null) {
            CategoriaDao categoriaDao = daoSession.getCategoriaDao();
            for (Categoria c : categorias) {
                if (categoriaDao.load(c.getIdCategoria()) == null) {
                    // Nueva categoria, se inserta en la BBDD
                    categoriaDao.insert(c);
                }
            }
            Prefs.from(application).putInstant(KEY_LAST_SAVED_CA, Instant.now());
        }
    }

    private void persistToDBTipos(Tipo[] tipos) {
        if (tipos != null) {
            TipoDao tipoDao = daoSession.getTipoDao();
            for (Tipo t : tipos) {
                if (tipoDao.load(t.getIdTipo()) == null) {
                    tipoDao.insert(t);
                }
            }
        }
    }

    @Override
    public void requestTipos(Callback<Tipo[]> cb) {
        CibelService.requestTipos(new Callback<Tipo[]>() {
            @Override
            public void onSuccess(Tipo[] data) {
                persistToDBTipos(data);
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        });
    }

    @Override
    public Tipo[] getTipos() {
        Tipo[] response = CibelService.getTipos();
        persistToDBTipos(response);
        return response;
    }

    @Override
    public void requestVulnerabilidades(Callback<Vulnerabilidad[]> cb) {
        CibelService.requestVulnerabilidades(new Callback<Vulnerabilidad[]>() {
            @Override
            public void onSuccess(Vulnerabilidad[] data) {
                persistToDBVulnerabilidades(data);
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        });
    }

    @Override
    public Vulnerabilidad[] getVulnerabilidades() {
        Vulnerabilidad[] response = CibelService.getVulnerabilidades();
        persistToDBVulnerabilidades(response);
        return response;
    }

    private void persistToDBVulnerabilidades(Vulnerabilidad[] vulnerabilidades) {
        if (vulnerabilidades != null) {
            VulnerabilidadDao vulnerabilidadDao = daoSession.getVulnerabilidadDao();
            for (Vulnerabilidad v : vulnerabilidades) {
                if (vulnerabilidadDao.load(v.getIdCVE()) == null) {
                    vulnerabilidadDao.insert(v);
                }
            }
        }
    }

    /**
     * Retorna true si los recursos guardados en la BD son antiguos a la
     * cantidad específicada de minutos.
     * @param minutes
     * @return true si los recursos guardados en la BD son antiguos a la
     * cantidad específicada de minutos
     */
    public boolean lastDownloadOlderThan(int minutes, String resourceName) {
        Instant lastDownloaded = Prefs.from(application).getInstant(resourceName);
        if (lastDownloaded == null) {
            return true;
        } else {
            Instant now = Instant.now();
            long sinceLastDownloaded = ChronoUnit.MINUTES.between(lastDownloaded, now);  // minutes
            return (sinceLastDownloaded > minutes);
        }
    }
}
