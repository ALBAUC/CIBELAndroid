package es.unican.appriegospersonales.activities.controles.detail;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Riesgo;

public interface IControlDetailContract {
    interface View {

        MyApplication getMyApplication();
    }

    interface  Presenter {

        String getControlName();

        String getCotrolDesc();

        List<Riesgo> getControlRisks();

        void init();

        Boolean isControlAdded();

        void onAddControlClicked();
    }
}
