package es.unican.appriegospersonales.activities.main;

import es.unican.appriegospersonales.activities.activos.CatalogoView;
import es.unican.appriegospersonales.activities.smarthome.SmartHomeView;
import es.unican.appriesgospersonales.R;

public class MainPresenter implements IMainContract.Presenter {

    private final IMainContract.View view;

    public MainPresenter(IMainContract.View view) {
        this.view = view;
    }

    @Override
    public void onNavHomeClicked() {
        view.showFragment(new CatalogoView(), R.string.bottom_nav_home);
    }

    @Override
    public void onNavSmartHomeClicked() {
        view.showFragment(new SmartHomeView(), R.string.bottom_nav_perfil);
    }

}
