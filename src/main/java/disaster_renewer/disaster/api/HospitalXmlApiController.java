package disaster_renewer.disaster.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class HospitalXmlApiController {


    @GetMapping("/hospital/A")
    public String getDataFromOpenApi() throws IOException {

        StringBuilder response = new StringBuilder();
        /*URL*/
        String apiUrl = "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire"
                + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=A8m%2BLiXkYxIQHFPAUy7wLpqhw6YVD1oJnSsMAviqFWEVArg2KDkCBkPRdjyuoPMFKGp0Rl8DXCoa4SqWo5IH4g%3D%3D"
                + "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("2000", StandardCharsets.UTF_8)
                + "&" + URLEncoder.encode("QZ", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("A", StandardCharsets.UTF_8);
        URL url = new URL(apiUrl);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String xml = sb.toString();
        JSONObject jObject = XML.toJSONObject(xml);

        JSONArray array = jObject.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
        for(int i = 0 ; i < 375; i++) {
            response.append("insert into hospital (hospital_name, address, x_coordinate, y_coordinate) values (\"");
            response.append(((JSONObject) array.get(i)).getString("dutyName")).append("\", \"");
            response.append(((JSONObject) array.get(i)).getString("dutyAddr")).append("\", ");
            response.append(((JSONObject) array.get(i)).getDouble("wgs84Lat")).append(", ");
            response.append(((JSONObject) array.get(i)).getDouble("wgs84Lon")).append("); \n");
        }


//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        Object json = mapper.readValue(jObject.toString(), Object.class);
//        String output = mapper.writeValueAsString(json);

        return response.toString();
    }

    @GetMapping("/hospital/B")
    public String getDataFromOpenApi2() throws IOException {

        StringBuilder response = new StringBuilder();
        /*URL*/
        String apiUrl = "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire"
                + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=A8m%2BLiXkYxIQHFPAUy7wLpqhw6YVD1oJnSsMAviqFWEVArg2KDkCBkPRdjyuoPMFKGp0Rl8DXCoa4SqWo5IH4g%3D%3D"
                + "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("2000", StandardCharsets.UTF_8)
                + "&" + URLEncoder.encode("QZ", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("B", StandardCharsets.UTF_8);
        URL url = new URL(apiUrl);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String xml = sb.toString();
        JSONObject jObject = XML.toJSONObject(xml);

        JSONArray array = jObject.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
        for(int i = 0 ; i < 1429; i++) {
            if(i == 854) {
                response.append("insert into hospital (hospital_name, address) values (\"");
                response.append(((JSONObject) array.get(i)).getString("dutyName")).append("\", \"");
                response.append(((JSONObject) array.get(i)).getString("dutyAddr")).append("\"); \n");
            } else {
                response.append("insert into hospital (hospital_name, address, x_coordinate, y_coordinate) values (\"");
                response.append(((JSONObject) array.get(i)).getString("dutyName")).append("\", \"");
                response.append(((JSONObject) array.get(i)).getString("dutyAddr")).append("\", ");
                response.append(((JSONObject) array.get(i)).getDouble("wgs84Lat")).append(", ");
                response.append(((JSONObject) array.get(i)).getDouble("wgs84Lon")).append("); \n");
            }
        }


//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        Object json = mapper.readValue(jObject.toString(), Object.class);
//        String output = mapper.writeValueAsString(json);

        return response.toString();
    }

}
