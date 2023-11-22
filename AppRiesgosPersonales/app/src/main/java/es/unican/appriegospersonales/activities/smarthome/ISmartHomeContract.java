package es.unican.appriegospersonales.activities.smarthome;

import java.util.ArrayList;
import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Vulnerabilidad;

public interface ISmartHomeContract {
    interface Presenter {
        void init();

        List<Activo> getActivosPerfil();

        List<Activo> getActivosPerfilOrdenadosPorSeguridadAsc();

        ArrayList getEntries();

        List<Vulnerabilidad> getVulnerabilidadesPerfil();

        int getEcoRatingHome();

        int getSecurityRatingHome();

        List<Activo> getActivosPerfilOrdenadosPorSeguridadDesc();

        List<Activo> getActivosPerfilOrdenadosPorSostAsc();

        List<Activo> getActivosPerfilOrdenadosPorSostDesc();
    }

    interface  View {
        MyApplication getMyApplication();
    }
}
