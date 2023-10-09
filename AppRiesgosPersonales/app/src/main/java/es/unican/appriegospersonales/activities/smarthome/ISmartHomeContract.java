package es.unican.appriegospersonales.activities.smarthome;

import java.util.ArrayList;
import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Activo;

public interface ISmartHomeContract {
    interface Presenter {
        void init();

        List<Activo> getActivosPerfil();

        ArrayList getEntries();
    }

    interface  View {
        MyApplication getMyApplication();
    }
}
