package es.unican.appriegospersonales.model.nist;

public class MetricaCVSSV2 {

    private DataCVSS cvssData;

    private String baseSeverity;

    public DataCVSS getCvssData() {
        return cvssData;
    }

    public void setCvssData(DataCVSS cvssData) {
        this.cvssData = cvssData;
    }

    public String getBaseSeverity() {
        return baseSeverity;
    }

    public void setBaseSeverity(String baseSeverity) {
        this.baseSeverity = baseSeverity;
    }


    @Override
    public String toString() {
        return "MetricaCVSSV2{" +
                "cvssData=" + cvssData +
                ", baseSeverity='" + baseSeverity +
                '}';
    }
}
