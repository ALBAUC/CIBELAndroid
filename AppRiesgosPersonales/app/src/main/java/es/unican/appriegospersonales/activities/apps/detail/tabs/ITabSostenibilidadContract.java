package es.unican.appriegospersonales.activities.apps.detail.tabs;

public class ITabSostenibilidadContract {
    interface View {

    }

    interface Presenter {

        void init();

        int getEcoRate();

        int getDurabilidad();

        int getReparabilidad();

        int getReciclabilidad();

        int getEfClimatica();

        int getEfRecursos();
    }
}
