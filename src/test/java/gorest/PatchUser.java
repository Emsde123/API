package gorest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PatchUser {
    @Test
    public void partialUpdateUser() {
        String url = "https://gorest.co.in/public/v1/users/3155";
        // I have changed the status in requestData to inactive, we're updating the user.
        String requestData = "{\n" +
                "    \"name\": \"Daniel Beez\"\n" +
                "}";

        Response resp = given().
                header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
//                        header("Content-Type", "application/json").   <-- This line and the line below both accomplish the same thing.
                //                we need to define the content type, the server can't decipher String into JSON without help.
                        contentType(ContentType.JSON).
                body(requestData).
                when().
                // changed put to patch
                        patch(url);

        resp.prettyPrint();

        // 2 different ways to validate our status code
        // changed from 201 to 200 because now we're updating and not creating. (Ok)
        assertEquals(200, resp.getStatusCode());
        resp.then().statusCode(200);

        int statusCode = resp.getStatusCode();
        System.out.println(statusCode);
    }


}