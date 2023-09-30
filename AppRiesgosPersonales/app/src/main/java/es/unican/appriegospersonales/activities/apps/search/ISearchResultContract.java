package es.unican.appriegospersonales.activities.apps.search;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Activo;

public interface ISearchResultContract {

    interface View {
        MyApplication getMyApplication();
    }

    interface Presenter {

        void init();

        List<Activo> doSearch(String query);

        Activo getAssetByName(String appName);

        List<Activo> getPerfilAssets();
    }
}
