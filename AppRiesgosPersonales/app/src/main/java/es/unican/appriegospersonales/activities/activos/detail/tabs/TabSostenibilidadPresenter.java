package es.unican.appriegospersonales.activities.activos.detail.tabs;

import es.unican.appriegospersonales.model.Activo;

public class TabSostenibilidadPresenter implements ITabSostenibilidadContract.Presenter {
    private final ITabSostenibilidadContract.View view;
    private Activo activo;
    public TabSostenibilidadPresenter(Activo activo, TabSostenibilidadView view) {
        this.activo = activo;
        this.view = view;
    }

    @Override
    public void init() {

    }

    @Override
    public int getEcoPuntuacion() {
        return activo.getEcoPuntuacion();
    }

    @Override
    public int getDurabilidad() {
        return activo.getDurabilidad();
    }

    @Override
    public int getReparabilidad() {
        return activo.getReparabilidad();
    }

    @Override
    public int getReciclabilidad() {
        return activo.getReciclabilidad();
    }

    @Override
    public int getEfClimatica() {
        return activo.getEfClimatica();
    }

    @Override
    public int getEfRecursos() {
        return activo.getEfRecursos();
    }
}
