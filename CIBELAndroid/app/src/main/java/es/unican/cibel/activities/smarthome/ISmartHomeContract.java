package es.unican.cibel.activities.smarthome;

import java.util.ArrayList;
import java.util.List;

import es.unican.cibel.common.MyApplication;
import es.unican.cibel.model.Activo;
import es.unican.cibel.model.Vulnerabilidad;

public interface ISmartHomeContract {
    interface Presenter {
        void init();

        List<Activo> getActivosPerfil();

        List<Activo> getActivosPerfilOrdenadosPorSeguridadAsc();

        int getEcoPuntuacionHome();

        int getSecurityRatingHome();

        List<Activo> getActivosPerfilOrdenadosPorSeguridadDesc();

        List<Activo> getActivosPerfilOrdenadosPorSostAsc();

        List<Activo> getActivosPerfilOrdenadosPorSostDesc();
    }

    interface  View {
        MyApplication getMyApplication();
    }
}
