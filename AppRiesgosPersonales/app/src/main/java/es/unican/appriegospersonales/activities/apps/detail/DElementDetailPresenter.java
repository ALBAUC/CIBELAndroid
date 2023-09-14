package es.unican.appriegospersonales.activities.apps.detail;

import java.util.List;

import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.Riesgo;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.ElementoDigitalDao;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class DElementDetailPresenter implements IDElementDetailContract.Presenter {

    private final IDElementDetailContract.View view;
    private ElementoDigitalDao elementoDigitalDao;
    private PerfilDao perfilDao;
    private ElementoDigital elementoDigital;
    private Perfil perfil;

    public DElementDetailPresenter(ElementoDigital elementoDigital, IDElementDetailContract.View view) {
        this.elementoDigital = elementoDigital;
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        elementoDigitalDao = daoSession.getElementoDigitalDao();
        perfilDao = daoSession.getPerfilDao();
        perfil = Perfil.getInstance(perfilDao);
    }

    @Override
    public List<Riesgo> getDElementRisks() {
        return elementoDigital.getCategoria().getRiesgos();
    }

    @Override
    public String getDElementIcono() {
        return elementoDigital.getIcono();
    }

    @Override
    public String getDElementName() {
        return elementoDigital.getNombre();
    }

    @Override
    public String getDElementCategory() {
        return elementoDigital.getCategoria().getNombre();
    }

    @Override
    public void onAddDElementClicked() {
        if (!isDElementAdded()) {
            elementoDigital.setFk_perfil(perfil.getId());
            perfil.getElementosDigitalesAnhadidos().add(elementoDigital);
            elementoDigitalDao.update(elementoDigital);
            perfilDao.update(perfil);
        } else {
            elementoDigital.setFk_perfil(null);
            perfil.getElementosDigitalesAnhadidos().remove(elementoDigital);
            elementoDigitalDao.update(elementoDigital);
            perfilDao.update(perfil);
        }
    }

    @Override
    public boolean isDElementAdded() {
        return perfil.getElementosDigitalesAnhadidos().contains(elementoDigital);
    }

    @Override
    public List<ElementoDigital> getPerfilDElements() {
        return perfil.getElementosDigitalesAnhadidos();
    }
}
