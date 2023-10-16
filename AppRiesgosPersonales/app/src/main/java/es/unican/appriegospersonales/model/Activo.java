package es.unican.appriegospersonales.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;
import java.util.Objects;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.CategoriaDao;
import es.unican.appriegospersonales.repository.db.ActivoDao;
import es.unican.appriegospersonales.repository.db.TipoDao;
import es.unican.appriegospersonales.repository.db.VulnerabilidadDao;
import es.unican.appriesgospersonales.R;

@SuppressLint("ParcelCreator")
@Entity
public class Activo implements Parcelable {

    @SerializedName("id")
    @NonNull
    @Id
    private Long idActivo;

    private String nombre;

    private String icono;

    @ToOne(joinProperty = "fk_categoria")
    private Categoria categoria;

    private long fk_categoria;

    private Long fk_perfil;

    @ToOne(joinProperty = "fk_tipo")
    private Tipo tipo;
    private long fk_tipo;

    @ToMany
    @JoinEntity(
            entity = JoinActivosWithVulnerabilidades.class,
            sourceProperty = "activoId",
            targetProperty = "vulnerabilidadId"
    )
    private List<Vulnerabilidad> vulnerabilidades;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1317875841)
    private transient ActivoDao myDao;

    @Generated(hash = 446666093)
    public Activo(@NonNull Long idActivo, String nombre, String icono, long fk_categoria, Long fk_perfil, long fk_tipo) {
        this.idActivo = idActivo;
        this.nombre = nombre;
        this.icono = icono;
        this.fk_categoria = fk_categoria;
        this.fk_perfil = fk_perfil;
        this.fk_tipo = fk_tipo;
    }

    @Generated(hash = 315079783)
    public Activo() {
    }

    @Generated(hash = 1426606615)
    private transient Long categoria__resolvedKey;

    @Generated(hash = 606252662)
    private transient Long tipo__resolvedKey;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Long getFk_categoria() {
        return fk_categoria;
    }

    public void setFk_categoria(Long fk_categoria) {
        this.fk_categoria = fk_categoria;
    }

    @NonNull
    public Long getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(@NonNull Long idActivo) {
        this.idActivo = idActivo;
    }

    public Categoria getCategoriaTrampa() {
        return categoria;
    }

    public Tipo getTipoTrampa() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Activo{" +
                "idActivo=" + idActivo +
                ", nombre='" + nombre + '\'' +
                ", icono='" + icono + '\'' +
                ", categoria=" + categoria +
                ", fk_categoria=" + fk_categoria +
                ", fk_perfil=" + fk_perfil +
                ", tipo=" + tipo +
                ", vulnerabilidades=" + vulnerabilidades +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(idActivo);
        parcel.writeString(nombre);
        parcel.writeString(icono);
        parcel.writeValue(categoria);
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
        Activo activo = (Activo) o;
        return idActivo.equals(activo.idActivo) && Objects.equals(nombre, activo.nombre) && Objects.equals(icono, activo.icono) && Objects.equals(categoria, activo.categoria) && Objects.equals(tipo, activo.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActivo, nombre, icono, categoria, tipo);
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1256633020)
    public Categoria getCategoria() {
        long __key = this.fk_categoria;
        if (categoria__resolvedKey == null || !categoria__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CategoriaDao targetDao = daoSession.getCategoriaDao();
            Categoria categoriaNew = targetDao.load(__key);
            synchronized (this) {
                categoria = categoriaNew;
                categoria__resolvedKey = __key;
            }
        }
        return categoria;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1862388064)
    public void setCategoria(@NonNull Categoria categoria) {
        if (categoria == null) {
            throw new DaoException("To-one property 'fk_categoria' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.categoria = categoria;
            fk_categoria = categoria.getIdCategoria();
            categoria__resolvedKey = fk_categoria;
        }
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
    @Generated(hash = 1926086416)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getActivoDao() : null;
    }

    public void setFk_categoria(long fk_categoria) {
        this.fk_categoria = fk_categoria;
    }

    public void setFk_perfil(long fk_perfil) {
        this.fk_perfil = fk_perfil;
    }

    public long getFk_tipo() {
        return this.fk_tipo;
    }

    public void setFk_tipo(long fk_tipo) {
        this.fk_tipo = fk_tipo;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1348072207)
    public Tipo getTipo() {
        long __key = this.fk_tipo;
        if (tipo__resolvedKey == null || !tipo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TipoDao targetDao = daoSession.getTipoDao();
            Tipo tipoNew = targetDao.load(__key);
            synchronized (this) {
                tipo = tipoNew;
                tipo__resolvedKey = __key;
            }
        }
        return tipo;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1296182171)
    public void setTipo(@NonNull Tipo tipo) {
        if (tipo == null) {
            throw new DaoException("To-one property 'fk_tipo' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.tipo = tipo;
            fk_tipo = tipo.getIdTipo();
            tipo__resolvedKey = fk_tipo;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 842821075)
    public List<Vulnerabilidad> getVulnerabilidades() {
        if (vulnerabilidades == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            VulnerabilidadDao targetDao = daoSession.getVulnerabilidadDao();
            List<Vulnerabilidad> vulnerabilidadesNew = targetDao._queryActivo_Vulnerabilidades(idActivo);
            synchronized (this) {
                if (vulnerabilidades == null) {
                    vulnerabilidades = vulnerabilidadesNew;
                }
            }
        }
        return vulnerabilidades;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1061741557)
    public synchronized void resetVulnerabilidades() {
        vulnerabilidades = null;
    }

    public int calcularIndiceRiesgo() {
        int totalRiesgo = calcularTotalRiesgo();
        int indiceRiesgo = 0;

        if (totalRiesgo >= 9) {
            indiceRiesgo = 3;
        } else if (totalRiesgo >= 5) {
            indiceRiesgo = 2;
        } else if (totalRiesgo >= 2) {
            indiceRiesgo = 1;
        }

        return indiceRiesgo;
    }

    public int calcularTotalRiesgo() {
        List<Vulnerabilidad> vulnerabilidades = getVulnerabilidades();
        int totalRiesgo = 0;

        for (Vulnerabilidad v : vulnerabilidades) {
            String severidad = v.getBaseSeverity();
            int factorSeveridad = 0;

            // Asigna un valor a la severidad
            if (severidad.equals(Vulnerabilidad.SEVERITY_C)) {
                factorSeveridad = 3;  // Asigna el mayor valor
            } else if (severidad.equals(Vulnerabilidad.SEVERITY_H)) {
                factorSeveridad = 2;
            } else if (severidad.equals(Vulnerabilidad.SEVERITY_M)) {
                factorSeveridad = 1;
            } else if (severidad.equals(Vulnerabilidad.SEVERITY_L)) {
                factorSeveridad = 0;  // Asigna el menor valor
            }

            totalRiesgo += factorSeveridad;
        }
        return totalRiesgo;
    }
}
