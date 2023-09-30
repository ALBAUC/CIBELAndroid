package es.unican.appriegospersonales.activities.riesgos.tabs;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;

public interface ITabAmenazasAppsContract {
    interface Presenter {

        void init();

        List<Amenaza> getAmenazasApps();

        List<Control> getPerfilControls();
    }

    interface View {

        MyApplication getMyApplication();
    }
}
