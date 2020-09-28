package __testNGTest;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GETRequests {

    String classURL = "https://gorest.co.in/public-api/users";
    String taskURL = "https://tla-school-api.herokuapp.com/api/school/programs/sdetcourse";
    String cookieURL = "https://jsonplaceholder.typicode.com/users";


    @Test(description = "Get body off the request")
    void test01() {
        Response response = RestAssured.get(taskURL);
        response.getBody().prettyPrint();
    }

    @Test(description = "class task")
    void test02() {
        Response response = RestAssured.get(classURL);
        response.getBody().prettyPrint();
        System.out.println(response.getHeaders());
    }

    @Test(description = "apply assertions")
    void test03() {
        given()
                .when()
                .get(classURL)
                .then()
                .statusCode(200);
    }

    @Test(description = "logging the results")
    void test04() {
        given()
                .when()
                .get(classURL + "/2")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(description = "assert specific detail of the response body ")
    public void test05() {
        given()
                .get(classURL + "/2")
                .then()
                .statusCode(200)
                .body("data.gender", equalTo("Male"))
                .log()
                .body();
    }

    @Test(description = "validate if response body contains given items ")
    public void test06() {
        given()
                .get(classURL)
                .then()
                .body("data.name", hasItems("Aaditya Shukla Ret.", "Bankim Shah"))
                .log().body();
    }

    @Test(description = "validate if response body contains given items ")
    public void test07() {
        given()
                .get(taskURL)
                .then()
                .body("data.name", hasItems("Java", "Apache POI"))
                .log().body();
    }


    @Test(description = "extract header info")
    void test08() {
        Headers headers = given()
                .get(classURL).getHeaders();
        System.out.println(headers + "\n");
        System.out.println(headers.get("server"));

        Header header = given().get(classURL).headers().get("content-type");
        System.out.println(header);
    }

    @Test(description = "extract cookie info")
    void test09() {
        given()
                .get(taskURL)
                .then()
                .statusCode(200)
                .cookie("connect.sid")
                .log().cookies();
    }

    @Test(description = "extract cookie info")
    void test10() {
        Cookie cookie = given()
                .get(cookieURL)
                .then()
                .statusCode(200)
                .extract()
                .detailedCookie("__cfduid");

        System.out.println(cookie.getDomain());
        System.out.println(cookie.getValue());
        System.out.println(cookie.getExpiryDate());
    }

    @Test(description = "extract cookie info")
    void test11() {
        Cookie cookie = given()
                .get(taskURL)
                .getDetailedCookie("connect.sid");
        System.out.println(cookie.getValue());
        System.out.println(cookie.isSecured());
    }

    // NOTE PathParameters
    @Test()
    void test12() {
        String baseURL = "https://gorest.co.in/public-api";
        given()
                .when()
                .pathParam("resource", "users")
                .get(baseURL + "/{resource}")
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @DataProvider(name = "resourceData")
    public Object[] getResourceData() {
        return new Object[] {"users", "posts", "comments", "products"};
    }

    //NOTE using dataProvider with Path Paramaters
    @Test(dataProvider = "resourceData", description = "pathParam with DataProvider")
    void test13(String resource) {
        String baseURL = "https://gorest.co.in/public-api";
        given()
                .when()
                .pathParam("resource", resource)
                .get(baseURL + "/{resource}")
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @DataProvider(name = "resourceData2")
    public Object[] getResourceData2() {
        return new Object[] {"posts", "comments", "albums", "photos", "todos", "users"};
    }

    //NOTE using dataProvider with Path Paramaters
    @Test(dataProvider = "resourceData2", description = "pathParam with DataProvider")
    void test14(String resource) {
        String baseURL = "https://jsonplaceholder.typicode.com";
        given()
                .when()
                .pathParam("resource", resource)
                .get(baseURL + "/{resource}")
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    //NOTE using Query paramaters
    @Test(description = "find a specific course")
    void test15() {
        given()
                .queryParam("name", "Selenium course")
                .get(taskURL)
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @Test(description = "chaining query params")
    void test16() {
        given()
                .queryParam("name", "Selenium course", "Java", "Java course")
                .get(taskURL)
                .then()
                .statusCode(200)
                .body("data.name", hasItems("Selenium course", "Java", "Java course"))
                .log()
                .body();
    }

    @Test()
    void test17() {
        given()
                .queryParam("name",  "Java")
                .get(taskURL)
                .then()
                .statusCode(200)
                .body("data[0].name", equalTo( "Java"))
                .log()
                .body();
    }

    @Test(description = "using google Places api")
    void test18(){
        given()
                .queryParam("location", "38.890759,-77.084747")
                .queryParam("radius", 1500)
                .queryParam("type", "school")
                .queryParam("key", "YourApiKey")
                .when()
                .get("https://maps.googleapis.com/maps/api/place/nearbysearch/json")
                .then()
                .statusCode(200)
                .body("results.name", hasItems("Strayer University", "The Merit School of Arlington"))
                .log().body();
    }

    @Test()
    void test19(){
        RestAssured.baseURI = "https://tla-school-api.herokuapp.com/api/school/resources";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "/students/5f67b0346c91e700178a82be");

        String responseBody = response.getBody().asString();
        int status = response.getStatusCode();

        System.out.println(responseBody);
        System.out.println();
        System.out.println(status);
    }



}
