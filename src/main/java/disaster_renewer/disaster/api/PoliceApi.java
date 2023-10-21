//package disaster_renewer.disaster.api;
//
//import disaster_renewer.disaster.domain.publicSafety.Police;
//import disaster_renewer.disaster.dto.PoliceDto;
//import disaster_renewer.disaster.repository.PoliceJpaRepository;
//import lombok.RequiredArgsConstructor;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//import java.io.InputStreamReader;
//import java.net.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class PoliceApi {
//
//    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
//
//    public static void main(String[] args) {
//
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//
//        List<Police> policeDtos = new ArrayList<>();
//
//        for (int i = 1; i < 228; i++) {
//            try {
//                tx.begin();
//                // Open API URL 설정
//                String apiUrl = "https://api.odcloud.kr/api/15054711/v1/uddi:f038d752-ff35-4a22-a2c2-cf9b90de7a30" +
//                        "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=A8m%2BLiXkYxIQHFPAUy7wLpqhw6YVD1oJnSsMAviqFWEVArg2KDkCBkPRdjyuoPMFKGp0Rl8DXCoa4SqWo5IH4g%3D%3D" + /*Service Key*/
//                        "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(Integer.toString(i), StandardCharsets.UTF_8) + /*페이지번호*/
//                        "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("10", StandardCharsets.UTF_8) + /*한 페이지 결과 수*/
//                        "&" + URLEncoder.encode("resultType", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("json", StandardCharsets.UTF_8); /*xml/json(default : xml)*/ // 실제 Open API의 URL로 대체
//                URL url = new URL(apiUrl);
//
//                // HttpURLConnection 생성 및 설정
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//                connection.setRequestProperty("Content-type", "application/json");
//
//                // API 응답 읽기
//                BufferedReader rd;
//                if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
//                    rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                } else {
//                    rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//                }
//                StringBuilder sb = new StringBuilder();
//                String line;
//
//
//                while ((line = rd.readLine()) != null) {
//                    sb.append(line);
//                }
//
//                rd.close();
//                connection.disconnect();
//
//                JSONObject jsonObject = new JSONObject(sb.toString());
//                JSONArray array = jsonObject.getJSONArray("data");
//
//                for (int j = 1; j < array.length(); j++) {
//                    policeDtos.add(createPolices((JSONObject) array.get(j)));
//                }
//
//                System.out.println(policeDtos);
//                tx.commit();
//            } catch (Exception e) {
//                // 예외 처리
//                e.printStackTrace();
//                tx.rollback();
//            } finally {
//                em.close();
//            }
//            emf.close();
//        }
//    }
//
//    public static Police createPolices(JSONObject jsonObject) throws JSONException {
//        Police police = new Police();
//        police.setXCoordinate(jsonObject.getDouble("X좌표"));
//        police.setYCoordinate(jsonObject.getDouble("Y좌표"));
//        police.setAddress((String) jsonObject.get("주소"));
//        return police;
//    }
//}
