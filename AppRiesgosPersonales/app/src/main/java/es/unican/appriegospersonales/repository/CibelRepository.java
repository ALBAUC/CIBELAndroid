package es.unican.appriegospersonales.repository;

import android.content.ContentValues;
import android.util.Log;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import es.unican.appriegospersonales.common.Callback;
import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.common.prefs.Prefs;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriegospersonales.model.JoinCategoriasWithRiesgos;
import es.unican.appriegospersonales.model.JoinRiesgosWithControles;
import es.unican.appriegospersonales.model.Riesgo;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.ControlDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.ElementoDigitalDao;
import es.unican.appriegospersonales.repository.db.JoinCategoriasWithRiesgosDao;
import es.unican.appriegospersonales.repository.db.JoinRiesgosWithControlesDao;
import es.unican.appriegospersonales.repository.db.RiesgoDao;
import es.unican.appriegospersonales.repository.rest.CibelService;

/**
 * Implementacion de un repositorio de los recursos de AppWiseService.
 * El repositorio tambien persiste en una base de datos local las listas de
 * aplicaciones, riesgos y controles recuperados.
 */
public class CibelRepository implements ICibelRepository {

    private static final String KEY_LAST_SAVED_A = "KEY_LAST_SAVED_A";
    private static final String KEY_LAST_SAVED_R = "KEY_LAST_SAVED_R";
    private static final String KEY_LAST_SAVED_C = "KEY_LAST_SAVED_C";
    private static final String KEY_LAST_SAVED_CA = "KEY_LAST_SAVED_CA";

    private final MyApplication application;
    private final DaoSession daoSession;

    public CibelRepository(MyApplication application) {
        this.application = application;
        this.daoSession = application.getDaoSession();
    }

    @Override
    public void requestElementosDigitales(Callback<ElementoDigital[]> cb, String categoria) {
        CibelService.requestElementosDigitales(new Callback<ElementoDigital[]>() {
            @Override
            public void onSuccess(ElementoDigital[] data) {
                persistToDBElementosDigitales(data);
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        }, categoria);
    }

    @Override
    public ElementoDigital[] getElementosDigitales(String categoria) {
        ElementoDigital[] response = CibelService.getElementosDigitales(categoria);
        persistToDBElementosDigitales(response);
        return response;
    }

    private void persistToDBElementosDigitales(ElementoDigital[] elementosDigitales) {
        if (elementosDigitales != null) {
            ElementoDigitalDao elementoDigitalDao = daoSession.getElementoDigitalDao();
            CategoriaDao categoriaDao = daoSession.getCategoriaDao();
            for (ElementoDigital ele : elementosDigitales) {
                ElementoDigital eleBD = elementoDigitalDao.load(ele.getIdElementoD());
                if (eleBD == null) {
                    // Nuevo elemento digital, se inserta en la bd
                    Categoria cat = categoriaDao.load(ele.getCat().getIdCategoria());
                    ele.setFk_categoria(cat.getIdCategoria());
                    elementoDigitalDao.insert(ele);
                } else {
                    // Ya estaba en la bd, lo actualizo
                    Categoria cat = categoriaDao.load(ele.getCat().getIdCategoria());
                    ele.setFk_categoria(cat.getIdCategoria());
                    Long fk_perfil = eleBD.getFk_perfil();
                    ele.setFk_perfil(fk_perfil);
                    elementoDigitalDao.update(ele);
                }
            }
            //Prefs.from(application).putInstant(KEY_LAST_SAVED_A, Instant.now());
        }
    }

    @Override
    public void requestAplicacion(Callback<Aplicacion> cb, String nombre) {
        CibelService.requestAplicacion(new Callback<Aplicacion>() {
            @Override
            public void onSuccess(Aplicacion data) {
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        }, nombre);
    }

    @Override
    public Aplicacion getAplicacion(String nombre) {
        return CibelService.getAplicacion(nombre);
    }

    @Override
    public void requestRiesgos(Callback<Riesgo[]> cb) {
        CibelService.requestRiesgos(new Callback<Riesgo[]>() {
            @Override
            public void onSuccess(Riesgo[] data) {
                persistToDBRiesgos(data);
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        });
    }

    @Override
    public Riesgo[] getRiesgos() {
        Riesgo[] response = CibelService.getRiesgos();
        persistToDBRiesgos(response);
        return response;
    }

    private void persistToDBRiesgos(Riesgo[] riesgos) {
        if (riesgos != null) {
            RiesgoDao riesgoDao = daoSession.getRiesgoDao();
            JoinRiesgosWithControlesDao rcDao = daoSession.getJoinRiesgosWithControlesDao();
            for (Riesgo r : riesgos) {
                if (riesgoDao.load(r.getIdRiesgo()) == null) {
                    List<Control> controles = r.getControles();
                    riesgoDao.insert(r);
                    for (Control c : controles) {
                        JoinRiesgosWithControles rc = new JoinRiesgosWithControles();
                        rc.setIdRiesgo(r.getIdRiesgo());
                        rc.setIdControl(c.getIdControl());
                        rcDao.insert(rc);
                    }
                }
            }
            //Prefs.from(application).putInstant(KEY_LAST_SAVED_R, Instant.now());
        }
    }

    @Override
    public void requestRiesgo(Callback<Riesgo> cb, Long id) {
        CibelService.requestRiesgo(new Callback<Riesgo>() {
            @Override
            public void onSuccess(Riesgo data) {
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        }, id);
    }

    @Override
    public Riesgo getRiesgo(Long id) {
        return CibelService.getRiesgo(id);
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

    private void persistToDBControles(Control[] controles) {
        if (controles != null) {
            ControlDao controlDao = daoSession.getControlDao();
            for (Control c : controles) {
                if (controlDao.load(c.getIdControl()) == null) {
                    controlDao.insert(c);
                }
            }
            //Prefs.from(application).putInstant(KEY_LAST_SAVED_C, Instant.now());
        }
    }

    @Override
    public void requestControl(Callback<Control> cb, Long id) {
        CibelService.requestControl(new Callback<Control>() {
            @Override
            public void onSuccess(Control data) {
                cb.onSuccess(data);
            }

            @Override
            public void onFailure() {
                cb.onFailure();
            }
        }, id);
    }

    @Override
    public Control getControl(Long id) {
        return CibelService.getControl(id);
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
            JoinCategoriasWithRiesgosDao crDao = daoSession.getJoinCategoriasWithRiesgosDao();
            for (Categoria c : categorias) {
                if (categoriaDao.load(c.getIdCategoria()) == null) {
                    List<Riesgo> riesgos = c.getRiesgos();
                    categoriaDao.insert(c);
                    for (Riesgo r : riesgos) {
                        JoinCategoriasWithRiesgos cr = new JoinCategoriasWithRiesgos();
                        cr.setIdCategoria(c.getIdCategoria());
                        cr.setIdRiesgo(r.getIdRiesgo());
                        crDao.insert(cr);
                    }
                }
            }
            //Prefs.from(application).putInstant(KEY_LAST_SAVED_CA, Instant.now());
        }
    }

    /**
     * Retorna true si los recursos guardados en la BD son antiguos a la
     * cantidad específicada de minutos.
     * @param minutes
     * @return true si los recursos guardados en la BD son antiguos a la
     * cantidad específicada de minutos
     */
    private boolean lastDownloadOlderThan(int minutes, String resourceName) {
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
