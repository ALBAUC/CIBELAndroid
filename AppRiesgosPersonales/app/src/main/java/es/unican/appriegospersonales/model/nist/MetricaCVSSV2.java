package es.unican.appriegospersonales.model.nist;

public class MetricaCVSSV2 {

    private DataCVSS cvssData;

    private String baseSeverity;

    private double exploitabilityScore;

    private double impactScore;

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

    public double getExploitabilityScore() {
        return exploitabilityScore;
    }

    public void setExploitabilityScore(double exploitabilityScore) {
        this.exploitabilityScore = exploitabilityScore;
    }

    public double getImpactScore() {
        return impactScore;
    }

    public void setImpactScore(double impactScore) {
        this.impactScore = impactScore;
    }

    @Override
    public String toString() {
        return "MetricaCVSSV2{" +
                "cvssData=" + cvssData +
                ", baseSeverity='" + baseSeverity + '\'' +
                ", exploitabilityScore=" + exploitabilityScore +
                ", impactScore=" + impactScore +
                '}';
    }
}
