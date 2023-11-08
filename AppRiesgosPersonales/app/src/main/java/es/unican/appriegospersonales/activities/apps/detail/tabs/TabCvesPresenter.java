package es.unican.appriegospersonales.activities.apps.detail.tabs;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Vulnerabilidad;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.VulnerabilidadDao;

public class TabCvesPresenter implements ITabCvesContract.Presenter{
    private final ITabCvesContract.View view;
    private VulnerabilidadDao vulnerabilidadDao;
    private Activo activo;

    public TabCvesPresenter(Activo activo, TabCvesView view) {
        this.activo = activo;
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        vulnerabilidadDao = daoSession.getVulnerabilidadDao();
    }

    @Override
    public List<Vulnerabilidad> getAssetCves() {
        List<Vulnerabilidad> assetCves = vulnerabilidadDao._queryActivo_Vulnerabilidades(activo.getIdActivo());
        return assetCves;
    }

    @Override
    public List<Vulnerabilidad> getAssetCvesOrdenadorPorFecha() {
        List<Vulnerabilidad> assetCves = getAssetCves();
        Collections.sort(assetCves, new Comparator<Vulnerabilidad>() {
            @Override
            public int compare(Vulnerabilidad cve1, Vulnerabilidad cve2) {
                // Extraer los años y los identificadores
                String[] parts1 = cve1.getIdCVE().split("-");
                String[] parts2 = cve2.getIdCVE().split("-");

                int year1 = Integer.parseInt(parts1[1]);
                int year2 = Integer.parseInt(parts2[1]);

                int id1 = Integer.parseInt(parts1[2]);
                int id2 = Integer.parseInt(parts2[2]);

                // Comparar primero por año, y luego por identificador dentro del mismo año
                if (year1 != year2) {
                    return Integer.compare(year2, year1);
                } else {
                    return Integer.compare(id2, id1);
                }
            }
        });

        return assetCves;
    }

    @Override
    public List<PieEntry> getEntries() {
        int numCritical = 0;
        int numHigh = 0;
        int numMedium = 0;
        int numLow = 0;
        for (Vulnerabilidad v : getAssetCves()) {
            String baseSeverity = v.getBaseSeverity();
            if (baseSeverity != null) {
                if (baseSeverity.equals(Vulnerabilidad.SEVERITY_C)) {
                    numCritical++;
                } else if (baseSeverity.equals(Vulnerabilidad.SEVERITY_H)) {
                    numHigh++;
                } else if (baseSeverity.equals(Vulnerabilidad.SEVERITY_M)) {
                    numMedium++;
                } else if (baseSeverity.equals(Vulnerabilidad.SEVERITY_L)) {
                    numLow++;
                }
            }
        }
        ArrayList pieEntries = new ArrayList();
        pieEntries.add(new PieEntry(numCritical, Vulnerabilidad.ES_SEVERITY_C));
        pieEntries.add(new PieEntry(numHigh, Vulnerabilidad.ES_SEVERITY_H));
        pieEntries.add(new PieEntry(numMedium, Vulnerabilidad.ES_SEVERITY_M));
        pieEntries.add(new PieEntry(numLow, Vulnerabilidad.ES_SEVERITY_L));
        return pieEntries;
    }
}
