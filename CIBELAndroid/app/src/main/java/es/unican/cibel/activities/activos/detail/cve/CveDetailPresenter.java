package es.unican.cibel.activities.activos.detail.cve;

import android.util.Log;

import java.util.List;
import java.util.Locale;

import es.unican.cibel.model.Debilidad;
import es.unican.cibel.model.Vulnerabilidad;
import es.unican.cibel.repository.db.DaoSession;
import es.unican.cibel.repository.db.DebilidadDao;

public class CveDetailPresenter implements ICveDetailContract.Presenter {

    private final ICveDetailContract.View view;
    private Vulnerabilidad vulnerabilidad;

    private DebilidadDao debilidadDao;

    public CveDetailPresenter(Vulnerabilidad vulnerabilidad, CveDetailView cveDetailView) {
        this.view = cveDetailView;
        this.vulnerabilidad = vulnerabilidad;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        debilidadDao = daoSession.getDebilidadDao();
    }

    @Override
    public double getCveBaseScore() {
        return vulnerabilidad.getBaseScore();
    }

    @Override
    public String getCveId() {
        return vulnerabilidad.getIdCVE();
    }

    @Override
    public String getCveDescripcion() {
        String result = "";
        Locale locale = view.getMyApplication().getResources().getConfiguration().getLocales().get(0);
        String language = locale.getLanguage();
        if (language.equals("es")) {
            result = vulnerabilidad.getDescripcion();
        } else if (language.equals("en")) {
            result = vulnerabilidad.getDescripcion_en();
        }
        return result;
    }

    @Override
    public String getCveConfidenciality() {
        return vulnerabilidad.getConfidentialityImpact();
    }

    @Override
    public String getCveIntegrity() {
        return vulnerabilidad.getIntegrityImpact();
    }

    @Override
    public String getCveAvailability() {
        return vulnerabilidad.getAvailabilityImpact();
    }

    @Override
    public int mapImpact(String impact) {
        return vulnerabilidad.mapImpact(impact);
    }

    public List<Debilidad> getCwes() {
        List<Debilidad> cwes = debilidadDao._queryVulnerabilidad_Cwes(vulnerabilidad.getIdCVE());
        return cwes;
    }

    @Override
    public String getIdCWE(Debilidad cwe) {
        return cwe.getIdCWE();
    }

    @Override
    public String getNombre(Debilidad cwe) {
        String result = "";
        Locale locale = view.getMyApplication().getResources().getConfiguration().getLocales().get(0);
        String language = locale.getLanguage();
        if (language.equals("es")) {
            result = cwe.getNombre();
        } else if (language.equals("en")) {
            result = cwe.getNombre_en();
        }
        return result;
    }

    @Override
    public String getDescripcion(Debilidad cwe) {
        String result = "";
        Locale locale = view.getMyApplication().getResources().getConfiguration().getLocales().get(0);
        String language = locale.getLanguage();
        if (language.equals("es")) {
            result = cwe.getDescripcion();
        } else if (language.equals("en")) {
            result = cwe.getDescripcion_en();
        }
        return result;
    }
}
