package es.unican.cibel.activities.smarthome;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import es.unican.cibel.model.Activo;
import es.unican.cibel.model.Perfil;
import es.unican.cibel.model.Vulnerabilidad;
import es.unican.cibel.repository.db.ActivoDao;
import es.unican.cibel.repository.db.DaoSession;
import es.unican.cibel.repository.db.PerfilDao;
import es.unican.cibel.repository.db.VulnerabilidadDao;

public class SmartHomePresenter implements ISmartHomeContract.Presenter {
    private final ISmartHomeContract.View view;
    private Perfil perfil;
    private ActivoDao activoDao;
    private VulnerabilidadDao vulnerabilidadDao;

    public SmartHomePresenter(ISmartHomeContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        PerfilDao perfilDao = daoSession.getPerfilDao();
        perfil = Perfil.getInstance(perfilDao);
        activoDao = daoSession.getActivoDao();
        vulnerabilidadDao = daoSession.getVulnerabilidadDao();
    }

    @Override
    public List<Activo> getActivosPerfil() {
        List<Activo> perfilAssets = activoDao._queryPerfil_ActivosAnhadidos(perfil.getId());
        return perfilAssets;
    }


    private List<Vulnerabilidad> getVulnerabilidadesPorActivo(Activo activo) {
        return vulnerabilidadDao._queryActivo_Vulnerabilidades(activo.getIdActivo());
    }

    @Override
    public int getEcoPuntuacionHome() {
        List<Activo> activos = getActivosPerfil();
        int result = 100;
        if (activos.size() != 0) {
            double s = 0;
            for (Activo a : activos) {
                s += a.getEcoPuntuacion();
            }
            result = (int) Math.round(s / activos.size());
        }

        return result;
    }

    @Override
    public int getSecurityRatingHome() {
        List<Activo> activos = getActivosPerfil();
        int result = 100;
        if (activos.size() != 0) {
            double s = 0;
            for (Activo a : activos) {
                s += a.getSecurityScore();
            }
            result = (int) Math.round(s / activos.size());
        }
        return result;
    }

    @Override
    public List<Activo> getActivosPerfilOrdenadosPorSeguridadAsc() {
        List<Activo> perfilAssets = getActivosPerfil();
        Collections.sort(perfilAssets, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                return Integer.compare(activo1.getSecurityScore(), activo2.getSecurityScore());
            }
        });

        return perfilAssets;
    }

    @Override
    public List<Activo> getActivosPerfilOrdenadosPorSeguridadDesc() {
        List<Activo> perfilAssets = getActivosPerfil();
        Collections.sort(perfilAssets, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                return Integer.compare(activo2.getSecurityScore(), activo1.getSecurityScore());
            }
        });

        return perfilAssets;
    }

    @Override
    public List<Activo> getActivosPerfilOrdenadosPorSostAsc() {
        List<Activo> perfilAssets = getActivosPerfil();
        Collections.sort(perfilAssets, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                return Integer.compare(activo1.getEcoPuntuacion(), activo2.getEcoPuntuacion());
            }
        });

        return perfilAssets;
    }

    @Override
    public List<Activo> getActivosPerfilOrdenadosPorSostDesc() {
        List<Activo> perfilAssets = getActivosPerfil();
        Collections.sort(perfilAssets, new Comparator<Activo>() {
            @Override
            public int compare(Activo activo1, Activo activo2) {
                return Integer.compare(activo2.getEcoPuntuacion(), activo1.getEcoPuntuacion());
            }
        });

        return perfilAssets;
    }

}
