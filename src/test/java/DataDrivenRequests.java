import io.restassured.RestAssured;
import io.restassured.http.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DataDrivenRequests {

    String taskURL = "https://tla-school-api.herokuapp.com/api/school/programs/sdetcourse";

    @DataProvider(name = "courseNames")
    public Object[][] courseData() {
        return new Object[][] {
                {"RestAssured77","2 days"},
                {"Gson77","30 min"},
                {"Google77","2 years"}
        };
    }

    // NOTE

    @Test(dataProvider = "courseNames", description = "basic POST")
    void test01(String courseName, String duration){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", courseName);
        jsonObject.put("duration", duration);
        given()
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .when()
                .post(taskURL)
                .then()
                .statusCode(200)
                .log().body();
    }

    @DataProvider(name = "courses")
    public Object[] courses() {
        return new Object[] {"RestAssured77", "Gson77", "Google77" };
    }

    @Test(dataProvider = "courses", description = "Delete courses with DataProvider")
    void test02(String course){
        given()
                .queryParam("name", course)
                .when()
                .delete(taskURL)
                .then()
                .statusCode(200)
                .log().body();
    }

}
