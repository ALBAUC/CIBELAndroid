package es.unican.appriegospersonales.activities.apps.detail.tabs;

public interface ITabSostenibilidadContract {
    interface View {

    }

    interface Presenter {

        void init();

        int getEcoRating();

        int getDurabilidad();

        int getReparabilidad();

        int getReciclabilidad();

        int getEfClimatica();

        int getEfRecursos();
    }
}
