package es.unican.appriegospersonales.activities.apps.detail;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriegospersonales.model.Riesgo;

/**
 * Interfaz que define los métodos que deben ser implementados por el presentador y la vista
 * del detalle de un elemento digital.
 */
public interface IDElementDetailContract {
    interface  View {

        MyApplication getMyApplication();
    }

    interface Presenter {

        void init();

        List<Riesgo> getDElementRisks();

        String getDElementIcono();

        String getDElementName();

        String getDElementCategory();

        void onAddDElementClicked();

        boolean isDElementAdded();

        List<ElementoDigital> getPerfilDElements();
    }
}
