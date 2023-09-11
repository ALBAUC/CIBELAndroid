package es.unican.appriegospersonales.activities.riesgos.detail;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Control;

public interface IRiesgoDetailContract {
    interface View {

        MyApplication getMyApplication();
    }

    interface Presenter {

        void init();

        String getRiesgoName();

        String getRiesgoDesc();

        List<Control> getRiskControls();

        List<Control> getPerfilControls();
    }
}
