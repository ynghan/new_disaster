package disaster_renewer.disaster.api;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class SafeCenter119CSVtoJSONCoontroller {

    @GetMapping("/safecenter119")
    public String convertCsvToJson() throws Exception {
        try {
            String jsonFilePath = "safeCenter119.json";

            StringBuilder sb = new StringBuilder();
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(jsonFilePath, StandardCharsets.UTF_8));

            // JSONArray 순회
            for (Object item : jsonArray) {
                JSONObject jsonObject = (JSONObject) item;
                sb.append("insert into safe_center119 (safe_center_id, jurisdiction, safe_center_name, address, number) values (");
                sb.append(jsonObject.get("순번")).append(", \"");
                sb.append(jsonObject.get("시도본부")).append("\", \"");
                sb.append(jsonObject.get("119안전센터명")).append("\", \"");
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
