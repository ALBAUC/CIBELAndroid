package es.unican.appriegospersonales.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class EcoDatos {
    @Id(autoincrement = true)
    private Long id;
    private int durabilidad;
    private int reparabilidad;
    private int reciclabilidad;
    private int efClimatica;
    private int efRecursos;
    private int ecoPuntuacion;
    @Generated(hash = 1252430566)
    public EcoDatos(Long id, int durabilidad, int reparabilidad, int reciclabilidad,
            int efClimatica, int efRecursos, int ecoPuntuacion) {
        this.id = id;
        this.durabilidad = durabilidad;
        this.reparabilidad = reparabilidad;
        this.reciclabilidad = reciclabilidad;
        this.efClimatica = efClimatica;
        this.efRecursos = efRecursos;
        this.ecoPuntuacion = ecoPuntuacion;
    }
    @Generated(hash = 1392999646)
    public EcoDatos() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getDurabilidad() {
        return this.durabilidad;
    }
    public void setDurabilidad(int durabilidad) {
        this.durabilidad = durabilidad;
    }
    public int getReparabilidad() {
        return this.reparabilidad;
    }
    public void setReparabilidad(int reparabilidad) {
        this.reparabilidad = reparabilidad;
    }
    public int getReciclabilidad() {
        return this.reciclabilidad;
    }
    public void setReciclabilidad(int reciclabilidad) {
        this.reciclabilidad = reciclabilidad;
    }
    public int getEfClimatica() {
        return this.efClimatica;
    }
    public void setEfClimatica(int efClimatica) {
        this.efClimatica = efClimatica;
    }
    public int getEfRecursos() {
        return this.efRecursos;
    }
    public void setEfRecursos(int efRecursos) {
        this.efRecursos = efRecursos;
    }
    public int getEcoPuntuacion() {
        return this.ecoPuntuacion;
    }
    public void setEcoPuntuacion(int ecoPuntuacion) {
        this.ecoPuntuacion = ecoPuntuacion;
    }

    @Override
    public String toString() {
        return "EcoDatos{" +
                "durabilidad=" + durabilidad +
                ", reparabilidad=" + reparabilidad +
                ", reciclabilidad=" + reciclabilidad +
                ", efClimatica=" + efClimatica +
                ", efRecursos=" + efRecursos +
                ", ecoPuntuacion=" + ecoPuntuacion +
                '}';
    }
}
