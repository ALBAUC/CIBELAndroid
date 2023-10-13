package es.unican.appriegospersonales.model.nist;

import java.util.List;

public class CVE {

    private String id;

    private List<DescripcionCVE> descriptions;

    private Metricas metrics;

    public List<DescripcionCVE> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<DescripcionCVE> descriptions) {
        this.descriptions = descriptions;
    }

    public Metricas getMetrics() {
        return metrics;
    }

    public void setMetrics(Metricas metrics) {
        this.metrics = metrics;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CVE{" +
                "descriptions=" + descriptions +
                ", metrics=" + metrics +
                '}';
    }
}
