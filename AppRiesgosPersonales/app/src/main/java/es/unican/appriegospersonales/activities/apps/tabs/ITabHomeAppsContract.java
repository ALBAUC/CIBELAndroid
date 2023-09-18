package es.unican.appriegospersonales.activities.apps.tabs;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.ElementoDigital;

public interface ITabHomeAppsContract {

    interface Presenter {
        void init();

        List<Categoria> getCategoriasDeApps();

        List<ElementoDigital> getPerfilDElements();
    }

    interface View {
        MyApplication getMyApplication();
    }
}
