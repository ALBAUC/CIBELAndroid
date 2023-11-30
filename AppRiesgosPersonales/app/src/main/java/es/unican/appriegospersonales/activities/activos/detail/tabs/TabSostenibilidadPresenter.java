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
    public int getEcoRating() {
        return 84;
    }

    @Override
    public int getDurabilidad() {
        return 74;
    }

    @Override
    public int getReparabilidad() {
        return 67;
    }

    @Override
    public int getReciclabilidad() {
        return 87;
    }

    @Override
    public int getEfClimatica() {
        return 76;
    }

    @Override
    public int getEfRecursos() {
        return 85;
    }
}
