package es.unican.appriegospersonales.activities.apps;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.repository.IAppWiseRepository;

/**
 * Interfaz que define los métodos que deben ser implementados por el presentador y la vista
 * de la pestaña Apps.
 */
public interface IAppsContract {
    interface View {
        /**
         * Devuelve una instancia de MyApplication.
         * @return MyApplication.
         */
        MyApplication getMyApplication();

        /**
         * Se solicita a la vista que muestre una alerta informando que hubo un
         * error cargando las aplicaciones.
         */
        void showLoadError();

        /**
         * Se solicita a la vista que muestre una alerta informando que las
         * aplicaciones han sido cargadas correctaemnte.
         * @param appsCount el numero de aplicaciones que fueron cargadas
         */
        void showLoadCorrect(int appsCount);
    }

    interface Presenter {
        /**
         * Inicializa las DAO y los datos de la base de datos.
         */
        void init();

        /**
         * Devuelve los datos con todas las categorias que se encuentran
         * la base de datos.
         * @return Todas las categorias.
         */
        List<Categoria> getCategorias();

        List<Aplicacion> getPerfilApps();

        Aplicacion getAppByName(String appName);
    }
}
