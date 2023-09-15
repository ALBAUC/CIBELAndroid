package es.unican.appriegospersonales.activities.riesgos;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Riesgo;

/**
 * Interfaz que define los métodos que deben ser implementados por el presentador y la vista
 * de la pestaña Riesgos.
 */
public interface IRiesgosContract {

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
        List<Riesgo> getRiesgos();

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
