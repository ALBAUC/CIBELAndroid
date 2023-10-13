package es.unican.appriegospersonales.model.nist;


public class DataCVSS {

    private String baseSeverity;

    private String confidentialityImpact;

    private String integrityImpact;

    private String availabilityImpact;

    private double baseScore;

    public String getBaseSeverity() {
        return baseSeverity;
    }

    public void setBaseSeverity(String baseSeverity) {
        this.baseSeverity = baseSeverity;
    }

    public String getConfidentialityImpact() {
        return confidentialityImpact;
    }

    public void setConfidentialityImpact(String confidentialityImpact) {
        this.confidentialityImpact = confidentialityImpact;
    }

    public String getIntegrityImpact() {
        return integrityImpact;
    }

    public void setIntegrityImpact(String integrityImpact) {
        this.integrityImpact = integrityImpact;
    }

    public String getAvailabilityImpact() {
        return availabilityImpact;
    }

    public void setAvailabilityImpact(String availabilityImpact) {
        this.availabilityImpact = availabilityImpact;
    }

    public double getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(double baseScore) {
        this.baseScore = baseScore;
    }

    @Override
    public String toString() {
        return "DataCVSS{" +
                "baseSeverity='" + baseSeverity + '\'' +
                ", confidentialityImpact='" + confidentialityImpact + '\'' +
                ", integrityImpact='" + integrityImpact + '\'' +
                ", availabilityImpact='" + availabilityImpact + '\'' +
                ", baseScore=" + baseScore +
                '}';
    }
}
