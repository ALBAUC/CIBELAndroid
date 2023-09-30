package es.unican.appriegospersonales.activities.controles.tabs;

import java.util.Collections;
import java.util.List;

import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.CibelRepository;
import es.unican.appriegospersonales.repository.ICibelRepository;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class TabControlesDevicesPresenter implements ITabControlesDevicesContract.Presenter {

    private final ITabControlesDevicesContract.View view;
    private PerfilDao perfilDao;
    private ICibelRepository repository;

    public TabControlesDevicesPresenter(ITabControlesDevicesContract.View view) {
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
    public List<Control> getControlesDevices() {
        List<Control> controlesDeDispositivos = List.of(repository.getControlesDeDispositivos());
        if (controlesDeDispositivos == null) controlesDeDispositivos = Collections.emptyList();
        return controlesDeDispositivos;
    }


    @Override
    public List<Control> getPerfilControls() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        return perfil.getControlesAnhadidos();
    }
}
