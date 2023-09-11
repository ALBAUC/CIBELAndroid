package es.unican.appriegospersonales.activities.apps.search;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Aplicacion;

public interface ISearchResultContract {

    interface View {
        MyApplication getMyApplication();
    }

    interface Presenter {

        void init();

        List<Aplicacion> doSearch(String query);

        Aplicacion getAppByName(String appName);

        List<Aplicacion> getPerfilApps();
    }
}
