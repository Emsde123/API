package mealB;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetExpensesAPI extends MealBReusableMethods{

    /*
    *  Create token request
    *  Submit token request
    *  Retrieve token from response
    *  Submit GET request to GetExpensesAPI and add token
    * */

    @Test
    public void getExpensesList(){
        String getExpenseURL = "http://dev-mb.yoll.io/api/expenses";
        Response response = given().
                            header("Authorization", "Bearer " + getToken()).
                            when().
                            get(getExpenseURL);
        response.prettyPrint();
    }



}
