package mealB;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.Test;
import utils.ReusableMethods;

import static io.restassured.RestAssured.given;

public class TokenAPI extends MealBReusableMethods {
    @Test
    public void testTokenAPIMethods(){
        generateToken();
    }

    @Ignore
    @Test
    public void testToken(){
    String url = "http://dev-mb.yoll.io/api/tokenauth/authenticate";
        // Create Token request payload
        MealBTokenRequestBuilder tokenObject = new MealBTokenRequestBuilder();
        tokenObject.setUsernameOrEmailAddress("ElshanRasul");
        tokenObject.setPassword("Elshan123");
        // convert object to String
       String strRequestData = ReusableMethods.convertObjectToString(tokenObject);
        // submit POST request to Token API
    Response response =  given().
            body(strRequestData).
            contentType(ContentType.JSON).
            when().
            post(url);
    }


}
