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
}
