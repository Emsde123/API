package serializaion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class CreateUserFromObject {

    // 1. Create a JSON data from object
    // 2. Convert Object to String (print String)
    // 3. Submit POST request
    // 4. Validate the status code


    @Test
    public void createUserFromJavaObject() throws JsonProcessingException {
        // 1. Create a JSON data from object
        GoRestRequestBuilder reqObj = new GoRestRequestBuilder();
        reqObj.setName("Daniel");
        reqObj.setEmail("DeeBeez@myEmailz.com");
        reqObj.setGender("male");
        reqObj.setStatus("active");

        //2. convert object to String (print String)
        ObjectMapper mapper= new ObjectMapper();
        String requestData= mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reqObj);
        System.out.println(requestData);

        //3. Submit POST request
        String url= "https://gorest.co.in/public/v1/users";
        Response resp = given().
                        header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
        //              ALWAYS ALWAYS NEED CONTENT TYPE WHEN MODIFYING OR POSTING DATA
                        contentType(ContentType.JSON).
        //              We can either pass GoRestBuilder object or the String we passed the JSON data into.
                        body(reqObj).
                        when().
                        post(url);

        resp.prettyPrint();

        // 4. validate the status code
        assertEquals(201, resp.getStatusCode());



    }



}
