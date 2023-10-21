package disaster_renewer.disaster.api;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class FireStationCSVtoJSONController {

    @GetMapping("/firestation")
    public String convertCsvToJson() throws Exception {

        try {
            String jsonFilePath = "fireStation.json";

            StringBuilder sb = new StringBuilder();
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(jsonFilePath, StandardCharsets.UTF_8));

            // JSONArray 순회
            for (Object item : jsonArray) {
                JSONObject jsonObject = (JSONObject) item;
                sb.append("insert into fire_center (fire_center_id, jurisdiction, fire_center_name, address, number) values (");
                sb.append(jsonObject.get("순번")).append(", \"");
                sb.append(jsonObject.get("본부명")).append("\", \"");
                sb.append(jsonObject.get("소방서")).append("\", \"");
                sb.append(jsonObject.get("주소")).append("\", \"");
                sb.append(jsonObject.get("전화번호")).append("\");\n");

            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
