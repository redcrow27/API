package __testNGTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.Test;
import pojos.dummyAPI.Employee;
import pojos.sdetCourse.SdetCourse;
import pojos.typecode.Comment;
import pojos.typecode.Post;
import pojos.gorest.User;


import static io.restassured.RestAssured.*;

public class Deserialization_POJO {

    @Test(description = "basic deserialization ")
    void test01() {
        Post post = given().get("https://jsonplaceholder.typicode.com/posts/1").as(Post.class);

        System.out.println(post.toString());
    }

    @Test(description = "basic deserialization ")
    void test02() {
        Comment comment = given().get("https://jsonplaceholder.typicode.com/comments/1").as(Comment.class);

        System.out.println(comment.toString());
    }

    @Test(description = "double layered POJOs")
    void test03() {

        Employee employee = given().get("http://dummy.restapiexample.com/api/v1/employee/1").as(Employee.class);

        System.out.println(employee.getData().getEmployee_salary());
    }

    @Test(description = "double layered POJOs")
    void test04() {

        User user = given().get("https://gorest.co.in/public-api/users/14").as(User.class);

        System.out.println(user.toString());
        System.out.println();
        System.out.println(user.getData().getEmail());
    }

    @Test(description = "Deserialize list of courses")
    void test05() {
        SdetCourse sdetCourse = given()
                .queryParam("name", "API Course")
                .queryParam("name", "Google")
                .get("https://tla-school-api.herokuapp.com/api/school/programs/sdetcourse")
                .as(SdetCourse.class);

        System.out.println(sdetCourse.getData().get(0).getName());
        System.out.println(sdetCourse.getData().get(3).getName());
    }

    //NOTE: Serializing/Deserializzing

    @Test
    void test06() {
        String data = "{\n" +
                "    \"code\": 200,\n" +
                "    \"meta\": null,\n" +
                "    \"data\": {\n" +
                "        \"id\": 2157,\n" +
                "        \"name\": \"Jason Grey\",\n" +
                "        \"email\": \"ekadant_abbott_ret@prohaska-johnston.com\",\n" +
                "        \"gender\": \"Female\",\n" +
                "        \"status\": \"Active\",\n" +
                "        \"created_at\": \"2020-09-27T20:50:15.240+05:30\",\n" +
                "        \"updated_at\": \"2020-09-27T20:50:15.240+05:30\"\n" +
                "    }\n" +
                "}";

        System.out.println(data);
        System.out.println();

        // Derialize
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User user = gson.fromJson(data, User.class);
        System.out.println(user.toString());
        System.out.println(user.getData().getEmail());
        System.out.println();

        // Serialize
        String s = gson.toJson(user);
        System.out.println(s);
    }




}
