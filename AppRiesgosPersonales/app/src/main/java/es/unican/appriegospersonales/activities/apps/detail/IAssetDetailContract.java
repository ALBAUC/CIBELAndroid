package es.unican.appriegospersonales.activities.apps.detail;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Control;

/**
 * Interfaz que define los métodos que deben ser implementados por el presentador y la vista
 * del detalle de un elemento digital.
 */
public interface IAssetDetailContract {
    interface  View {

        MyApplication getMyApplication();
    }

    interface Presenter {

        void init();

        List<Control> getAssetControls();

        String getAssetIcon();

        String getAssetName();

        String getAssetCategory();

        void onAddAssetClicked();

        boolean isAssetAdded();

        List<Activo> getPerfilAssets();

        List<Control> getPerfilControls();
    }
}
