package es.unican.appriegospersonales.repository.nist;

import android.util.Log;

import java.util.List;

import es.unican.appriegospersonales.common.MyApplication;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.JoinActivosWithVulnerabilidades;
import es.unican.appriegospersonales.model.Vulnerabilidad;
import es.unican.appriegospersonales.model.nist.CVE;
import es.unican.appriegospersonales.model.nist.DataCVSS;
import es.unican.appriegospersonales.model.nist.MetricaCVSSV2;
import es.unican.appriegospersonales.model.nist.MetricaCVSSV3;
import es.unican.appriegospersonales.model.nist.ResultCVES;
import es.unican.appriegospersonales.model.nist.Vulnerability;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.JoinActivosWithVulnerabilidadesDao;
import es.unican.appriegospersonales.repository.db.VulnerabilidadDao;
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
        persistToDBVulnerabilidades(modelo, response);
        return response;
    }

    private void persistToDBVulnerabilidades(String modelo, ResultCVES resultCVES) {
        if (resultCVES != null) {
            ActivoDao activoDao = daoSession.getActivoDao();
            Activo a = activoDao.queryBuilder().where
                    (ActivoDao.Properties.Nombre.like(modelo)).unique();
            VulnerabilidadDao vulnerabilidadDao = daoSession.getVulnerabilidadDao();
            JoinActivosWithVulnerabilidadesDao avDao = daoSession.getJoinActivosWithVulnerabilidadesDao();

            // Borrar las vulnerabilidades del dispositivo
            List<JoinActivosWithVulnerabilidades> activoVul = avDao.queryBuilder().where(JoinActivosWithVulnerabilidadesDao.Properties.ActivoId.like(a.getIdActivo().toString())).list();
            avDao.deleteInTx(activoVul);

            for (Vulnerability v : resultCVES.getVulnerabilities()) {
                CVE cve = v.getCve();

                // Borrar la vulnerabilidad de la dao si ya existia
                Vulnerabilidad vBD = vulnerabilidadDao.load(cve.getId());
                if (vBD != null) {
                    vulnerabilidadDao.delete(vBD);
                }

                // Construir vulnerabilidad
                Vulnerabilidad vulnerabilidad = new Vulnerabilidad();
                vulnerabilidad.setIdCVE(cve.getId());
                if (cve.getDescriptions().size() >= 2) {
                    vulnerabilidad.setDescripcion(cve.getDescriptions().get(1).getDescripcion());
                }
                List<MetricaCVSSV2> metricasCVSSV2 = cve.getMetrics().getCvssMetricV2();
                List<MetricaCVSSV3> metricasCVSSV30 = cve.getMetrics().getCvssMetricV30();
                List<MetricaCVSSV3> metricasCVSSV31 = cve.getMetrics().getCvssMetricV31();
                if (metricasCVSSV31 != null) {
                    MetricaCVSSV3 metricaCVSSV31 = metricasCVSSV31.get(0);
                    DataCVSS dataCVSS = metricaCVSSV31.getCvssData();
                    vulnerabilidad.setConfidentialityImpact(dataCVSS.getConfidentialityImpact());
                    vulnerabilidad.setIntegrityImpact(dataCVSS.getIntegrityImpact());
                    vulnerabilidad.setAvailabilityImpact(dataCVSS.getAvailabilityImpact());
                    vulnerabilidad.setBaseScore(dataCVSS.getBaseScore());
                    vulnerabilidad.setBaseSeverity(dataCVSS.getBaseSeverity());
                }
                if (metricasCVSSV30 != null) {
                    MetricaCVSSV3 metricaCVSSV3 = metricasCVSSV30.get(0);
                    DataCVSS dataCVSS = metricaCVSSV3.getCvssData();
                    vulnerabilidad.setConfidentialityImpact(dataCVSS.getConfidentialityImpact());
                    vulnerabilidad.setIntegrityImpact(dataCVSS.getIntegrityImpact());
                    vulnerabilidad.setAvailabilityImpact(dataCVSS.getAvailabilityImpact());
                    vulnerabilidad.setBaseScore(dataCVSS.getBaseScore());
                    vulnerabilidad.setBaseSeverity(dataCVSS.getBaseSeverity());
                } else if (metricasCVSSV2 != null) {
                    MetricaCVSSV2 metricaCVSSV2 = metricasCVSSV2.get(0);
                    DataCVSS dataCVSS = metricaCVSSV2.getCvssData();
                    vulnerabilidad.setBaseSeverity(metricaCVSSV2.getBaseSeverity());
                    vulnerabilidad.setConfidentialityImpact(dataCVSS.getConfidentialityImpact());
                    vulnerabilidad.setIntegrityImpact(dataCVSS.getIntegrityImpact());
                    vulnerabilidad.setAvailabilityImpact(dataCVSS.getAvailabilityImpact());
                    vulnerabilidad.setBaseScore(dataCVSS.getBaseScore());
                }
                // Añadir la vulnerabilidad a la dao
                vulnerabilidadDao.insert(vulnerabilidad);

                // Añadir la vulnerabilidad al dispositivo
                JoinActivosWithVulnerabilidades av = new JoinActivosWithVulnerabilidades();
                av.setVulnerabilidadId(vulnerabilidad.getIdCVE());
                av.setActivoId(a.getIdActivo());
                avDao.insert(av);
            }
        }
    }
}
