package es.unican.appriegospersonales.repository.nist;

import android.util.Log;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.nist.ResultCVES;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.nist.rest.NistService;

public class NistRepository implements INistRepository {

    private final MyApplication application;
    private final DaoSession daoSession;

    public NistRepository(MyApplication application) {
        this.application = application;
        this.daoSession = application.getDaoSession();
    }
    @Override
    public ResultCVES getVulnerabilidades(String modelo) {
        ResultCVES response = NistService.getVulnerabilidades(modelo);
        //persistToDBVulnerabilidades(modelo, response);
        return response;
    }

//    private void persistToDBVulnerabilidades(String modelo, ResultCVES resultCVES) {
//        // Pensar mejor
//        if (resultCVES != null) {
//            for (Vulnerability v : resultCVES.getVulnerabilities()) {
//                CVE cve = v.getCve();
//                Log.d("NistRepository", cve.toString());
//                ActivoDao activoDao = daoSession.getActivoDao();
//                Activo a = activoDao.queryBuilder().where
//                        (ActivoDao.Properties.Nombre.like(modelo)).unique();
//                a.getVulnerabilidades().add(cve);
//                activoDao.update(a);
//            }
//        }
//    }
}
