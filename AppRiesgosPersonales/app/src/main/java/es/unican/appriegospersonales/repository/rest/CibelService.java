package es.unican.appriegospersonales.repository.rest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import es.unican.appriegospersonales.common.Callback;
import es.unican.appriegospersonales.model.Amenaza;
import es.unican.appriegospersonales.model.Categoria;
import es.unican.appriegospersonales.model.Activo;
import es.unican.appriegospersonales.model.Control;
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
     * Descarga los activos de la API REST de forma asincrona.
     * Ejecuta la llamada en un hilo en segundo plano y notifica el resultado a
     * través del Callback proporcionado.
     * @param cb el callback que procesa la respuesta de forma asincrona
     * @param categoria se usa si se quiere que los activos se filtren por categoria,
     *                  sino dejarlo a NULL
     */
    public static void requestActivos(Callback<Activo[]> cb, String categoria) {
        final Call<Activo[]> call = getAPI().activos(categoria);
        call.enqueue(new CallbackAdapter<>(cb));
    }

    /**
     * Descarga los activos de la API REST de forma sincrona.
     * Bloquea el hilo actual hasta que se complete la llamada y se obtenga la respuesta.
     * @param categoria se usa si se quiere que los activos se filtren por categoria,
     *                  sino dejarlo a NULL
     * @return el objeto response que contiene los activos
     */
    public static Activo[] getActivos(String categoria) {
        final Call<Activo[]> call = getAPI().activos(categoria);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<Activo[]> runnable = new CallRunnable<>(call);
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
     * Descarga los riesgos de la API REST de forma sincrona.
     * Ejecuta la llamada en un hilo en segundo plano y notifica el resultado a
     * través del Callback proporcionado.
     * @param cb el callback que procesa la respuesta de forma asincrona
     */
    public static void requestAmenazas(Callback<Amenaza[]> cb) {
        final Call<Amenaza[]> call = getAPI().riesgos();
        call.enqueue(new CallbackAdapter<>(cb));
    }

    public static void requestAmenazasDeApps(Callback<Amenaza[]> cb) {
        final Call<Amenaza[]> call = getAPI().riesgosDeApps();
        call.enqueue(new CallbackAdapter<>(cb));
    }

    public static void requestAmenazasDeDispositivos(Callback<Amenaza[]> cb) {
        final Call<Amenaza[]> call = getAPI().riesgosDeDispositivos();
        call.enqueue(new CallbackAdapter<>(cb));
    }

    /**
     * Descarga los riesgos de la API REST de forma sincrona.
     * Bloquea el hilo actual hasta que se complete la llamada y se obtenga la respuesta.
     * @return el objeto response que contiene las aplicaciones
     */
    public static Amenaza[] getAmenazas() {
        final Call<Amenaza[]> call = getAPI().riesgos();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<Amenaza[]> runnable = new CallRunnable<>(call);
        executor.execute(runnable);

        executor.shutdown();
        try {
            executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return runnable.getResponse();
    }

    public static Amenaza[] getAmenazasDeApps() {
        final Call<Amenaza[]> call = getAPI().riesgosDeApps();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<Amenaza[]> runnable = new CallRunnable<>(call);
        executor.execute(runnable);

        executor.shutdown();
        try {
            executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return runnable.getResponse();
    }

    public static Amenaza[] getAmenazasDeDispositivos() {
        final Call<Amenaza[]> call = getAPI().riesgosDeDispositivos();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<Amenaza[]> runnable = new CallRunnable<>(call);
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

    public static Control[] getControlesDeApps() {
        final Call<Control[]> call = getAPI().controlesDeApps();

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

    public static Control[] getControlesDeDispositivos() {
        final Call<Control[]> call = getAPI().controlesDeDispositivos();

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

    public static Categoria[] getCategoriasDeApps() {
        final Call<Categoria[]> call = getAPI().categoriasDeApps();

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

    public static Categoria[] getCategoriasDeDispositivos() {
        final Call<Categoria[]> call = getAPI().categoriasDeDispositivos();

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
