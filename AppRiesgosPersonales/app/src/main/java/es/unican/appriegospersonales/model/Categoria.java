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
import es.unican.appriegospersonales.repository.db.CategoriaDao;

@Entity
public class Categoria {
    @SerializedName("id")
    @NonNull
    @Id
    private Long idCategoria;

    private String nombre;

    @Generated(hash = 1008510813)
    public Categoria(@NonNull Long idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    @Generated(hash = 577285458)
    public Categoria() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return idCategoria.equals(categoria.idCategoria) && nombre.equals(categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria, nombre);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre +
                '}';
    }

    public Long getIdCategoria() {
        return this.idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
