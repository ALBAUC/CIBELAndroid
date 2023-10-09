package es.unican.appriegospersonales.model.nist;

import com.google.gson.annotations.SerializedName;

public class DescripcionCVE {

    @SerializedName("lang")
    private String idioma;
    
    @SerializedName("value")
    private String descripcion;

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "DescripcionCVE{" +
                "idioma='" + idioma + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
