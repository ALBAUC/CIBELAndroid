package es.unican.appriegospersonales.activities.smarthome;

import androidx.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Vulnerabilidad;

public interface ISmartHomeContract {
    interface Presenter {
        void init();

        List<Activo> getActivosPerfil();

        List<Activo> getActivosPerfilOrdenadosPorRiesgo();

        ArrayList getEntries();

        List<Vulnerabilidad> getVulnerabilidadesPerfil();
    }

    interface  View {
        MyApplication getMyApplication();
        String getStringFromRes(@StringRes int resourceId);
    }
}
