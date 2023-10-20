package es.unican.appriegospersonales.repository.cibel.rest;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Tipo;
import es.unican.appriegospersonales.model.Vulnerabilidad;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *  Acceso a CIBEL API usando Retrofit.
 */
public interface CibelAPI {

    @GET("activos")
    Call<Activo[]> activos(@Query("categoria") String categoria);

    @GET("amenazas")
    Call<Amenaza[]> riesgos();

    @GET("controles")
    Call<Control[]> controles();

    @GET("categorias")
    Call<Categoria[]> categorias();

    @GET("tipos")
    Call<Tipo[]> tipos();

    @GET("vulnerabilidades")
    Call<Vulnerabilidad[]> vulnerabilidades();
}
