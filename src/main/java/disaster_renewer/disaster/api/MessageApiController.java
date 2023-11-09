package disaster_renewer.disaster.api;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class MessageApiController {

    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://106.250.19.243:8080/openApi/행정안전부_긴급재난문자"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("0B8T785T3DZWP60N", StandardCharsets.UTF_8)); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("JSON", StandardCharsets.UTF_8)); /*정류소명 검색어*/
        urlBuilder.append("&" + URLEncoder.encode("pageNum", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8)); /*정류소명 검색어*/
        urlBuilder.append("&" + URLEncoder.encode("numRowsPerPage", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8)); /*정류소명 검색어*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
    }

//    @GetMapping("/message")
//    public String getMessage() throws IOException, JSONException {
//        StringBuilder urlBuilder = new StringBuilder("https://www.safetydata.go.kr/openApi/행정안전부_긴급재난문자"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("0B8T785T3DZWP60N", StandardCharsets.UTF_8)); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("returnType", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("JSON", StandardCharsets.UTF_8)); /*정류소명 검색어*/
//        urlBuilder.append("&" + URLEncoder.encode("pageNum", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8)); /*정류소명 검색어*/
//        urlBuilder.append("&" + URLEncoder.encode("numRowsPerPage", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1000", StandardCharsets.UTF_8)); /*정류소명 검색어*/
//        URL url = new URL(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//
//        return sb.toString();
//    }
}
