package es.unican.appriegospersonales.activities.riesgos.detail;

import java.util.List;

import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class AmenazaDetailPresenter implements  IAmenazaDetailContract.Presenter {

    private IAmenazaDetailContract.View view;
    private Amenaza amenaza;
    private PerfilDao perfilDao;

    public AmenazaDetailPresenter(Amenaza amenaza, IAmenazaDetailContract.View view) {
        this.amenaza = amenaza;
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();
    }

    @Override
    public String getThreatName() {
        return amenaza.getNombre();
    }

    @Override
    public String getThreatDesc() {
        return amenaza.getDescripcion();
    }

    @Override
    public List<Control> getThreatControls() {
        return amenaza.getControles();
    }

    @Override
    public List<Control> getPerfilControls() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        return perfil.getControlesAnhadidos();
    }
}
