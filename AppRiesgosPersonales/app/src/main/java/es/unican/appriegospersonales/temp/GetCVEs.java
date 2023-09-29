package es.unican.appriegospersonales.temp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class GetCVEs {
    public GetCVEs() {
        try {
            // Especifica el modelo o aplicación que deseas analizar
            String modelo = "Google Maps";

            // Construye la URL de la API NVD que contiene información de vulnerabilidades
            String url = "https://services.nvd.nist.gov/rest/json/cves/2.0?keywordSearch=" + modelo;

            // Realiza una solicitud HTTP para obtener los datos JSON
            Connection.Response response = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .execute();

            // Analiza la respuesta JSON
            JSONObject datos = new JSONObject(response.body());

            // Crear una lista para almacenar los datos de cada vulnerabilidad
            JSONArray filas = new JSONArray();

            // Itera a través de las vulnerabilidades y agrega los datos a la lista de filas
            JSONArray vulnerabilities = datos.getJSONArray("vulnerabilities");
            if (vulnerabilities != null) {
                for (int i = 0; i < vulnerabilities.length(); i++) {
                    JSONObject vulnerability = vulnerabilities.getJSONObject(i);
                    JSONObject cve = vulnerability.getJSONObject("cve");
                    String cve_id = cve.getString("id");

                    // Inicializa las variables con "NULL" en caso de que falten métricas
                    String accessVector = "NULL";
                    String accessComplexity = "NULL";
                    String confidentialityImpact = "NULL";
                    String integrityImpact = "NULL";
                    String availabilityImpact = "NULL";
                    String base_score = "NULL";
                    String base_severity = "NULL";
                    String exploitability_score = "NULL";
                    String impact_score = "NULL";
                    String second_description = "NULL";

                    // Verifica si hay al menos dos descripciones antes de acceder al segundo elemento
                    if (cve.has("descriptions") && cve.getJSONArray("descriptions").length() >= 2) {
                        second_description = cve.getJSONArray("descriptions").getJSONObject(1).getString("value");
                    }

                    // Comprueba si hay métricas CVSS v3
                    if (vulnerability.has("impact") && vulnerability.getJSONObject("impact").has("baseMetricV3")) {
                        JSONObject cvssV3 = vulnerability.getJSONObject("impact").getJSONObject("baseMetricV3");
                        accessVector = cvssV3.getJSONObject("cvssV3").getString("attackVector");
                        accessComplexity = cvssV3.getJSONObject("cvssV3").getString("attackComplexity");
                        confidentialityImpact = cvssV3.getJSONObject("cvssV3").getString("confidentialityImpact");
                        integrityImpact = cvssV3.getJSONObject("cvssV3").getString("integrityImpact");
                        availabilityImpact = cvssV3.getJSONObject("cvssV3").getString("availabilityImpact");
                        base_score = cvssV3.getJSONObject("cvssV3").getString("baseScore");
                        base_severity = cvssV3.getString("baseSeverity");
                        exploitability_score = cvssV3.getString("exploitabilityScore");
                        impact_score = cvssV3.getString("impactScore");
                    }

                    // Comprueba si hay métricas CVSS v2
                    else if (vulnerability.has("impact") && vulnerability.getJSONObject("impact").has("baseMetricV2")) {
                        JSONObject cvssV2 = vulnerability.getJSONObject("impact").getJSONObject("baseMetricV2");
                        accessVector = cvssV2.getJSONObject("cvssV2").getString("accessVector");
                        accessComplexity = cvssV2.getJSONObject("cvssV2").getString("accessComplexity");
                        confidentialityImpact = cvssV2.getJSONObject("cvssV2").getString("confidentialityImpact");
                        integrityImpact = cvssV2.getJSONObject("cvssV2").getString("integrityImpact");
                        availabilityImpact = cvssV2.getJSONObject("cvssV2").getString("availabilityImpact");
                        base_score = cvssV2.getJSONObject("cvssV2").getString("baseScore");
                        base_severity = cvssV2.getJSONObject("cvssV2").getString("severity");
                        exploitability_score = cvssV2.getJSONObject("cvssV2").getString("exploitabilityScore");
                        impact_score = cvssV2.getJSONObject("cvssV2").getString("impactScore");
                    }
                }
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GetCVEs();
    }
}
