package disaster_renewer.disaster.api;

import disaster_renewer.disaster.dto.HospitalDto;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.geojson.geom.GeometryJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HospitalShapeFileController {

    @GetMapping("/hospital")
    public String getHospital() throws IOException, JSONException {

//        String response = "";

//        List<HospitalDto> hospitalDtos = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int num = 1;

        // Shapefile 로드
        File shapefile = new File("XsDB_hospital_100M_TM.shp");
        ShapefileDataStore dataStore = new ShapefileDataStore(shapefile.toURI().toURL());

        // Feature 컬렉션 가져오기
        SimpleFeatureCollection collection = dataStore.getFeatureSource().getFeatures();
        SimpleFeatureIterator iterator = collection.features();

        // GeoJSON 생성
        GeometryJSON gjson = new GeometryJSON();
        while (iterator.hasNext()) {
            SimpleFeature feature = iterator.next();
            String geojson = gjson.toString((Geometry) feature.getDefaultGeometry());


            JSONObject jsonObject = new JSONObject(geojson);
            JSONArray array = jsonObject.getJSONArray("coordinates");

            sb.append("insert into hospital (hospital_id, x_coordinate, y_coordinate) values (");
            sb.append(num).append(", ");
            sb.append(array.getDouble(0)).append(", ");
            sb.append(array.getDouble(1)).append(");\n");
            num++;

        }

        iterator.close();
        dataStore.dispose();

        return sb.toString();
//        return response;
    }
}
