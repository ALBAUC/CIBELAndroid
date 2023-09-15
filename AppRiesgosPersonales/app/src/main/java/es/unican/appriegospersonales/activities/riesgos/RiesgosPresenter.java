package es.unican.appriegospersonales.activities.riesgos;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.List;

import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.Riesgo;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.db.RiesgoDao;

public class RiesgosPresenter implements IRiesgosContract.Presenter {

    private final IRiesgosContract.View view;
    private RiesgoDao riesgoDao;
    private PerfilDao perfilDao;

    public RiesgosPresenter(IRiesgosContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        riesgoDao = daoSession.getRiesgoDao();
        perfilDao = daoSession.getPerfilDao();
    }

    @Override
    public List<Riesgo> getRiesgos() {
        return riesgoDao.loadAll();
    }

    @Override
    public List<Control> getPerfilControls() {
        Perfil perfil = Perfil.getInstance(perfilDao);
        return perfil.getControlesAnhadidos();
    }
}
