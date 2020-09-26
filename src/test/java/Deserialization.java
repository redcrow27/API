import io.restassured.RestAssured;
import io.restassured.http.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Deserialization {

    String taskURL = "https://tla-school-api.herokuapp.com/api/school/programs/sdetcourse";
    String studentURL = "https://tla-school-api.herokuapp.com/api/school/resources/students";

    @Test
    void test01() {
      String name = given()
              .get(taskURL)
              .then()
              .statusCode(200)
              .extract()
              .path("data[0].name");

        System.out.println("\n" + name);
    }

    @Test
    void test02() {
        List<String> name = given()
                .get(taskURL)
                .then()
                .statusCode(200)
                .extract()
                .path("data.name");

        name.forEach(a -> System.out.println(a));
    }


    @Test
    void test03() {
        List<String> ids = given()
                .get(studentURL)
                .then()
                .statusCode(200)
                .extract()
                .path("data._id");

        ids.forEach(a -> System.out.println(a));
    }


}
