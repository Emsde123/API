package Homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class DeleteRequest {

    @Test
    public void deleteUser(){
        String url = "https://gorest.co.in/public/v1/users/3155";



        Response response = given().
                            header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                            when().delete(url);

        response.prettyPrint();
        Assert.assertEquals(204, response.getStatusCode());
    }

}
