package es.unican.appriegospersonales.activities.riesgos;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;

/**
 * Interfaz que define los métodos que deben ser implementados por el presentador y la vista
 * de la pestaña Riesgos.
 */
public interface IAmenazasContract {

    interface Presenter {
        /**
         * Inicializa las DAO y los riesgos de la vista.
         */
        void init();

        /**
         * Devuelve los datos con todas los riesgos que se encuentran
         * la base de datos.
         * @return Todos los riesgos.
         */
        List<Amenaza> getAmenazas();

        List<Control> getPerfilControls();
    }

    interface View {
        /**
         * Devuelve una instancia de MyApplication.
         * @return MyApplication.
         */
        MyApplication getMyApplication();
    }
}
