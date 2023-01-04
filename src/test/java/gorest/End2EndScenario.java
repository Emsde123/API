package gorest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import serializaion.GoRestRequestBuilder;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.junit.Assert.assertEquals;


public class End2EndScenario {

    @Test
    public void testEnd2EndScenario() throws JsonProcessingException {

        /*
         * 1. Create a GorestUser with POST method
         * 2. Validate new User is created by GET with ID.
         * 3. Update user name and Status with PUT method
         * 4. Validate new user is updated by GET with ID.
         * 5. Delete user by DELETE method.
         * 6. Validate user is deleted by GET by with Id.
         */

        // 1. Create goRestUser with POST method
        String url = "https://gorest.co.in/public/v1/users";
        GoRestRequestBuilder reqBuilder = new GoRestRequestBuilder();
        reqBuilder.setName("Deeee Beez");
        reqBuilder.setEmail("beeezDevvgddodz@gmail.com");
        reqBuilder.setGender("male");
        reqBuilder.setStatus("active");

                // asString() method is ONLY for JSON, we can't use it here because
                // we're working with a POJO
            // convert object to String
        ObjectMapper objMap = new ObjectMapper();
       String strReqData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(reqBuilder);
        System.out.println("strReqData: " + strReqData);


        // 2. Submit POST request
        Response postRequest = given().
                        header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                        body(strReqData).
                        contentType(ContentType.JSON).
                        when().post(url);

        postRequest.prettyPrint();

                    // validation
        assertEquals(201, postRequest.getStatusCode());

                    // retrieve the account id from response
        // Since our response only has one account we don't have to specify index or anything.
        // we can assign it to int since it's an integer in our JSON body.
        int recordID = JsonPath.read(postRequest.asString(), "$.data.id");


        // 2. Validate new user is created by GET with ID
        Response getResponse = given().
                                header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                                when().get(url + "/" + recordID);

        getResponse.prettyPrint();

        // validation
        assertEquals(200, getResponse.getStatusCode());

        // 3. Update user name and Status with PUT method
             // Since we use the whole body for PUT methods, we're creating a new object
             // with 2 fields instead of 4 to act as the whole JSON body
             // even though we're really updating 2 existing records.
        GoRestRequestBuilder reqObjUpdate = new GoRestRequestBuilder();
        reqObjUpdate.setName("Deenice");
        reqObjUpdate.setEmail("deeupdated3@gmeez.com");
        reqObjUpdate.setGender("male");
        reqObjUpdate.setStatus("active");


        // convert object to String
        ObjectMapper objMapUpdate = new ObjectMapper();
       String updatedFields = objMapUpdate.writerWithDefaultPrettyPrinter().writeValueAsString(reqObjUpdate);
        System.out.println("Updated fields: " + updatedFields );
       Response putResponse = given().
                              header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                              contentType(ContentType.JSON).
                              body(updatedFields).
                              when().put(url + "/" + recordID);

       putResponse.prettyPrint();

        // verify
        assertEquals(200, putResponse.getStatusCode());


        // 4.Validate new user is updated by GET with ID
        Response getResponse2 = given().
                header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                when().get(url + "/" + recordID);

        getResponse2.prettyPrint();
        // validation
        assertEquals(200, getResponse2.getStatusCode());

        // validate name has been updated
        String updatedName = JsonPath.read(getResponse2.asString(), "$.data.name");
        assertEquals("Deenice", updatedName);


        // 5. Delete user by DELETE method
        Response deleteResponse = given().
                                  header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                                  when().delete(url + "/" + recordID);

        // validating account was deleted
        assertEquals(204, deleteResponse.getStatusCode());

        // 6. Validate user is deleted by GET with ID
        Response getResponse3 = given().
                header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                when().get(url + "/" + recordID);

        // validation
        assertEquals(404, getResponse3.getStatusCode());
        String errorMessage = JsonPath.read(getResponse3.asString(), "$.data.message");
        assertEquals(errorMessage, "Resource not found");
    }
}

