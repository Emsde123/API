package gorest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class CreateUser {

    // GET vs POST calls
    // With GET calls we don't need authentication with GoRest API, but in real world environment we will usually need authentication for doing anything.
    //
    //
    //
    // POST calls require a body, because we have to have the data we're creating.
    // POST calls also require headers, to define the content type and give authorization.
    // SERVER CAN NOT READ YOUR DATA WITHOUT POINTING WITH HEADERS



    @Test
    public void createNewUser(){
        String url = "https://gorest.co.in/public/v1/users";
        String requestData = "{\n" +
                "    \"name\": \"Daniel Bee\",\n" +
                "    \"email\": \"daniel_Intuill231@google.com\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"active\"\n" +
                "}";


        Response resp = given().
                        header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
//                        header("Content-Type", "application/json").   <-- This line and the line below both accomplish the same thing.
        //                we need to define the content type, the server can't decipher String into JSON without help.
                        contentType(ContentType.JSON).
                        body(requestData).
                        when().
                        post(url);

        resp.prettyPrint();

        // 2 different ways to validate our status code
        assertEquals(201, resp.getStatusCode());
        resp.then().statusCode(201);

        int statusCode = resp.getStatusCode();
        System.out.println(statusCode);

    }

}
