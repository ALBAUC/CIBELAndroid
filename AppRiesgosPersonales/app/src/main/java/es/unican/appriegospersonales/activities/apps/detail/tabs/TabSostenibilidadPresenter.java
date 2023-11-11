package es.unican.appriegospersonales.activities.apps.detail.tabs;

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
    public int getEcoRate() {
        return 77;
    }

    @Override
    public int getDurabilidad() {
        return 42;
    }

    @Override
    public int getReparabilidad() {
        return 55;
    }

    @Override
    public int getReciclabilidad() {
        return 76;
    }

    @Override
    public int getEfClimatica() {
        return 35;
    }

    @Override
    public int getEfRecursos() {
        return 80;
    }
}
