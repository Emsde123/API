package gorest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import serializaion.GoRestRequestBuilder;
import utils.ReusableMethods;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class End2EndRefactored {

    /*
     * 1. Create a GorestUser with POST method
     * 2. Validate new User is created by GET with ID.
     * 3. Update user name and Status with PUT method
     * 4. Validate new user is updated by GET with ID.
     * 5. Delete user by DELETE method.
     * 6. Validate user is deleted by GET by with Id.
     */

    @Test
            public void testEnd2EndRefactored(){


    String url = "https://gorest.co.in/public/v1/users";
        // 1.
    String strReqData = ReusableMethods.createRequestData("Danny Beeeez", "dannybeezly@gmail.com", "male", "active");

        // 2. Submit POST request
        Response postResponse = ReusableMethods.createGoRestUser(strReqData);

        // validation
        assertEquals(201, postResponse.getStatusCode());



        // retrieve the account id from response
        // Since our response only has one account we don't have to specify index or anything.
        // we can assign it to int since it's an integer in our JSON body.
        int recordID = JsonPath.read(postResponse.asString(), "$.data.id");


        // 2a. Validate new user is created by GET with ID
       Response getResponse = ReusableMethods.getGoRestUserByID(recordID);
        assertEquals(200, getResponse.getStatusCode());

        // 3. Assigns JSON body to String to use for our PUT request
       String strReqDataUpdate = ReusableMethods.createRequestData("Deenice", "deeupdated@gmail.com", "male", "active");
        // response of our PUT request
        Response putResponse = ReusableMethods.updateGoRestUser(strReqDataUpdate, recordID);
        // 3a. verify user has been updated by getting OK status code
        assertEquals(200, putResponse.getStatusCode());


        // 4.Validate new user is updated by GET with ID
        Response getResponse2 = ReusableMethods.getGoRestUserByID(recordID);
        // 4a. validation
        assertEquals(200, getResponse2.getStatusCode());
        // 4b. validate name has been updated
        String updatedName = JsonPath.read(getResponse2.asString(), "$.data.name");
        assertEquals("Deenice", updatedName);


        // 5. Delete user by DELETE method
        Response deleteResponse = ReusableMethods.deleteGoRestUser(recordID);
        // 5a. validating account was deleted
        assertEquals(204, deleteResponse.getStatusCode());


        // 6. Validate user is deleted by GET with ID
        Response getResponse3 = ReusableMethods.getGoRestUserByID(recordID);
        assertEquals(404, getResponse3.getStatusCode());
        // 6a. Validate error message
        String errorMessage = JsonPath.read(getResponse3.asString(), "$.data.message");
        assertEquals(errorMessage, "Resource not found");


}

}
