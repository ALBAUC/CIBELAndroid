package es.unican.appriegospersonales.activities.apps.search;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.ElementoDigital;

public interface ISearchResultContract {

    interface View {
        MyApplication getMyApplication();
    }

    interface Presenter {

        void init();

        List<ElementoDigital> doSearch(String query);

        ElementoDigital getDElementByName(String appName);

        List<ElementoDigital> getPerfilApps();
    }
}
