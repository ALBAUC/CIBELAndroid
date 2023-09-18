package es.unican.appriegospersonales.repository.rest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import es.unican.appriegospersonales.common.Callback;
import es.unican.appriegospersonales.model.Aplicacion;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Control;
import es.unican.appriegospersonales.model.ElementoDigital;
import es.unican.appriegospersonales.model.Riesgo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase para acceder a los recursos del servicio REST CIBELService usando Retrofit.
 */
public class CibelService {

    private CibelService() {
    }

    private static CibelAPI api;

    private static CibelAPI getAPI() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CibelServiceConstants.getAPIURL())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = retrofit.create(CibelAPI.class);
        }
        return api;
    }

    public static final long TIMEOUT_SECONDS = 60L;

    /**
     * Descarga los elementos digitales de la API REST de forma asincrona.
     * Ejecuta la llamada en un hilo en segundo plano y notifica el resultado a
     * través del Callback proporcionado.
     * @param cb el callback que procesa la respuesta de forma asincrona
     * @param categoria se usa si se quiere que los elementos digitales se filtren por categoria,
     *                  sino dejarlo a NULL
     */
    public static void requestElementosDigitales(Callback<ElementoDigital[]> cb, String categoria) {
        final Call<ElementoDigital[]> call = getAPI().elementosDigitales(categoria);
        call.enqueue(new CallbackAdapter<>(cb));
    }

    /**
     * Descarga los elementos digitales de la API REST de forma sincrona.
     * Bloquea el hilo actual hasta que se complete la llamada y se obtenga la respuesta.
     * @param categoria se usa si se quiere que los elementos digitales se filtren por categoria,
     *                  sino dejarlo a NULL
     * @return el objeto response que contiene los elementos digitales
     */
    public static ElementoDigital[] getElementosDigitales(String categoria) {
        final Call<ElementoDigital[]> call = getAPI().elementosDigitales(categoria);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<ElementoDigital[]> runnable = new CallRunnable<>(call);
        executor.execute(runnable);

        // Espera a que acaben las tareas en background
        executor.shutdown();
        try {
            executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Si hubo algun problema, response es null
        return  runnable.getResponse();
    }

    /**
     * Descarga una aplicacion de la API REST de forma asincrona.
     * Ejecuta la llamada en un hilo en segundo plano y notifica el resultado a
     * través del Callback proporcionado.
     * @param cb el callback que procesa la respuesta de forma asincrona
     * @param nombre de la aplicacion que se quiere descargar
     */
    public static void requestAplicacion(Callback<Aplicacion> cb, String nombre) {
        final Call<Aplicacion> call = getAPI().aplicacion(nombre);
        call.enqueue(new CallbackAdapter<>(cb));
    }

    /**
     * Descarga una aplicacion de la API REST de forma sincrona.
     * Bloquea el hilo actual hasta que se complete la llamada y se obtenga la respuesta.
     * @param nombre de la aplicacion que se quiere descargar
     * @return el objeto response que contiene la aplicacion
     */
    public static Aplicacion getAplicacion(String nombre) {
        final Call<Aplicacion> call = getAPI().aplicacion(nombre);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<Aplicacion> runnable = new CallRunnable<>(call);
        executor.execute(runnable);

        executor.shutdown();
        try {
            executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return  runnable.getResponse();
    }

    /**
     * Descarga los riesgos de la API REST de forma sincrona.
     * Ejecuta la llamada en un hilo en segundo plano y notifica el resultado a
     * través del Callback proporcionado.
     * @param cb el callback que procesa la respuesta de forma asincrona
     */
    public static void requestRiesgos(Callback<Riesgo[]> cb) {
        final Call<Riesgo[]> call = getAPI().riesgos();
        call.enqueue(new CallbackAdapter<>(cb));
    }

    /**
     * Descarga los riesgos de la API REST de forma sincrona.
     * Bloquea el hilo actual hasta que se complete la llamada y se obtenga la respuesta.
     * @return el objeto response que contiene las aplicaciones
     */
    public static Riesgo[] getRiesgos() {
        final Call<Riesgo[]> call = getAPI().riesgos();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<Riesgo[]> runnable = new CallRunnable<>(call);
        executor.execute(runnable);

        executor.shutdown();
        try {
            executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return runnable.getResponse();
    }

    /**
     * Descarga un riesgo de la API REST de forma asincrona.
     * Ejecuta la llamada en un hilo en segundo plano y notifica el resultado a
     * través del Callback proporcionado.
     * @param cb el callback que procesa la respuesta de forma asincrona
     * @param id del riesgo que se quiere descargar
     */
    public static void requestRiesgo(Callback<Riesgo> cb, Long id) {
        final Call<Riesgo> call = getAPI().riesgo(id);
        call.enqueue(new CallbackAdapter<>(cb));
    }

    /**
     * Descarga un riesgo de la API REST de forma sincrona.
     * Bloquea el hilo actual hasta que se complete la llamada y se obtenga la respuesta.
     * @param id del riesgo que se quiere descargar
     * @return el objeto response que contiene el riesgo
     */
    public static Riesgo getRiesgo(Long id) {
        final Call<Riesgo> call = getAPI().riesgo(id);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<Riesgo> runnable = new CallRunnable<>(call);
        executor.execute(runnable);

        executor.shutdown();
        try {
            executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return  runnable.getResponse();
    }

    /**
     * Descarga los controles de la API REST de forma sincrona.
     * Ejecuta la llamada en un hilo en segundo plano y notifica el resultado a
     * través del Callback proporcionado.
     * @param cb el callback que procesa la respuesta de forma asincrona
     */
    public static void requestControles(Callback<Control[]> cb) {
        final Call<Control[]> call = getAPI().controles();
        call.enqueue(new CallbackAdapter<>(cb));
    }

    /**
     * Descarga los controles de la API REST de forma sincrona.
     * Bloquea el hilo actual hasta que se complete la llamada y se obtenga la respuesta.
     * @return el objeto response que contiene los controles
     */
    public static Control[] getControles() {
        final Call<Control[]> call = getAPI().controles();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<Control[]> runnable = new CallRunnable<>(call);
        executor.execute(runnable);

        executor.shutdown();
        try {
            executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return runnable.getResponse();
    }

    /**
     * Descarga un control de la API REST de forma asincrona.
     * Ejecuta la llamada en un hilo en segundo plano y notifica el resultado a
     * través del Callback proporcionado.
     * @param cb el callback que procesa la respuesta de forma asincrona
     * @param id del control que se quiere descargar
     */
    public static void requestControl(Callback<Control> cb, Long id) {
        final Call<Control> call = getAPI().control(id);
        call.enqueue(new CallbackAdapter<>(cb));
    }

    /**
     * Descarga un control de la API REST de forma sincrona.
     * Bloquea el hilo actual hasta que se complete la llamada y se obtenga la respuesta.
     * @param id del control que se quiere descargar
     * @return el objeto response que contiene el riesgo
     */
    public static Control getControl(Long id) {
        final Call<Control> call = getAPI().control(id);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<Control> runnable = new CallRunnable<>(call);
        executor.execute(runnable);

        executor.shutdown();
        try {
            executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return  runnable.getResponse();
    }

    /**
     * Descarga las categorias de la API REST de forma sincrona.
     * Ejecuta la llamada en un hilo en segundo plano y notifica el resultado a
     * través del Callback proporcionado.
     * @param cb el callback que procesa la respuesta de forma asincrona
     */
    public static void requestCategorias(Callback<Categoria[]> cb) {
        final Call<Categoria[]> call = getAPI().categorias();
        call.enqueue(new CallbackAdapter<>(cb));
    }

    /**
     * Descarga las categorias de la API REST de forma sincrona.
     * Bloquea el hilo actual hasta que se complete la llamada y se obtenga la respuesta.
     * @return el objeto response que contiene las aplicaciones
     */
    public static Categoria[] getCategorias() {
        final Call<Categoria[]> call = getAPI().categorias();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<Categoria[]> runnable = new CallRunnable<>(call);
        executor.execute(runnable);

        executor.shutdown();
        try {
            executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return runnable.getResponse();
    }
}
