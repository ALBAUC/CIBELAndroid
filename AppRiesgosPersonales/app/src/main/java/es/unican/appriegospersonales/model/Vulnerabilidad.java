package es.unican.appriegospersonales.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Objects;

@Entity
public class Vulnerabilidad {

    @Id
    private String idCVE;
    private String descripcion;
    private String confidentialityImpact;
    private String integrityImpact;
    private String availabilityImpact;
    private double baseScore;
    private String baseSeverity;

    @Generated(hash = 325331057)
    public Vulnerabilidad(String idCVE, String descripcion,
            String confidentialityImpact, String integrityImpact,
            String availabilityImpact, double baseScore, String baseSeverity) {
        this.idCVE = idCVE;
        this.descripcion = descripcion;
        this.confidentialityImpact = confidentialityImpact;
        this.integrityImpact = integrityImpact;
        this.availabilityImpact = availabilityImpact;
        this.baseScore = baseScore;
        this.baseSeverity = baseSeverity;
    }
    @Generated(hash = 1462448819)
    public Vulnerabilidad() {
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getConfidentialityImpact() {
        return this.confidentialityImpact;
    }
    public void setConfidentialityImpact(String confidentialityImpact) {
        this.confidentialityImpact = confidentialityImpact;
    }
    public String getIntegrityImpact() {
        return this.integrityImpact;
    }
    public void setIntegrityImpact(String integrityImpact) {
        this.integrityImpact = integrityImpact;
    }
    public String getAvailabilityImpact() {
        return this.availabilityImpact;
    }
    public void setAvailabilityImpact(String availabilityImpact) {
        this.availabilityImpact = availabilityImpact;
    }
    public double getBaseScore() {
        return this.baseScore;
    }
    public void setBaseScore(double baseScore) {
        this.baseScore = baseScore;
    }
    public String getBaseSeverity() {
        return this.baseSeverity;
    }
    public void setBaseSeverity(String baseSeverity) {
        this.baseSeverity = baseSeverity;
    }
    public String getIdCVE() {
        return this.idCVE;
    }
    public void setIdCVE(String idCVE) {
        this.idCVE = idCVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vulnerabilidad that = (Vulnerabilidad) o;
        return Objects.equals(idCVE, that.idCVE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCVE);
    }

    @Override
    public String toString() {
        return "Vulnerabilidad{" +
                ", idCVE='" + idCVE + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", confidentialityImpact='" + confidentialityImpact + '\'' +
                ", integrityImpact='" + integrityImpact + '\'' +
                ", availabilityImpact='" + availabilityImpact + '\'' +
                ", baseScore=" + baseScore +
                ", baseSeverity='" + baseSeverity + '\'' +
                '}';
    }
}
