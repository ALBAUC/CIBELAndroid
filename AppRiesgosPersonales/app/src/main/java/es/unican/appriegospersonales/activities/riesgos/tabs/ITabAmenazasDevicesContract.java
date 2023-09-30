package es.unican.appriegospersonales.activities.riesgos.tabs;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;

public interface ITabAmenazasDevicesContract {
    interface Presenter {

        void init();

        List<Amenaza> getAmenazasDevices();

        List<Control> getPerfilControls();
    }

    interface View {

        MyApplication getMyApplication();
    }
}
