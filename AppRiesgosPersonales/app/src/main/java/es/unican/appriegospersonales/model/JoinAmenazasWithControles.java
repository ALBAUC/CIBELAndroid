package es.unican.appriegospersonales.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class JoinAmenazasWithControles {
    @Id(autoincrement = true)
    private Long id;
    private Long idAmenaza;
    private Long idControl;

    @Generated(hash = 1614929054)
    public JoinAmenazasWithControles(Long id, Long idAmenaza, Long idControl) {
        this.id = id;
        this.idAmenaza = idAmenaza;
        this.idControl = idControl;
    }

    @Generated(hash = 758728844)
    public JoinAmenazasWithControles() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAmenaza() {
        return idAmenaza;
    }

    public void setIdAmenaza(Long idAmenaza) {
        this.idAmenaza = idAmenaza;
    }

    public Long getIdControl() {
        return idControl;
    }

    public void setIdControl(Long idControl) {
        this.idControl = idControl;
    }
}
