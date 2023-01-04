package mealB;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetMonthlyExpensesAPI extends MealBReusableMethods{

    @Test
    public void getMonthlyExpenses(){
        String monthlyExpensesURL = "http://dev-mb.yoll.io/api/expenses/getMonthlyExpenses";
        Response response = given().
                            header("Authorization","Bearer " + getToken()).
                            when().
                            get(monthlyExpensesURL);
        response.prettyPrint();
    }


}
