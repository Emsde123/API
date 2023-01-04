package gorest;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.*;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestScenarios {

    // validate gender
    @Test
    @Ignore
    public void validateGender(){

        // we need to use v1 because v1 has metadata and normal data
        // but in v2 we don't have any array name being mentioned, so we can't use JSON path.
        String url = "https://gorest.co.in/public/v1/users";

        Response resp = given().
                        param("gender", "female").
                        when().get(url);

        // We can read Json data with the .read() method.
        // It will take two parameters: JSON data, JSON path. (JSON data should be in String format)
        // JsonPath.read(jsonData, "$.yourJsonPath");


        // Grabbing all genders from our response and adding them into a list.
//        JsonPath.read(resp.asString(), "$.data[*].gender");
        List<String> genderList =      JsonPath.read(resp.asString(), "$.data[*].gender");
        System.out.println(genderList);
        // ["female","female","female","female","female","female","female","female","female","female"]

        // all the genders are female because we specified we're grabbing data for females with our Response.

        // Now we only need to validate gender
        for(String eachGender : genderList){
            Assert.assertEquals("female", eachGender);
        }






    }
// validate status
    @Ignore
    @Test
    public void validateStatus(){
        String url = "https://gorest.co.in/public/v1/users";
        Response resp = given().
                        param("status", "active")
                        .when().get(url);

        String strResponse = resp.prettyPrint();
        List<String> statusList = JsonPath.read(strResponse, "$.data[*].status");
        System.out.println(statusList);
        //["active","active","active","active","active","active","active","active","active","active"]
        for(String eachStatus : statusList){
            Assert.assertEquals("active", eachStatus);
        }

    }


    @Test
    public void validateTotalFemaleUsers(){
        String url = "https://gorest.co.in/public/v1/users";
        Response resp = given().
                        param("gender", "female").
                        param("status", "active").
                        when().get(url);
        String strResponse = resp.prettyPrint();
        String firstActiveFemalesName = JsonPath.read(strResponse, "$.data[0].name" );
        System.out.println(firstActiveFemalesName);
        int totalFemaleUsers =JsonPath.read(strResponse, "$.meta.pagination.total");
        System.out.println(totalFemaleUsers);

        // Get all users info
        // In this scenario we have ints, and Strings for user data, because it's in key value format we can use maps
        List<Map<String, Object>> allUsersMap = JsonPath.read(strResponse, "$.data[*]");
        for(Map<String, Object> map : allUsersMap){
            System.out.println(map);
        }
    }



}
