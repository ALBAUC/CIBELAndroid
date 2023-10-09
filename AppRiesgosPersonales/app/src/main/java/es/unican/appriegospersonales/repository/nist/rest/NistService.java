package es.unican.appriegospersonales.repository.nist.rest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import es.unican.appriegospersonales.model.nist.ResultCVES;
import es.unican.appriegospersonales.repository.common.CallRunnable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NistService {

    private static NistAPI api;

    private static NistAPI getAPI() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(NistServiceConstants.getAPIURL())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(NistAPI.class);
        }
        return api;
    }

    public static final long TIMEOUT_SECONDS = 60L;

    public static ResultCVES getVulnerabilidades(String modelo) {
        final Call<ResultCVES> call = getAPI().vulnerabilidades(modelo);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CallRunnable<ResultCVES> runnable = new CallRunnable<>(call);
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
}
