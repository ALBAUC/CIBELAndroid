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
import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.JoinCategriasWithControles;
import es.unican.appriegospersonales.model.JoinAmenazasWithControles;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.AmenazaDao;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.ControlDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.JoinAmenazasWithControlesDao;
import es.unican.appriegospersonales.repository.db.JoinCategriasWithControlesDao;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.cibel.rest.CibelService;

/**
 * Implementacion de un repositorio de los recursos de AppWiseService.
 * El repositorio tambien persiste en una base de datos local las listas de
 * aplicaciones, riesgos y controles recuperados.
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
    public void requestActivos(Callback<Activo[]> cb, String categoria) {
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
        }, categoria);
    }

    @Override
    public Activo[] getActivos(String categoria) {
        Activo[] response = CibelService.getActivos(categoria);
        persistToDBActivos(response);
        return response;
    }

    private void persistToDBActivos(Activo[] activos) {
        if (activos != null) {
            ActivoDao activoDao = daoSession.getActivoDao();
            CategoriaDao categoriaDao = daoSession.getCategoriaDao();
            PerfilDao perfilDao = daoSession.getPerfilDao();
            for (Activo a : activos) {
                Activo aBD = activoDao.load(a.getIdActivo());
                if (aBD == null) {
                    // Nuevo activo, se inserta en la bd
                    Categoria cat = categoriaDao.load(a.getCat().getIdCategoria());
                    a.setFk_categoria(cat.getIdCategoria());
                    activoDao.insert(a);
                    // Caso de ejemplo: se insertan directamente al perfil
                    Perfil perfil = Perfil.getInstance(perfilDao);
                    a.setFk_perfil(perfil.getId());
                    perfil.getActivosAnhadidos().add(a);
                    activoDao.update(a);
                    perfilDao.update(perfil);
                } else if (!aBD.equals(a)) {
                    // Ya estaba en la bd, se actualiza
                    aBD.setNombre(a.getNombre());
                    aBD.setIcono(a.getIcono());
                    aBD.setFk_categoria(a.getCat().getIdCategoria());
                    activoDao.update(aBD);
                }
            }
            Prefs.from(application).putInstant(KEY_LAST_SAVED_A, Instant.now());
        }
    }

    @Override
    public void requestAmenazas(Callback<Amenaza[]> cb) {
        CibelService.requestAmenazas(new Callback<Amenaza[]>() {
            @Override
            public void onSuccess(Amenaza[] data) {
                persistToDBAmenazas(data);
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        });
    }

    @Override
    public void requestAmenazasDeApps(Callback<Amenaza[]> cb) {
        CibelService.requestAmenazasDeApps(new Callback<Amenaza[]>() {
            @Override
            public void onSuccess(Amenaza[] data) {
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        });
    }

    @Override
    public void requestAmenazasDeDispositivos(Callback<Amenaza[]> cb) {
        CibelService.requestAmenazasDeDispositivos(new Callback<Amenaza[]>() {
            @Override
            public void onSuccess(Amenaza[] data) {
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        });
    }

    @Override
    public Amenaza[] getAmenazas() {
        Amenaza[] response = CibelService.getAmenazas();
        persistToDBAmenazas(response);
        return response;
    }

    @Override
    public Amenaza[] getAmenazasDeApps() {
        Amenaza[] response = CibelService.getAmenazasDeApps();
        return response;
    }

    @Override
    public Amenaza[] getAmenazasDeDispositivos() {
        Amenaza[] response = CibelService.getAmenazasDeDispositivos();
        return response;
    }

    private void persistToDBAmenazas(Amenaza[] amenazas) {
        if (amenazas != null) {
            AmenazaDao amenazaDao = daoSession.getAmenazaDao();
            JoinAmenazasWithControlesDao rcDao = daoSession.getJoinAmenazasWithControlesDao();
            for (Amenaza a : amenazas) {
                if (amenazaDao.load(a.getIdAmenaza()) == null) {
                    // Nueva amenaza, se inserta en la BBDD
                    List<Control> controles = a.getControles();
                    for (Control c : controles) {
                        JoinAmenazasWithControles rc = new JoinAmenazasWithControles();
                        rc.setIdAmenaza(a.getIdAmenaza());
                        rc.setIdControl(c.getIdControl());
                        rcDao.insert(rc);
                    }
                    amenazaDao.insert(a);
                }
            }
            Prefs.from(application).putInstant(KEY_LAST_SAVED_T, Instant.now());
        }
    }

    @Override
    public void requestControles(Callback<Control[]> cb) {
        CibelService.requestControles(new Callback<Control[]>() {
            @Override
            public void onSuccess(Control[] data) {
                persistToDBControles(data);
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                Log.d(ContentValues.TAG, "Falla aqui x2");
                cb.onFailure();
            }
        });
    }

    @Override
    public Control[] getControles() {
        Control[] response = CibelService.getControles();
        persistToDBControles(response);
        return response;
    }

    @Override
    public Control[] getControlesDeApps() {
        Control[] response = CibelService.getControlesDeApps();
        return response;
    }

    @Override
    public Control[] getControlesDeDispositivos() {
        Control[] response = CibelService.getControlesDeDispositivos();
        return response;
    }

    private void persistToDBControles(Control[] controles) {
        if (controles != null) {
            ControlDao controlDao = daoSession.getControlDao();
            for (Control c : controles) {
                if (controlDao.load(c.getIdControl()) == null) {
                    controlDao.insert(c);
                }
            }
            Prefs.from(application).putInstant(KEY_LAST_SAVED_C, Instant.now());
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

    @Override
    public Categoria[] getCategoriasDeApps() {
        Categoria[] response = CibelService.getCategoriasDeApps();
        return response;
    }

    @Override
    public Categoria[] getCategoriasDeDispositivos() {
        Categoria[] response = CibelService.getCategoriasDeDispositivos();
        return response;
    }

    private void persistToDBCategorias(Categoria[] categorias) {
        if (categorias != null) {
            CategoriaDao categoriaDao = daoSession.getCategoriaDao();
            JoinCategriasWithControlesDao crDao = daoSession.getJoinCategriasWithControlesDao();
            for (Categoria c : categorias) {
                if (categoriaDao.load(c.getIdCategoria()) == null) {
                    // Nueva categoria, se inserta en la BBDD
                    List<Control> controles = c.getControles();
                    categoriaDao.insert(c);
                    for (Control r : controles) {
                        JoinCategriasWithControles cr = new JoinCategriasWithControles();
                        cr.setIdCategoria(c.getIdCategoria());
                        cr.setIdControl(r.getIdControl());
                        crDao.insert(cr);
                    }
                }
            }
            Prefs.from(application).putInstant(KEY_LAST_SAVED_CA, Instant.now());
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
