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

        List<Activo> getActivosOrdenadosPorSeguridadDesc(String query);

        List<Activo> getActivosOrdenadosPorSostAsc(String query);

        List<Activo> getActivosOrdenadosPorSostDesc(String query);

        List<Activo> getActivosOrdenadosPorSeguridadAsc(String query);
    }
}
