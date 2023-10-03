package es.unican.appriegospersonales.activities.controles.detail;

import android.util.Log;

import java.util.List;

import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.db.AmenazaDao;
import es.unican.appriegospersonales.repository.db.ControlDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class ControlDetailPresenter implements IControlDetailContract.Presenter {

    private final IControlDetailContract.View view;
    private ControlDao controlDao;
    private PerfilDao perfilDao;
    private AmenazaDao amenazaDao;
    private Control control;
    private Perfil perfil;

    public ControlDetailPresenter(Control control, IControlDetailContract.View view) {
        this.control = control;
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        controlDao = daoSession.getControlDao();
        perfilDao = daoSession.getPerfilDao();
        amenazaDao = daoSession.getAmenazaDao();
        perfil = Perfil.getInstance(perfilDao);
    }

    @Override
    public String getControlName() {
        return control.getNombre();
    }

    @Override
    public String getCotrolDesc() {
        return control.getDescripcion();
    }

    @Override
    public List<Amenaza> getControlThreats() {
        List<Amenaza> controlThreats = amenazaDao._queryControl_MitigaAmenazas(control.getIdControl());
        return controlThreats;
    }

    @Override
    public Boolean isControlAdded() {
        Perfil p = perfilDao.load(perfil.getId());
        return p.getControlesAnhadidos().contains(control);
    }

    @Override
    public void onAddControlClicked() {
        if (!isControlAdded()) {
            control.setFk_perfil(perfil.getId());
            perfil.getControlesAnhadidos().add(control);
            controlDao.update(control);
            perfilDao.update(perfil);
        } else {
            control.setFk_perfil(null);
            perfil.getControlesAnhadidos().remove(control);
            controlDao.update(control);
            perfilDao.update(perfil);
        }
    }
}
