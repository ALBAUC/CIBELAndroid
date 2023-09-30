package es.unican.appriegospersonales.repository.rest;

import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.DispositivoIoT;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *  Acceso a CIBEL API usando Retrofit.
 */
public interface CibelAPI {

    @GET("activos")
    Call<Activo[]> activos(@Query("categoria") String categoria);

    @GET("activos/apps")
    Call<Aplicacion[]> aplicaciones(@Query("categoria") String categoria);

    @GET("activos/apps/{nombre}")
    Call<Aplicacion> aplicacion(@Path("nombre") String nombre);

    @GET("activos/dispositivos")
    Call<DispositivoIoT[]> dispositivos(@Query("categoria") String categoria);

    @GET("activos/dispositivos/{nombre}")
    Call<DispositivoIoT> dispositivo(@Path("nombre") String nombre);

    @GET("amenazas")
    Call<Amenaza[]> riesgos();

    @GET("amenazas/apps")
    Call<Amenaza[]> riesgosDeApps();

    @GET("amenazas/dispositivos")
    Call<Amenaza[]> riesgosDeDispositivos();

    @GET("controles")
    Call<Control[]> controles();

    @GET("controles/apps")
    Call<Control[]> controlesDeApps();

    @GET("controles/dispositivos")
    Call<Control[]> controlesDeDispositivos();

    @GET("categorias")
    Call<Categoria[]> categorias();

    @GET("categorias/apps")
    Call<Categoria[]> categoriasDeApps();

    @GET("categorias/dispositivos")
    Call<Categoria[]> categoriasDeDispositivos();
}
