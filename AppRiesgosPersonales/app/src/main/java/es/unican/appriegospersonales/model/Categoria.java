package es.unican.appriegospersonales.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.ControlDao;
import es.unican.appriegospersonales.repository.db.CategoriaDao;

@Entity
public class Categoria {
    @SerializedName("id")
    @NonNull
    @Id
    private Long idCategoria;

    private String nombre;

    private String tipo;

    @ToMany
    @JoinEntity(
            entity = JoinCategriasWithControles.class,
            sourceProperty = "idCategoria",
            targetProperty = "idControl"
    )
    private List<Control> controles;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1170305099)
    private transient CategoriaDao myDao;

    public Categoria() {
        idCategoria = 0L;
        controles = new ArrayList<>();
    }

    @Generated(hash = 2079507152)
    public Categoria(@NonNull Long idCategoria, String nombre, String tipo) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    @NonNull
    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(@NonNull Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setControles(List<Control> controles) {
        this.controles = controles;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return idCategoria.equals(categoria.idCategoria) && nombre.equals(categoria.nombre) && controles.equals(categoria.controles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria, nombre, controles);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                ", controles=" + controles +
                '}';
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1493170162)
    public List<Control> getControles() {
        if (controles == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ControlDao targetDao = daoSession.getControlDao();
            List<Control> controlesNew = targetDao._queryCategoria_Controles(idCategoria);
            synchronized (this) {
                if (controles == null) {
                    controles = controlesNew;
                }
            }
        }
        return controles;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 787308186)
    public synchronized void resetControles() {
        controles = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 829587735)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCategoriaDao() : null;
    }

}
