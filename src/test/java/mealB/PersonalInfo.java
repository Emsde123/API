package mealB;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PersonalInfo extends MealBReusableMethods {

    @Test
    public void personalInfo(){
        String personalInfoURL = "http://dev-mb.yoll.io/api/personalinfo";
        Response response = given().
                            header("Authorization", "Bearer " + getToken()).
                            when().
                            get(personalInfoURL);
        response.prettyPrint();
    }

}
