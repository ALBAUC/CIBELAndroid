package es.unican.cibel.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class JoinVulnerabilidadesWithDebilidades {
    @Id
    private Long id;
    private String cveId;
    private String cweId;
    @Generated(hash = 391354929)
    public JoinVulnerabilidadesWithDebilidades(Long id, String cveId,
            String cweId) {
        this.id = id;
        this.cveId = cveId;
        this.cweId = cweId;
    }
    @Generated(hash = 973512580)
    public JoinVulnerabilidadesWithDebilidades() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCveId() {
        return this.cveId;
    }
    public void setCveId(String cveId) {
        this.cveId = cveId;
    }
    public String getCweId() {
        return this.cweId;
    }
    public void setCweId(String cweId) {
        this.cweId = cweId;
    }

}
