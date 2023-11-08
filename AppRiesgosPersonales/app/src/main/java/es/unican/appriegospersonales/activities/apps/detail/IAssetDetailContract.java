package es.unican.appriegospersonales.activities.apps.detail;

import androidx.annotation.StringRes;

import com.github.mikephil.charting.data.PieEntry;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Vulnerabilidad;

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

        String getAssetIcon();

        String getAssetName();

        String getAssetType();

        void onAddAssetClicked();

        boolean isAssetAdded();

        List<Activo> getPerfilAssets();

        List<Control> getPerfilControls();

        List<Vulnerabilidad> getAssetCves();
    }
}
