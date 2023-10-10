package es.unican.appriegospersonales.temp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class GetCVEs {
    public GetCVEs(String modelo) {
        try {
            // Especifica el modelo o aplicación que deseas analizar
            
            //Tablets
            //modelo = "Xiaomi Mi Pad 4"
            //modelo = "Dell Latitude 7202"
            
            //Consolas
            //modelo = "Playstation 3" //Consola, para la 5 no hay
            //modelo = "Microsoft Xbox 360"
            
            //Impresoras
            //modelo = "HP OfficeJet Pro" // Impresora
            //modelo = "Brother MFC-9970CDW"
            
            //Relojes Inteligentes
            //modelo = "Pebble Smartwatch" // Smartwatch
            //modelo = "Apple Watch Ultra"
            
            //Ordenadores
            //modelo = "Lenovo ThinkPad T440s" //Ordenador
            //modelo = "Apple Mac Mini"
            
            //Smartphones
            //modelo = "iPhone 12" // Movil
            //modelo = "iPhone 13" // Movil Moderno
            //modelo = "Samsung Galaxy S5" // Android Viejo
            //modelo = "Huawei Mate 20"
            
            //Smart TVs
            //modelo = "Samsung Smart TV X10P" // Smart TV
            //modelo = "TCL Android Smart TV V8-R851T02-LF1"
            
            
            //Altavoces Inteligentes
            //modelo = "Amazon Echo Dot" // Asistente
            //modelo = "Google Home"
            //modelo = "JBL Go 2"
            
            //Auriculares inteligentes
            //modelo = "SONY Wireless Headphones WF-1000X"
            //modelo = "JBL TUNE500BT"
            
            //Iluminacion inteligente
            //modelo = "TPLink Tapo L530" // Bombilla Class 1
            //modelo = "Philips Smart Wi-Fi Wiz Connected"
            
            
            //Termostatos inteligentes
            //modelo = "Radio Thermostat CT80" // Termostato Class 2
            //modelo = "Radio Thermostat CT50"
            
            //Routers
            //modelo = "TP-LINK ARCHER C50" // Router
            //modelo = "Alcatel-Lucent CellPipe 7130"
            //modelo = "Huawei HG8247"
            
            //Repetidores o amplificadores
            //modelo = "Ayision Ays-WR01"
            //modelo = "Intelbras NPLUG"
            
            //Camaras de seguridad ip
            //modelo = "TP-Link Tapo C200"
            //modelo = "Geutebruck G-Cam/EFD-2250"
            //modelo = "ADC-V522IR"
            
            //Alarmas y timbres inteligentes
            //modelo = "ABUS Secvest FUAA50000"
            //modelo = "Chuango 433 MHz burglar-alarm"
            
            
            //Cerraduras inteligentes
            //modelo = "Tinxy Door Lock" //Cerradura
            //modelo = "Nokelock Smart padlock O1" //Candado
            
            //Detectores y sensores
            //modelo = "FLIR AX8"
            //modelo = "Tollgrade SmartGrid LightHouse Sensor"
            
            //Electrodomesticos inteligentes
            //modelo = "Dongguan Diqee Diqee360" //Robot aspiradora
            //modelo = "Jisiwei i3"  //Aspiradora
            //modelo = "Rittal Chiller SK 3232-Series" //Aire acondicionado
            
            //Otros
            //modelo = "Sophos Cyberoam" //Firewall
            //modelo = "Finjan Vital Security Appliance 5100" //Firewall
            //modelo = "Innominate mGuard Smart HW" //Enrutador

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

                    //CVE de la vulnerabilidad
                    String cve_id = cve.getString("id");

                    // Inicializa las variables con "NULL" en caso de que falten métricas

                    //Se refiere a la vía usada para explotar la vulnerabilidad (Por ejemplo por red)
                    String accessVector = "NULL";

                    //Se refiere a la dificultad que tiene explotar la vulnerabilidad
                    String accessComplexity = "NULL";

                    //Impacto que tiene en la confidencialidad si se explota la vulnerabilidad
                    String confidentialityImpact = "NULL";

                    //Impacto que tiene en la integridad si se explota la vulnerabilidad
                    String integrityImpact = "NULL";

                    //Impacto que tiene en la disponibilidad si se explota la vulnerabilidad
                    String availabilityImpact = "NULL";

                    //Puntuación de 0 a 10 dada a la vulnerabilidad por su peligro
                    String base_score = "NULL";

                    //Nivel de severidad de la vulnerabilidad (Bajo, medio o alto)
                    String base_severity = "NULL";

                    String exploitability_score = "NULL";
                    String impact_score = "NULL";

                    //Descripcion de la vulnerabilidad
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

}
