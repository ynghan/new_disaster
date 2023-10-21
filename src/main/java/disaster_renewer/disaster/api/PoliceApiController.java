package disaster_renewer.disaster.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/api")
public class PoliceApiController {
    @Value("${my.api.key.police}")
    private String apiKey;

    @GetMapping("/police")
    public String getDataFromOpenApi() {

        StringBuilder finalResponse = new StringBuilder();
//        List<PoliceDto> policeDtos = new ArrayList<>();

        int num = 1;

        for (int a = 1; a < 228; a++) {
            try {
                StringBuilder response = new StringBuilder();
                // Open API URL 설정
                String apiUrl = "https://api.odcloud.kr/api/15054711/v1/uddi:f038d752-ff35-4a22-a2c2-cf9b90de7a30" +
                        "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + apiKey + /*Service Key*/
                        "&" + URLEncoder.encode("page", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(Integer.toString(a), StandardCharsets.UTF_8) + /*페이지번호*/
                        "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("10", StandardCharsets.UTF_8) + /*한 페이지 결과 수*/
                        "&" + URLEncoder.encode("resultType", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("json", StandardCharsets.UTF_8); /*xml/json(default : xml)*/ // 실제 Open API의 URL로 대체
                JSONArray array = getJsonArray(apiUrl);

                if(a != 227) {
                    for (int j = 0; j < 10; j++) {
                        response.append("insert into police (police_id, police_agency, police_station, police_box, address, x_coordinate, y_coordinate) values (");
                        response.append(num).append(", \"").append(((JSONObject) array.get(j)).getString("청")).append("\", \"");
                        response.append(((JSONObject) array.get(j)).getString("서")).append("\", \"");
                        response.append(((JSONObject) array.get(j)).getString("지구대파출소")).append("\", \"");
                        response.append(((JSONObject) array.get(j)).getString("주소")).append("\", ");
                        response.append(((JSONObject) array.get(j)).getDouble("X좌표")).append(", ");
                        response.append(((JSONObject) array.get(j)).getDouble("Y좌표")).append(");\n");
                        num++;
                    }
                    finalResponse.append(response);
                } else {
                    for(int j = 0; j < 4; j++) {
                        response.append("insert into police (police_id, police_agency, police_station, police_box, address, x_coordinate, y_coordinate) values (");
                        response.append(num).append(", \"").append(((JSONObject) array.get(j)).getString("청")).append("\", \"");
                        response.append(((JSONObject) array.get(j)).getString("서")).append("\", \"");
                        response.append(((JSONObject) array.get(j)).getString("지구대파출소")).append("\", \"");
                        response.append(((JSONObject) array.get(j)).getString("주소")).append("\", ");
                        response.append(((JSONObject) array.get(j)).getDouble("X좌표")).append(", ");
                        response.append(((JSONObject) array.get(j)).getDouble("Y좌표")).append(");\n");
                        num++;
                    }
                    finalResponse.append(response);
                }


            } catch (Exception e) {
                // 예외 처리
                e.printStackTrace();
                return null;
            }
        }
        return finalResponse.toString();
    }

    private static JSONArray getJsonArray(String apiUrl) throws IOException, JSONException {
        URL url = new URL(apiUrl);

        // HttpURLConnection 생성 및 설정
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");

        // API 응답 읽기
        BufferedReader rd;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;


        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        connection.disconnect();

        JSONObject jsonObject = new JSONObject(sb.toString());
        return jsonObject.getJSONArray("data");
    }
}
//    public PoliceDto createPolices(JSONObject jsonObject) throws JSONException {
//        PoliceDto policeDto = new PoliceDto();
//        policeDto.setXCoordinate(jsonObject.getDouble("X좌표"));
//        policeDto.setYCoordinate(jsonObject.getDouble("Y좌표"));
////        policeDto.setAddress((String) jsonObject.get("주소"));
//        return policeDto;
//    }

