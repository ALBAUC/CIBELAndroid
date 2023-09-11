package es.unican.appriegospersonales.activities.apps.detail;

import java.util.List;

import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.Riesgo;
import es.unican.appriegospersonales.repository.db.AplicacionDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class AppDetailPresenter implements IAppDetailContract.Presenter {

    private final IAppDetailContract.View view;
    private AplicacionDao aplicacionDao;
    private PerfilDao perfilDao;
    private Aplicacion aplicacion;
    private Perfil perfil;

    public AppDetailPresenter(Aplicacion aplicacion, IAppDetailContract.View view) {
        this.aplicacion = aplicacion;
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        aplicacionDao = daoSession.getAplicacionDao();
        perfilDao = daoSession.getPerfilDao();
        perfil = Perfil.getInstance(perfilDao);
    }

    @Override
    public List<Riesgo> getAppRisks() {
        return aplicacion.getCategoria().getRiesgos();
    }

    @Override
    public String getAppIcono() {
        return aplicacion.getIcono();
    }

    @Override
    public String getAppName() {
        return aplicacion.getNombre();
    }

    @Override
    public String getAppCategory() {
        return aplicacion.getCategoria().getNombre();
    }

    @Override
    public void onAddAppClicked() {
        if (!isAppAdded()) {
            aplicacion.setFk_perfil(perfil.getId());
            perfil.getAppsAnhadidas().add(aplicacion);
            aplicacionDao.update(aplicacion);
            perfilDao.update(perfil);
        } else {
            aplicacion.setFk_perfil(null);
            perfil.getAppsAnhadidas().remove(aplicacion);
            aplicacionDao.update(aplicacion);
            perfilDao.update(perfil);
        }
    }

    @Override
    public boolean isAppAdded() {
        return perfil.getAppsAnhadidas().contains(aplicacion);
    }

    @Override
    public List<Aplicacion> getPerfilApps() {
        return perfil.getAppsAnhadidas();
    }
}
