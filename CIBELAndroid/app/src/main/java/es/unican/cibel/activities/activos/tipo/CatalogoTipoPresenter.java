package es.unican.cibel.activities.activos.tipo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.unican.cibel.model.Activo;
import es.unican.cibel.model.Perfil;
import es.unican.cibel.model.Tipo;
import es.unican.cibel.repository.db.ActivoDao;
import es.unican.cibel.repository.db.DaoSession;
import es.unican.cibel.repository.db.PerfilDao;

public class CatalogoTipoPresenter implements ICatalogoTipoContract.Presenter {

    private final ICatalogoTipoContract.View view;
    private final Tipo tipo;
    private ActivoDao activoDao;
    private PerfilDao perfilDao;

    public CatalogoTipoPresenter(Tipo tipo, ICatalogoTipoContract.View view) {
        this.view = view;
        this.tipo = tipo;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        activoDao = daoSession.getActivoDao();
        perfilDao = daoSession.getPerfilDao();
    }

    @Override
    public String getTipoName() {
        return tipo.getNombre();
    }

    @Override
    public List<Activo> getActivosDeTipo() {
        List<Activo> activosDeTipo = activoDao.queryBuilder().where(ActivoDao.Properties.Fk_tipo
                .like(tipo.getIdTipo().toString())).list();
        return activosDeTipo;
    }

    @Override
    public List<Activo> getActivosTipoOrdenadosPorSeguridadDesc() {
        List<Activo> activosDeTipo = getActivosDeTipo();
        Collections.sort(activosDeTipo, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                return Integer.compare(activo2.getSecurityScore(), activo1.getSecurityScore());
            }
        });

        return activosDeTipo;
    }

    @Override
    public List<Activo> getActivosTipoOrdenadosPorSostAsc() {
        List<Activo> activosDeTipo = getActivosDeTipo();
        Collections.sort(activosDeTipo, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                return Integer.compare(activo1.getEcoPuntuacion(), activo2.getEcoPuntuacion());
            }
        });

        return activosDeTipo;
    }

    @Override
    public List<Activo> getActivosTipoOrdenadosPorSostDesc() {
        List<Activo> activosDeTipo = getActivosDeTipo();
        Collections.sort(activosDeTipo, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                return Integer.compare(activo2.getEcoPuntuacion(), activo1.getEcoPuntuacion());
            }
        });

        return activosDeTipo;
    }

    @Override
    public List<Activo> getActivosTipoOrdenadosPorSeguridadAsc() {
        List<Activo> activosDeTipo = getActivosDeTipo();
        Collections.sort(activosDeTipo, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                return Integer.compare(activo1.getSecurityScore(), activo2.getSecurityScore());
            }
        });

        return activosDeTipo;
    }

    @Override
    public List<Activo> getActivosPerfil() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        List<Activo> perfilAssets = activoDao._queryPerfil_ActivosAnhadidos(perfil.getId());
        return perfilAssets;
    }

    @Override
    public Activo getAssetByName(String appName) {
        return activoDao.queryBuilder().where(ActivoDao.Properties.Nombre.like(appName)).unique();
    }
}
