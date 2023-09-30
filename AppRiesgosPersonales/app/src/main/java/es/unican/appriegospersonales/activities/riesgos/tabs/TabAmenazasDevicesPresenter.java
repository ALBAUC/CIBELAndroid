package es.unican.appriegospersonales.activities.riesgos.tabs;

import java.util.Collections;
import java.util.List;

import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.CibelRepository;
import es.unican.appriegospersonales.repository.ICibelRepository;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class TabAmenazasDevicesPresenter implements  ITabAmenazasDevicesContract.Presenter {
    private final ITabAmenazasDevicesContract.View view;
    private PerfilDao perfilDao;
    private ICibelRepository repository;

    public TabAmenazasDevicesPresenter(ITabAmenazasDevicesContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();

        if (repository == null) {
            repository = new CibelRepository(view.getMyApplication());
        }
    }

    @Override
    public List<Amenaza> getAmenazasDevices() {
        List<Amenaza> amenazasDeDispositivos = List.of(repository.getAmenazasDeDispositivos());
        if (amenazasDeDispositivos == null) amenazasDeDispositivos = Collections.emptyList();
        return amenazasDeDispositivos;
    }

    @Override
    public List<Control> getPerfilControls() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        return perfil.getControlesAnhadidos();
    }
}
