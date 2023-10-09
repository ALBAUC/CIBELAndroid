package es.unican.appriegospersonales.model.nist;

import java.util.List;

public class CVE {

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

    @Override
    public String toString() {
        return "CVE{" +
                "descriptions=" + descriptions +
                ", metrics=" + metrics +
                '}';
    }
}
