package es.unican.appriegospersonales.activities.controles.detail;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Amenaza;

public interface IControlDetailContract {
    interface View {

        MyApplication getMyApplication();
    }

    interface  Presenter {

        String getControlName();

        String getCotrolDesc();

        List<Amenaza> getControlThreats();

        void init();

        Boolean isControlAdded();

        void onAddControlClicked();
    }
}
