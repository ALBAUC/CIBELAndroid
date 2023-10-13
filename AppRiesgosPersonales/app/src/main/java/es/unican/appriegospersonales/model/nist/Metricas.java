package es.unican.appriegospersonales.model.nist;


import java.util.List;

public class Metricas {

    private List<MetricaCVSSV2> cvssMetricV2;
    private List<MetricaCVSSV3> cvssMetricV30;
    private List<MetricaCVSSV3> cvssMetricV31;

    public List<MetricaCVSSV2> getCvssMetricV2() {
        return cvssMetricV2;
    }

    public void setCvssMetricV2(List<MetricaCVSSV2> cvssMetricV2) {
        this.cvssMetricV2 = cvssMetricV2;
    }

    public List<MetricaCVSSV3> getCvssMetricV30() {
        return cvssMetricV30;
    }

    public void setCvssMetricV30(List<MetricaCVSSV3> cvssMetricV30) {
        this.cvssMetricV30 = cvssMetricV30;
    }

    public List<MetricaCVSSV3> getCvssMetricV31() {
        return cvssMetricV31;
    }

    public void setCvssMetricV31(List<MetricaCVSSV3> cvssMetricV31) {
        this.cvssMetricV31 = cvssMetricV31;
    }

    @Override
    public String toString() {
        return "Metricas{" +
                "cvssMetricV2=" + cvssMetricV2 +
                ", cvssMetricV30=" + cvssMetricV30 +
                ", cvssMetricV31=" + cvssMetricV31 +
                '}';
    }
}
