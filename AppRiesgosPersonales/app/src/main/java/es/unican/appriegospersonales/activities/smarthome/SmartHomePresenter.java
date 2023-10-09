package es.unican.appriegospersonales.activities.smarthome;

import android.util.Log;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Perfil;
import es.unican.appriegospersonales.model.nist.CVE;
import es.unican.appriegospersonales.model.nist.ResultCVES;
import es.unican.appriegospersonales.model.nist.Vulnerability;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.nist.INistRepository;
import es.unican.appriegospersonales.repository.nist.NistRepository;

public class SmartHomePresenter implements ISmartHomeContract.Presenter {
    private final ISmartHomeContract.View view;
    private Perfil perfil;
    private ActivoDao activoDao;
    private INistRepository repository;

    public SmartHomePresenter(ISmartHomeContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        DaoSession daoSession = view.getMyApplication().getDaoSession();
        PerfilDao perfilDao = daoSession.getPerfilDao();
        perfil = Perfil.getInstance(perfilDao);
        activoDao = daoSession.getActivoDao();
        repository = new NistRepository(view.getMyApplication());


    }

    @Override
    public List<Activo> getActivosPerfil() {
        List<Activo> perfilAssets = activoDao._queryPerfil_ActivosAnhadidos(perfil.getId());
        return perfilAssets;
    }

    @Override
    public ArrayList getEntries() {
        List<CVE> resultCVESList = new LinkedList<>();
        for (Activo a : getActivosPerfil()) {
            ResultCVES resultCVES = repository.getVulnerabilidades(a.getNombre());
            if (resultCVES != null) {
                Log.d("SmartHomePresenter", a.getNombre() + " -> " + resultCVES.toString());
                for (Vulnerability v : resultCVES.getVulnerabilities()) {
                    resultCVESList.add(v.getCve());
                }
            }
        }
        Log.d("SmartHomePresenter", resultCVESList.toString());

        ArrayList pieEntries = new ArrayList();
        pieEntries.add(new PieEntry(2, "CRITICAL"));
        pieEntries.add(new PieEntry(10, "HIGH"));
        pieEntries.add(new PieEntry(50, "MEDIUM"));
        pieEntries.add(new PieEntry(25, "LOW"));
        return pieEntries;
    }
}
