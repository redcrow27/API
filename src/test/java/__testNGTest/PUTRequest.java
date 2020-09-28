package __testNGTest;

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

public class PUTRequest {

    String taskURL = "https://tla-school-api.herokuapp.com/api/school/programs/sdetcourse";

    @Test(description = "PUT request")
    void test01() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Java");
        map.put("duration", "33 months");

        JSONObject jsonObject = new JSONObject(map);

        given()
                .header("content-type", ContentType.JSON)
                .queryParam("name", "Java")
                .body(jsonObject)
                .when()
                .put(taskURL)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(description = "API key as a Query param")
    void test02() {
        JSONObject student = new JSONObject();
        student.put("firstName", "Anderson77");
        student.put("lastName", "Cooper77");
        student.put("email", "acooper77@gg.com");
        student.put("batch", 77);

        given()
                .header("content-type", ContentType.JSON)
                .queryParam("key", "d03e989018msh7f4691c614e87a9p1a8181j")
                .pathParam("studentID", "5f67a00f6c91e700178a82ad")
                .body(student)
                .when().put("https://tla-school-api.herokuapp.com/api/school/resources/students/{studentID}")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(description = "API key as a Header param")
    void test03(){
        JSONObject instructor = new JSONObject();
        instructor.put("firstName", "777");
        instructor.put("lastName", "7777");
        instructor.put("email", "7777777777777@gg.com");

        given()
                .header("content-type", ContentType.JSON)
                .header("Authorization", "d03e989018msh7f4691c614e87a9p1a8181j")
                .pathParam("instructorID", "5f6f760688158f0017c40038")
                .body(instructor)
                .when()
                .put("https://tla-school-api.herokuapp.com/api/school/resources/instructors/{instructorID}")
                .then()
                .statusCode(200)
                .log().body();
    }





}
