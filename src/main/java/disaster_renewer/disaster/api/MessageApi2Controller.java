package disaster_renewer.disaster.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MessageApi2Controller {
        public static void main(String[] args) {
            String url = "https://www.safetydata.go.kr/openApi/";
            String dataName = "행정안전부_긴급재난문자";
            String apiKey = "0B8T785T3DZWP60N";

            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("serviceKey", apiKey);
            queryParams.put("returnType", "JSON");
            queryParams.put("pageNum", "1");
            queryParams.put("numRowsPerPage", "5");

            try {
                StringBuilder requestUrl = new StringBuilder(url + dataName + "?");
                for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                    requestUrl.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }

                URL urlObject = new URL(requestUrl.toString());

                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    System.out.println("API Response:");
                    System.out.println(response.toString());
                } else {
                    System.out.println("HTTP GET request failed with response code: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }