package es.unican.appriegospersonales.activities.perfil.tabs;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.Riesgo;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;

public class TabRisksPresenter {
    private final TabRisksView view;
    private PerfilDao perfilDao;
    private Perfil perfil;

    public TabRisksPresenter(TabRisksView view) {
        this.view = view;
    }

    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        perfilDao = daoSession.getPerfilDao();
        perfil = Perfil.getInstance(perfilDao);
    }

    public List<ElementoDigital> getAppsAnhadidas() {
        return perfil.getElementosDigitalesAnhadidos();
    }

    public Set<Riesgo> getRiesgosActuales() {
        Set<Riesgo> riesgosActuales = new LinkedHashSet<>();
        List<ElementoDigital> dElesAnhadidos = perfil.getElementosDigitalesAnhadidos();
        for (ElementoDigital e : dElesAnhadidos) {
            List<Riesgo> riesgosCat = e.getCat().getRiesgos();
            for (Riesgo r : riesgosCat) {
                riesgosActuales.add(r);
            }
        }
        return riesgosActuales;
    }

    public List<Control> getPerfilControls() {
        return perfil.getControlesAnhadidos();
    }
}
