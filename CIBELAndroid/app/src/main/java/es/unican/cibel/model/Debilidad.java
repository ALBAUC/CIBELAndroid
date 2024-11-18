package es.unican.cibel.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Debilidad {
    @Id
    private String idCWE;
    private String nombre;
    private String descripcion;
    private String nombre_en;
    private String descripcion_en;
    @Generated(hash = 440486188)
    public Debilidad(String idCWE, String nombre, String descripcion,
            String nombre_en, String descripcion_en) {
        this.idCWE = idCWE;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombre_en = nombre_en;
        this.descripcion_en = descripcion_en;
    }
    @Generated(hash = 1071281392)
    public Debilidad() {
    }
    public String getIdCWE() {
        return this.idCWE;
    }
    public void setIdCWE(String idCWE) {
        this.idCWE = idCWE;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getNombre_en() {
        return this.nombre_en;
    }
    public void setNombre_en(String nombre_en) {
        this.nombre_en = nombre_en;
    }
    public String getDescripcion_en() {
        return this.descripcion_en;
    }
    public void setDescripcion_en(String descripcion_en) {
        this.descripcion_en = descripcion_en;
    }

    @Override
    public String toString() {
        return "Debilidad{" +
                "idCWE='" + idCWE + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", nombre_en='" + nombre_en + '\'' +
                ", descripcion_en='" + descripcion_en + '\'' +
                '}';
    }
}
