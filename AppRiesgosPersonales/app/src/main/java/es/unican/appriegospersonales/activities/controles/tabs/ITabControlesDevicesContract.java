package es.unican.appriegospersonales.activities.controles.tabs;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Control;

public interface ITabControlesDevicesContract {

    interface Presenter {

        void init();

        List<Control> getControlesDevices();

        List<Control> getPerfilControls();
    }

    interface View {

        MyApplication getMyApplication();
    }
}
