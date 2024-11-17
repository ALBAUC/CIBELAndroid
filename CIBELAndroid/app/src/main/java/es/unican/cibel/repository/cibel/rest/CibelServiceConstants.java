package es.unican.cibel.repository.cibel.rest;

public class CibelServiceConstants {

    private CibelServiceConstants() {
    }

    private static final String API_URL = "https://thorough-healthy-escargot.ngrok-free.app";
    //private static final String API_URL = "http://192.168.1.80:8080/";

    public static String getAPIURL() {
        return API_URL;
    }
}
