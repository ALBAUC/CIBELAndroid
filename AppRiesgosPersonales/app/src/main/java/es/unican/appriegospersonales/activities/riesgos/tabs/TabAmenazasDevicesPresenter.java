package es.unican.appriegospersonales.activities.riesgos.tabs;

import java.util.Collections;
import java.util.List;

import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.CibelRepository;
import es.unican.appriegospersonales.repository.ICibelRepository;
import es.unican.appriegospersonales.repository.db.AmenazaDao;
import es.unican.appriegospersonales.repository.db.ControlDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.rest.CibelServiceConstants;

public class TabAmenazasDevicesPresenter implements  ITabAmenazasDevicesContract.Presenter {
    private final ITabAmenazasDevicesContract.View view;
    private PerfilDao perfilDao;
    private AmenazaDao amenazaDao;
    private ControlDao controlDao;

    public TabAmenazasDevicesPresenter(ITabAmenazasDevicesContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();
        amenazaDao = daoSession.getAmenazaDao();
        controlDao = daoSession.getControlDao();
    }

    @Override
    public List<Amenaza> getAmenazasDevices() {
        List<Amenaza> amenazasDeDispositivos = amenazaDao.queryBuilder().where(AmenazaDao.Properties.Tipo
                .like(CibelServiceConstants.DISPOSITIVO_IOT)).list();
        if (amenazasDeDispositivos == null) amenazasDeDispositivos = Collections.emptyList();
        return amenazasDeDispositivos;
    }

    @Override
    public List<Control> getPerfilControls() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        List<Control> perfilControls = controlDao._queryPerfil_ControlesAnhadidos(perfil.getId());
        return perfilControls;
    }
}
