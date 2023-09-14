package es.unican.appriegospersonales.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import es.unican.appriegospersonales.repository.db.DaoSession;
import es.unican.appriegospersonales.repository.db.ControlDao;
import es.unican.appriegospersonales.repository.db.PerfilDao;
import es.unican.appriegospersonales.repository.db.ElementoDigitalDao;

@Entity
public class Perfil {
    @Id
    private Long id;

    @ToMany(referencedJoinProperty = "fk_perfil")
    private List<ElementoDigital> elementosDigitalesAnhadidos = new ArrayList<>();

    @ToMany(referencedJoinProperty = "fk_perfil")
    private List<Control> controlesAnhadidos = new ArrayList<>();

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1371249193)
    private transient PerfilDao myDao;

    @Generated(hash = 1966544863)
    public Perfil(Long id) {
        this.id = id;
    }

    @Generated(hash = 1796537861)
    public Perfil() {
    }

    public static Perfil getInstance(PerfilDao perfilDao) {
        Perfil result = perfilDao.load(1L);
        if (result == null) {
            Perfil instancia = new Perfil(1L);
            perfilDao.insert(instancia);
            result = instancia;
        }
        return result;
    }

    public void setElementosDigitalesAnhadidos(List<ElementoDigital> elementosDigitalesAnhadidos) {
        this.elementosDigitalesAnhadidos = elementosDigitalesAnhadidos;
    }

    public void setControlesAnhadidos(List<Control> controlesAnhadidos) {
        this.controlesAnhadidos = controlesAnhadidos;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 18301824)
    public List<Control> getControlesAnhadidos() {
        if (controlesAnhadidos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ControlDao targetDao = daoSession.getControlDao();
            List<Control> controlesAnhadidosNew = targetDao._queryPerfil_ControlesAnhadidos(id);
            synchronized (this) {
                if (controlesAnhadidos == null) {
                    controlesAnhadidos = controlesAnhadidosNew;
                }
            }
        }
        return controlesAnhadidos;
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
    @Generated(hash = 684696215)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPerfilDao() : null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 3856527)
    public List<ElementoDigital> getElementosDigitalesAnhadidos() {
        if (elementosDigitalesAnhadidos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ElementoDigitalDao targetDao = daoSession.getElementoDigitalDao();
            List<ElementoDigital> elementosDigitalesAnhadidosNew = targetDao
                    ._queryPerfil_ElementosDigitalesAnhadidos(id);
            synchronized (this) {
                if (elementosDigitalesAnhadidos == null) {
                    elementosDigitalesAnhadidos = elementosDigitalesAnhadidosNew;
                }
            }
        }
        return elementosDigitalesAnhadidos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1694548660)
    public synchronized void resetElementosDigitalesAnhadidos() {
        elementosDigitalesAnhadidos = null;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1568778147)
    public synchronized void resetControlesAnhadidos() {
        controlesAnhadidos = null;
    }
}
