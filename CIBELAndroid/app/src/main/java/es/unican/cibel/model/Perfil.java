package es.unican.cibel.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import es.unican.cibel.repository.db.DaoSession;
import es.unican.cibel.repository.db.ActivoDao;
import es.unican.cibel.repository.db.PerfilDao;


@Entity
public class Perfil {
    @Id
    private Long id;

    @ToMany(referencedJoinProperty = "fk_perfil")
    private List<Activo> activosAnhadidos = new ArrayList<>();

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

    public void setActivosAnhadidos(List<Activo> activosAnhadidos) {
        this.activosAnhadidos = activosAnhadidos;
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
    @Generated(hash = 642359910)
    public List<Activo> getActivosAnhadidos() {
        if (activosAnhadidos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ActivoDao targetDao = daoSession.getActivoDao();
            List<Activo> activosAnhadidosNew = targetDao
                    ._queryPerfil_ActivosAnhadidos(id);
            synchronized (this) {
                if (activosAnhadidos == null) {
                    activosAnhadidos = activosAnhadidosNew;
                }
            }
        }
        return activosAnhadidos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1355837221)
    public synchronized void resetActivosAnhadidos() {
        activosAnhadidos = null;
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

}
