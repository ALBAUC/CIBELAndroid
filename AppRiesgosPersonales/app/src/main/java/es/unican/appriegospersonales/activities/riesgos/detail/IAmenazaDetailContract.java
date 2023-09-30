package es.unican.appriegospersonales.activities.riesgos.detail;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Control;

public interface IAmenazaDetailContract {
    interface View {

        MyApplication getMyApplication();
    }

    interface Presenter {

        void init();

        String getThreatName();

        String getThreatDesc();

        List<Control> getThreatControls();

        List<Control> getPerfilControls();
    }
}
