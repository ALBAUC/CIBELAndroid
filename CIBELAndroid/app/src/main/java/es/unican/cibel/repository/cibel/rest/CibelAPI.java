package es.unican.cibel.repository.cibel.rest;

import es.unican.cibel.model.Activo;
import es.unican.cibel.model.Categoria;
import es.unican.cibel.model.Tipo;
import es.unican.cibel.model.Vulnerabilidad;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *  Acceso a CIBEL API usando Retrofit.
 */
public interface CibelAPI {

    @GET("dispositivos")
    Call<Activo[]> activos(@Query("categoria") String categoria);

    @GET("dispositivos/tipos")
    Call<Tipo[]> tipos();

    @GET("dispositivos/vulnerabilidades")
    Call<Vulnerabilidad[]> vulnerabilidades();
}
