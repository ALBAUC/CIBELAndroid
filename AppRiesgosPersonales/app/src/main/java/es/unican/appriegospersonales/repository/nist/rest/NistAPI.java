package es.unican.appriegospersonales.repository.nist.rest;

import es.unican.appriegospersonales.model.nist.ResultCVES;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NistAPI {

    @GET("cves/2.0")
    Call<ResultCVES> vulnerabilidades(@Query("keywordSearch") String modelo);
}
