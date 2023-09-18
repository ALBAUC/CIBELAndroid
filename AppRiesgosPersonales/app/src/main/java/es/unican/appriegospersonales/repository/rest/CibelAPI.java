package es.unican.appriegospersonales.repository.rest;

import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.DispositivoIoT;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriegospersonales.model.Riesgo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *  Acceso a CIBEL API usando Retrofit.
 */
public interface CibelAPI {

    @GET("elementosDigitales")
    Call<ElementoDigital[]> elementosDigitales(@Query("categoria") String categoria);

    @GET("elementosDigitales/apps")
    Call<Aplicacion[]> aplicaciones(@Query("categoria") String categoria);

    @GET("elementosDigitales/apps/{nombre}")
    Call<Aplicacion> aplicacion(@Path("nombre") String nombre);

    @GET("elementosDigitales/dispositivos")
    Call<DispositivoIoT[]> dispositivos(@Query("categoria") String categoria);

    @GET("elementosDigitales/dispositivos/{nombre}")
    Call<DispositivoIoT> dispositivo(@Path("nombre") String nombre);

    @GET("riesgos")
    Call<Riesgo[]> riesgos();

    @GET("riesgos/apps")
    Call<Riesgo[]> riesgosDeApps();

    @GET("riesgos/dispositivos")
    Call<Riesgo[]> riesgosDeDispositivos();

    @GET("riesgos/{id}")
    Call<Riesgo> riesgo(@Path("id") Long id);

    @GET("controles")
    Call<Control[]> controles();

    @GET("controles/apps")
    Call<Control[]> controlesDeApps();

    @GET("controles/dispositivos")
    Call<Control[]> controlesDeDispositivos();

    @GET("controles/{id}")
    Call<Control> control(@Path("id") Long id);

    @GET("categorias")
    Call<Categoria[]> categorias();

    @GET("categorias/apps")
    Call<Categoria[]> categoriasDeApps();

    @GET("categorias/dispositivos")
    Call<Categoria[]> categoriasDeDispositivos();
}
