package es.unican.appriegospersonales.repository.nist;

import es.unican.appriegospersonales.model.nist.ResultCVES;

public interface INistRepository {
    /**
     * Solicita vulnerabilidades de forma sincrona.
     * Este metodo retorna un objeto ResultCVES que contiene la lista de
     * vulnerabilidades asociadas al modelo indicado. Persiste las vulnerabilidades
     * en la base de datos local.
     * @param modelo
     * @return objeto ResultCVES que contiene la lista de
     * vulnerabilidades asociadas al modelo indicado.
     */
    ResultCVES getVulnerabilidades(String modelo);
}
