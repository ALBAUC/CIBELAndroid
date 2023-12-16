package es.unican.cibel.activities.activos.detail.tabs;

public interface ITabSostenibilidadContract {
    interface View {

    }

    interface Presenter {

        void init();

        int getEcoPuntuacion();

        int getDurabilidad();

        int getReparabilidad();

        int getReciclabilidad();

        int getEfClimatica();

        int getEfRecursos();
    }
}
