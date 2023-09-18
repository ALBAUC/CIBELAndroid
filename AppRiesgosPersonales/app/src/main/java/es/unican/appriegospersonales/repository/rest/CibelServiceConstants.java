package es.unican.appriegospersonales.repository.rest;

public class CibelServiceConstants {

    private CibelServiceConstants() {
    }

    private static final String API_URL = "http://192.168.4.31:8080/";

    public static String getAPIURL() {
        return API_URL;
    }
}
