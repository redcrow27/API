package __testNGTest;

import io.restassured.RestAssured;
import io.restassured.http.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POSTRequest {

    String taskURL = "https://tla-school-api.herokuapp.com/api/school/programs/sdetcourse";

    @Test(description = "serializing using Map")
    void test01() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Java");
        map.put("duration", "3 months");

        System.out.println(map);

        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject);
    }

    @Test(description = "serializing without using Map")
    void test02() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Java");
        jsonObject.put("duration", "3 months");

        System.out.println(jsonObject);
    }

    @Test(description = "create a Json object from .json file")
    void test03() {
        JSONObject jsonObject = JsonUtils.readJsonObjectFromJsonFile("courseObject.json");
        System.out.println(jsonObject);
    }

    // NOTE: Sending POST Request

    @Test(description = "basic POST")
    void test04(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "API course");
        jsonObject.put("duration", "2 weeks");

        given()
                .contentType("application/json")
                .body(jsonObject)
                .when()
                .post(taskURL)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(description = "Sending with header paramaeters")
    void test05() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "API course 1");
        jsonObject.put("duration", "2 weeks");

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonObject)
                .when()
                .post(taskURL)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(description = "POST request with parameters")
    void test06() {
        RestAssured.baseURI = "https://tla-school-api.herokuapp.com/api/school/resources";
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject student = new JSONObject();
        student.put("firstName", "Anderson77");
        student.put("lastName", "Cooper77");
        student.put("email", "acooper77@gg.com");
        student.put("batch", 77);

        httpRequest.header("content-type", "application/json");
        httpRequest.queryParam("key", "sdfsegsgaaads");
        httpRequest.body(student);

        Response response = httpRequest.request(Method.POST, "/students");

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);

        Assert.assertEquals(200, statusCode);
    }


    @Test(description = "POST request with parameters")
    void test07() {
        RestAssured.baseURI = "https://tla-school-api.herokuapp.com/api/school/resources";
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject student = new JSONObject();
        student.put("firstName", "Anderson66");
        student.put("lastName", "Cooper66");
        student.put("phoneNumber", 777-777-6666);
        student.put("email", "acooper66@gg.com");
        student.put("batch", 66);
        student.put("courses", "Java");


        httpRequest.header("content-type", "application/json");
        httpRequest.queryParam("key", "sdfsegsgaaads");
        httpRequest.body(student);

        Response response = httpRequest.request(Method.POST, "/instructors");

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);

        Assert.assertEquals(statusCode, 200);
    }

    @Test(description = "POST request using Bearer's token")
    void test08() {
        String token = "bed13965e94fe0b7654746140b449bc44b7a9e41a58a841892a27386292b45e0";
        JSONObject userObject = new JSONObject();
        userObject.put("name", "Cloudy Weather77");
        userObject.put("email", "cloud77@gmail.com");
        userObject.put("gender", "Male");
        userObject.put("status","Active");

        given()
                .header("Authorization", "Bearer " + token)
                .header("content-type", ContentType.JSON)
                .body(userObject)
                .when()
                .post("https://gorest.co.in/public-api/users")
                .then()
                .statusCode(200)
                .log().body();
    }






}
