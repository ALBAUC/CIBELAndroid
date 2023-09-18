package es.unican.appriegospersonales.activities.riesgos.tabs;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Riesgo;

public interface ITabRiesgosAppsContract {
    interface Presenter {

        void init();

        List<Riesgo> getRiesgosApps();

        List<Control> getPerfilControls();
    }

    interface View {

        MyApplication getMyApplication();
    }
}
