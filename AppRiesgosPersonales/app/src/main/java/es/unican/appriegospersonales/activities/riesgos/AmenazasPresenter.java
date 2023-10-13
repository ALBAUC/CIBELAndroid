package es.unican.appriegospersonales.activities.riesgos;

import java.util.List;

import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.db.AmenazaDao;
import es.unican.appriegospersonales.repository.db.ControlDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class AmenazasPresenter implements IAmenazasContract.Presenter {

    private final IAmenazasContract.View view;
    private AmenazaDao amenazaDao;
    private PerfilDao perfilDao;
    private ControlDao controlDao;

    public AmenazasPresenter(IAmenazasContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        amenazaDao = daoSession.getAmenazaDao();
        perfilDao = daoSession.getPerfilDao();
        controlDao = daoSession.getControlDao();
    }

    @Override
    public List<Amenaza> getAmenazas() {
        return amenazaDao.loadAll();
    }

    @Override
    public List<Control> getPerfilControls() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        List<Control> perfilControls = controlDao._queryPerfil_ControlesAnhadidos(perfil.getId());
        return perfilControls;
    }
}
