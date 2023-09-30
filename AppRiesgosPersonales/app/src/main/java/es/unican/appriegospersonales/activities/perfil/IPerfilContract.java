package es.unican.appriegospersonales.activities.perfil;

import es.unican.appriegospersonales.common.MyApplication;

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
