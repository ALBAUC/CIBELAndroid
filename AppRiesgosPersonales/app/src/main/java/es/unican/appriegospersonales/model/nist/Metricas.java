package es.unican.appriegospersonales.model.nist;


import java.util.List;

public class Metricas {

    private List<MetricaCVSSV2> cvssMetricV2;

    public List<MetricaCVSSV2> getCvssMetricV2() {
        return cvssMetricV2;
    }

    public void setCvssMetricV2(List<MetricaCVSSV2> cvssMetricV2) {
        this.cvssMetricV2 = cvssMetricV2;
    }

    @Override
    public String toString() {
        return "Metricas{" +
                "cvssMetricV2=" + cvssMetricV2 +
                '}';
    }
}
