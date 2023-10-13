package es.unican.appriegospersonales.model.nist;

public class MetricaCVSSV3 {

    private DataCVSS cvssData;

    public DataCVSS getCvssData() {
        return cvssData;
    }

    public void setCvssData(DataCVSS cvssData) {
        this.cvssData = cvssData;
    }

    @Override
    public String toString() {
        return "MetricaCVSSV3{" +
                "cvssData=" + cvssData +
                '}';
    }
}
