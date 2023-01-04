package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import serializaion.GoRestRequestBuilder;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class ReusableMethods {

   private static String url = "https://gorest.co.in/public/v1/users";

    /*
     * 1. Create a GorestUser with POST method
     * 2. Validate new User is created by GET with ID.
     * 3. Update user name and Status with PUT method
     * 4. Validate new user is updated by GET with ID.
     * 5. Delete user by DELETE method.
     * 6. Validate user is deleted by GET by with Id.
     */


    // This method will create request data and then convert it to a String using our other method
    // that we created.
    public static String createRequestData(String name, String email, String gender, String status){
        GoRestRequestBuilder reqObj = new GoRestRequestBuilder();
        reqObj.setName(name);
        reqObj.setEmail(email);
        reqObj.setGender(gender);
        reqObj.setStatus(status);
       String requestData = convertObjectToString(reqObj);
       return requestData;
    }



    public static String convertObjectToString(Object reqObject) {
        ObjectMapper objMap = new ObjectMapper();
        String strReqData = "";
        try{
            strReqData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(reqObject);

        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
       return strReqData;
    }

    // 1.
    public static Response createGoRestUser(String requestData){

        Response response = given().
                header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                body(requestData).
                contentType(ContentType.JSON).
                when().post(url);

        response.prettyPrint();
        return response;
    }


    // 2.
    // 3.
    // 4. Validate new user is created by GET with ID
    // We're returning a response so we can validate status codes
    public static Response getGoRestUserByID(int recordID){
        Response getResponse = given().
                header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                when().get(url + "/" + recordID);
        getResponse.prettyPrint();
        return getResponse;
    }

    // Step 3
    public static Response updateGoRestUser(String requestData, int recordID){
        Response putResponse = given().
                header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                contentType(ContentType.JSON).
                body(requestData).
                when().put(url + "/" + recordID);

        putResponse.prettyPrint();
        return putResponse;
    }

    // 5
    public static Response deleteGoRestUser(int recordID){
        Response deleteResponse = given().
                header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                when().delete(url + "/" + recordID);
        return deleteResponse;
    }


}
