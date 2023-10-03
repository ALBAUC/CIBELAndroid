package es.unican.appriegospersonales.repository.rest;

public class CibelServiceConstants {

    private CibelServiceConstants() {
    }

    private static final String API_URL = "http://192.168.4.36:8080/";
    public static final String APP = "App";
    public static final String DISPOSITIVO_IOT = "DispositivoIoT";

    public static String getAPIURL() {
        return API_URL;
    }
}
