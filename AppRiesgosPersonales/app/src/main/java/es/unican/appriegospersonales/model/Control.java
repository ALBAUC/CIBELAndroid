package es.unican.appriegospersonales.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
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
import es.unican.appriegospersonales.repository.db.AmenazaDao;
import es.unican.appriegospersonales.repository.db.ControlDao;

@SuppressLint("ParcelCreator")
@Entity
public class Control implements Parcelable {
    @SerializedName("id")
    @NonNull
    @Id
    private Long idControl;

    private String nombre;

    private String descripcion;

    private String tipo;

    @Transient
    private boolean expanded = false;

    @Expose(deserialize = false)
    @ToMany
    @JoinEntity(
            entity = JoinAmenazasWithControles.class,
            sourceProperty = "idControl",
            targetProperty = "idAmenaza"
    )
    private List<Amenaza> mitigaAmenazas;

    private Long fk_perfil;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 695321196)
    private transient ControlDao myDao;

    public Control() {
        idControl = 0L;
        mitigaAmenazas = new ArrayList<>();
    }

    @Generated(hash = 547181294)
    public Control(@NonNull Long idControl, String nombre, String descripcion, String tipo, Long fk_perfil) {
        this.idControl = idControl;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fk_perfil = fk_perfil;
    }

    @NonNull
    public Long getIdControl() {
        return idControl;
    }

    public void setIdControl(@NonNull Long idControl) {
        this.idControl = idControl;
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

    public void setMitigaAmenazas(List<Amenaza> mitigaAmenazas) {
        this.mitigaAmenazas = mitigaAmenazas;
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
    public String toString() {
        return "Control{" +
                "idControl=" + idControl +
                '}';
    }

    public boolean getExpanded() {
        return this.expanded;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(idControl);
        parcel.writeString(nombre);
        parcel.writeString(descripcion);
        parcel.writeList(mitigaAmenazas);
    }

    public Long getFk_perfil() {
        return this.fk_perfil;
    }

    public void setFk_perfil(Long fk_perfil) {
        this.fk_perfil = fk_perfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Control control = (Control) o;
        return idControl.equals(control.idControl) && Objects.equals(nombre, control.nombre) && Objects.equals(descripcion, control.descripcion) && Objects.equals(tipo, control.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idControl, nombre, descripcion, tipo);
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 762480350)
    public List<Amenaza> getMitigaAmenazas() {
        if (mitigaAmenazas == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AmenazaDao targetDao = daoSession.getAmenazaDao();
            List<Amenaza> mitigaAmenazasNew = targetDao._queryControl_MitigaAmenazas(idControl);
            synchronized (this) {
                if (mitigaAmenazas == null) {
                    mitigaAmenazas = mitigaAmenazasNew;
                }
            }
        }
        return mitigaAmenazas;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 962568000)
    public synchronized void resetMitigaAmenazas() {
        mitigaAmenazas = null;
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
    @Generated(hash = 1336688166)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getControlDao() : null;
    }
}
