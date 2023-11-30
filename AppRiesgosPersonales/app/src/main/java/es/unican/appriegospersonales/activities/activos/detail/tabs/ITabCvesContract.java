package es.unican.appriegospersonales.activities.activos.detail.tabs;

import com.github.mikephil.charting.data.PieEntry;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Vulnerabilidad;

public interface ITabCvesContract {
    interface View {

        MyApplication getMyApplication();
    }

    interface Presenter {

        void init();

        List<Vulnerabilidad> getAssetCves();

        List<Vulnerabilidad> getAssetCvesOrdenadorPorFechaRec();

        List<PieEntry> getEntries();

        List<Vulnerabilidad> getAssetCvesOrdenadorPorFechaAnt();

        List<Vulnerabilidad> getAssetCvesOrdenadorPorGravedadAsc();

        List<Vulnerabilidad> getAssetCvesOrdenadorPorGravedadDesc();
    }
}
