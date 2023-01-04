package Homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class PatchRequest {
    @Test
    public void patchUserAndValidateResponse(){
        String url = " https://gorest.co.in/public/v1/users/3624";

        String requestData = "    {\n" +
                "            \"name\": \"Haritam Abdullah\",\n" +
                "            \"gender\": \"male\"\n" +
                "        }";

        Response response = given().
                                    header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                                    contentType(ContentType.JSON).
                                    body(requestData).
                                    when().
                                    patch(url);

        response.prettyPrint();
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getContentType());

    }

}
