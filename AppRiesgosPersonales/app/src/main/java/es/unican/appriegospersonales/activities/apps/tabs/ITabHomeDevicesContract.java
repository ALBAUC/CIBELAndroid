package es.unican.appriegospersonales.activities.apps.tabs;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.ElementoDigital;

public interface ITabHomeDevicesContract {

    interface Presenter {
        void init();

        List<Categoria> getCategoriasDeDispositivos();

        List<ElementoDigital> getPerfilDElements();
    }

    interface View {
        MyApplication getMyApplication();
    }
}
