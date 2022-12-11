package gorest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

// Imports the static method so we can use it without class name
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class UserList {


        // Junit is a framework for unit testing.
        // Annotations are used to run the test. The Junit library contains classes that have annotations.
        // When we write automation tests we use our manual API testing application to help us write.


        // given method is static so we need to use the Class name (RestAssured)
        // We didn't add anything to our header, so we can leave given empty. We're just grabbing the user list.

        // given() - parameters, content type, request body, headers .....
        // given().param("gender", "female").param("status", "active").header("key", "value").body("data")
        // Request body is REQUIRED with PATCH, PUT, POST



        // header() - passes headers to request.
        // username, password, Token, ContentType ... can be passed as header.

        // when()  - methods: get, post, put, delete .......
        // After when() we write one of request methods: get(), post(), put(), delete()
        // .when.get(url);
        // then()  - response validation: content type, status code ......
        @Test
        public void getUserList(){


        String url = "https://gorest.co.in/public/v1/users";

        Response resp = given()
               // .param("ParamKey", "ParamValue")
                .param("gender", "female")
                .param("status", "active")
                .header("key", "value") // authorization
                .body("data")
                        .when().get(url);


        // Converting response to String
        resp.prettyPrint(); // Prints the full JSON of the first page of UserList.

        // assigns JSON to a String, without making it pretty.
        String strResponse = resp.asString();

        // assigns JSON to a String, in pretty format.
        String strResponse2 = resp.asPrettyString();

        // statusCode()    <- validates the status code with the provided int.
        resp.then().statusCode(200);

        // getStatusCode() <- returns the status code, no validation (We can use assertions though)
        int statusCode = resp.getStatusCode();
        System.out.println(statusCode);
        // Validating status code
        // 1
        resp.then().statusCode(200);
        // 2 (both below lines do the same thing)
        assertEquals(200, resp.getStatusCode());
        assertEquals(200, statusCode);


        // contentType();
        // Validating the content type.
        resp.then().contentType(ContentType.JSON);
        resp.then().contentType("application/json");

        // Validate each value of the JSON response
      int total =  resp.then().extract().path("meta.pagination.total");
            System.out.println(total);
            assertEquals(477, total);
      int pages =  resp.then().extract().path("meta.pagination.pages");
            assertEquals(48, pages);
            System.out.println(pages);
      int page =  resp.then().extract().path("meta.pagination.page");
            System.out.println(page);
            assertEquals(1, page);
      int limit =   resp.then().extract().path("meta.pagination.limit");
            System.out.println(limit);
            assertEquals(10, limit);

}

    }

