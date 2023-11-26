package es.unican.appriegospersonales.activities.apps.tipo;

import java.util.List;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Tipo;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.TipoDao;

public class CatalogoTipoPresenter implements ICatalogoTipoContract.Presenter {

    private final ICatalogoTipoContract.View view;
    private final Tipo tipo;
    private ActivoDao activoDao;

    public CatalogoTipoPresenter(Tipo tipo, ICatalogoTipoContract.View view) {
        this.view = view;
        this.tipo = tipo;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        activoDao = daoSession.getActivoDao();
    }

    @Override
    public String getTipoName() {
        return tipo.getNombre();
    }

    @Override
    public List<Activo> getActivosDeTipo() {
        List<Activo> activosDeTipo = activoDao.queryBuilder().where(ActivoDao.Properties.Fk_tipo
                .like(tipo.getIdTipo().toString())).list();
        return activosDeTipo;
    }
}
