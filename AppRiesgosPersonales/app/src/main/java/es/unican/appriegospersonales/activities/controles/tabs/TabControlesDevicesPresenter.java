package es.unican.appriegospersonales.activities.controles.tabs;

import java.util.Collections;
import java.util.List;

import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.CibelRepository;
import es.unican.appriegospersonales.repository.ICibelRepository;
import es.unican.appriegospersonales.repository.db.ControlDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.rest.CibelServiceConstants;

public class TabControlesDevicesPresenter implements ITabControlesDevicesContract.Presenter {

    private final ITabControlesDevicesContract.View view;
    private PerfilDao perfilDao;
    private ControlDao controlDao;

    public TabControlesDevicesPresenter(ITabControlesDevicesContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();
        controlDao = daoSession.getControlDao();
    }

    @Override
    public List<Control> getControlesDevices() {
        List<Control> controlesDeDispositivos = controlDao.queryBuilder().where(ControlDao.Properties.Tipo
                .like(CibelServiceConstants.DISPOSITIVO_IOT)).list();
        if (controlesDeDispositivos == null) controlesDeDispositivos = Collections.emptyList();
        return controlesDeDispositivos;
    }


    @Override
    public List<Control> getPerfilControls() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        List<Control> perfilControls = controlDao._queryPerfil_ControlesAnhadidos(perfil.getId());
        return perfilControls;
    }
}
