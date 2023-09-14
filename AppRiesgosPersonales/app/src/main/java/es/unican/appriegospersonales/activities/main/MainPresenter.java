package es.unican.appriegospersonales.activities.main;

import es.unican.appriegospersonales.activities.apps.HomeView;
import es.unican.appriegospersonales.activities.controles.ControlesView;
import es.unican.appriegospersonales.activities.perfil.PerfilView;
import es.unican.appriegospersonales.activities.riesgos.RiesgosView;
import es.unican.appriesgospersonales.R;

public class MainPresenter implements IMainContract.Presenter {

    private final IMainContract.View view;

    public MainPresenter(IMainContract.View view) {
        this.view = view;
    }

    @Override
    public void onNavHomeClicked() {
        view.showFragment(new HomeView(), R.string.bottom_nav_home);
    }

    @Override
    public void onNavRiesgosClicked() {
        view.showFragment(new RiesgosView(), R.string.bottom_nav_riesgos);
    }

    @Override
    public void onNavControlesClicked() {
        view.showFragment(new ControlesView(), R.string.bottom_nav_controles);
    }

    @Override
    public void onNavPerfilClicked() {
        view.showFragment(new PerfilView(), R.string.bottom_nav_perfil);
    }

}
