package mealB;

import io.restassured.response.Response;
import org.junit.Test;
import utils.ReusableMethods;

import static io.restassured.RestAssured.given;

public class DashboardAPI extends MealBReusableMethods {

    @Test
    public void getDashboardinfo(){
        String dashboardURL = "http://dev-mb.yoll.io/api/dashboard";
        Response response = given().
                            header("Authorization","Bearer " + getToken()).
                            when().
                            get(dashboardURL);
        response.prettyPrint();
    }



}
