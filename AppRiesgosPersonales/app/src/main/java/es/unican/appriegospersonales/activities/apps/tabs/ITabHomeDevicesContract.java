package es.unican.appriegospersonales.activities.apps.tabs;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Tipo;
import es.unican.appriegospersonales.model.Activo;

public interface ITabHomeDevicesContract {

    interface Presenter {
        void init();

        List<Tipo> getCategoriasDeDispositivos();

        List<Activo> getPerfilAssets();
    }

    interface View {
        MyApplication getMyApplication();
    }
}
