package es.unican.appriegospersonales.activities.controles.tabs;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;

public interface ITabControlesAppsContract {

    interface Presenter {
        void init();
        List<Control> getControlesApps();
        List<Control> getPerfilControls();
    }

    interface View {
        MyApplication getMyApplication();
    }
}
