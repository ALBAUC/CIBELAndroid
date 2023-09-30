package es.unican.appriegospersonales.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.ControlDao;
import es.unican.appriegospersonales.repository.db.AmenazaDao;

@SuppressLint("ParcelCreator")
@Entity
public class Amenaza implements Parcelable {
    @SerializedName("id")
    @NonNull
    @Id
    private Long idAmenaza;

    private String nombre;

    private String descripcion;

    @ToMany
    @JoinEntity(
            entity = JoinAmenazasWithControles.class,
            sourceProperty = "idAmenaza",
            targetProperty = "idControl"
    )
    private List<Control> controles;

    private String tipo;

    @Transient
    private boolean expanded = false;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 16682177)
    private transient AmenazaDao myDao;

    public Amenaza() {
        idAmenaza = 0L;
        controles = new ArrayList<>();
    }

    @Generated(hash = 543568064)
    public Amenaza(@NonNull Long idAmenaza, String nombre, String descripcion, String tipo) {
        this.idAmenaza = idAmenaza;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    @NonNull
    public Long getIdAmenaza() {
        return idAmenaza;
    }

    public void setIdAmenaza(@NonNull Long idAmenaza) {
        this.idAmenaza = idAmenaza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setControles(List<Control> controles) {
        this.controles = controles;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
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
        if (o == null || getClass() != o.getClass()) return false;
        Amenaza amenaza = (Amenaza) o;
        return idAmenaza.equals(amenaza.idAmenaza) && Objects.equals(nombre, amenaza.nombre) && Objects.equals(descripcion, amenaza.descripcion) && Objects.equals(controles, amenaza.controles) && Objects.equals(tipo, amenaza.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAmenaza, nombre, descripcion, controles, tipo);
    }

    @Override
    public String toString() {
        return "Riesgo{" +
                "idRiesgo=" + idAmenaza +
                ", nombre='" + nombre + '\'' +
                ", controles=" + controles +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(idAmenaza);
        parcel.writeString(nombre);
        parcel.writeString(descripcion);
        parcel.writeList(controles);
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1833291491)
    public List<Control> getControles() {
        if (controles == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ControlDao targetDao = daoSession.getControlDao();
            List<Control> controlesNew = targetDao._queryAmenaza_Controles(idAmenaza);
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
    @Generated(hash = 446613322)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAmenazaDao() : null;
    }

}
