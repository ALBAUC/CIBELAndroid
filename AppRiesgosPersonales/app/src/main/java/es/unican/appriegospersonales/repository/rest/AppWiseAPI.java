package es.unican.appriegospersonales.repository.rest;

import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.Riesgo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *  Acceso a AppWise API usando Retrofit.
 */
public interface AppWiseAPI {
    @GET("apps")
    Call<Aplicacion[]> aplicaciones(@Query("categoria") String categoria);

    @GET("apps/{nombre}")
    Call<Aplicacion> aplicacion(@Path("nombre") String nombre);

    @GET("riesgos")
    Call<Riesgo[]> riesgos();

    @GET("riesgos/{id}")
    Call<Riesgo> riesgo(@Path("id") Long id);

    @GET("controles")
    Call<Control[]> controles();

    @GET("controles/{id}")
    Call<Control> control(@Path("id") Long id);

    @GET("categorias")
    Call<Categoria[]> categorias();
}
