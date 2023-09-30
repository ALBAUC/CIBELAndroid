package es.unican.appriegospersonales.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class JoinCategriasWithControles {
    @Id(autoincrement = true)
    private Long id;
    private Long idCategoria;
    private Long idControl;

    @Generated(hash = 746554498)
    public JoinCategriasWithControles(Long id, Long idCategoria, Long idControl) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.idControl = idControl;
    }

    @Generated(hash = 151560834)
    public JoinCategriasWithControles() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getIdControl() {
        return idControl;
    }

    public void setIdControl(Long idControl) {
        this.idControl = idControl;
    }
}
