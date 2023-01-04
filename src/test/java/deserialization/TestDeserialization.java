package deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import mealB.MealBReusableMethods;
import mealB.PersonalInfo;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestDeserialization extends MealBReusableMethods {




    @Test
    public void test() throws JsonProcessingException {
        // Submit GET request to Personal Info and get response
        String personalInfoURL = "http://dev-mb.yoll.io/api/personalinfo";
        Response response = given().
                header("Authorization", "Bearer " + getToken()).
                when().get(personalInfoURL);
        response.prettyPrint();

        // convert JSON response to JAVA object --> deserialization
        ObjectMapper objMapper = new ObjectMapper();

        // first param is the JSON we want to convert, second value is which class to pass (needs to be parent class)
        PersonalInfoResponse responseObj = objMapper.readValue(response.asString(), PersonalInfoResponse.class);

        // validation of parent element
        System.out.println(responseObj.is__abp());

        // validation of child element


    }


}
