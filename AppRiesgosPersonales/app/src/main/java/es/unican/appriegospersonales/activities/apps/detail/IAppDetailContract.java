package es.unican.appriegospersonales.activities.apps.detail;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Riesgo;

/**
 * Interfaz que define los métodos que deben ser implementados por el presentador y la vista
 * del detalle de una App.
 */
public interface IAppDetailContract {
    interface  View {

        MyApplication getMyApplication();
    }

    interface Presenter {

        void init();

        List<Riesgo> getAppRisks();

        String getAppIcono();

        String getAppName();

        String getAppCategory();

        void onAddAppClicked();

        boolean isAppAdded();

        List<Aplicacion> getPerfilApps();
    }
}
