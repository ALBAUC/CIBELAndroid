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
import es.unican.appriegospersonales.repository.db.RiesgoDao;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.ElementoDigitalDao;

@Entity
public class Categoria {
    @SerializedName("id")
    @NonNull
    @Id
    private Long idCategoria;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("riesgos")
    @ToMany
    @JoinEntity(
            entity = JoinCategoriasWithRiesgos.class,
            sourceProperty = "idCategoria",
            targetProperty = "idRiesgo"
    )
    private List<Riesgo> riesgos;

    @ToMany(referencedJoinProperty = "fk_categoria")
    private List<ElementoDigital> elementsCat;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1170305099)
    private transient CategoriaDao myDao;

    public Categoria() {
        idCategoria = 0L;
        riesgos = new ArrayList<>();
    }

    @Generated(hash = 1008510813)
    public Categoria(@NonNull Long idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
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

    public void setRiesgos(List<Riesgo> riesgos) {
        this.riesgos = riesgos;
    }

    public void setElementsCat(List<ElementoDigital> elementsCat) {
        this.elementsCat = elementsCat;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 250290145)
    public List<Riesgo> getRiesgos() {
        if (riesgos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RiesgoDao targetDao = daoSession.getRiesgoDao();
            List<Riesgo> riesgosNew = targetDao
                    ._queryCategoria_Riesgos(idCategoria);
            synchronized (this) {
                if (riesgos == null) {
                    riesgos = riesgosNew;
                }
            }
        }
        return riesgos;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 360708629)
    public synchronized void resetRiesgos() {
        riesgos = null;
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

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 829587735)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCategoriaDao() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return idCategoria.equals(categoria.idCategoria) && nombre.equals(categoria.nombre) && riesgos.equals(categoria.riesgos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria, nombre, riesgos);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                ", riesgos=" + riesgos +
                '}';
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1803356079)
    public List<ElementoDigital> getElementsCat() {
        if (elementsCat == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ElementoDigitalDao targetDao = daoSession.getElementoDigitalDao();
            List<ElementoDigital> elementsCatNew = targetDao._queryCategoria_ElementsCat(idCategoria);
            synchronized (this) {
                if (elementsCat == null) {
                    elementsCat = elementsCatNew;
                }
            }
        }
        return elementsCat;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1774734976)
    public synchronized void resetElementsCat() {
        elementsCat = null;
    }
}
