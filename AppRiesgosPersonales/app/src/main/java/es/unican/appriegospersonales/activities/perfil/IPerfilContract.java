package es.unican.appriegospersonales.activities.perfil;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Control;

/**
 * Interfaz que define los métodos que deben ser implementados por el presentador y la vista
 * de la pestaña Perfil.
 */
public interface IPerfilContract {
    interface Presenter {

        int getNivelRiesgo();

        void init();
    }

    interface View {


        MyApplication getMyApplication();
    }
}
