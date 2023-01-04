package mealB;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class OtherExpense extends MealBReusableMethods{
    @Test
    public void scenario1(){
        String requestData = createOtherExpenseReqData("API", 2500, "12/21/2022 00:00:00", 1);
        System.out.println(requestData);

        // submit POST call
       Response response = postCreateExpenseRequest(requestData);

       // validate --> 200
        Assert.assertEquals(200, response.getStatusCode());

       // validate the name --> Electricity
        String actualExpName = JsonPath.read(response.asString(), "$.result.name");
        Assert.assertEquals("Electricity", actualExpName);

    }

    @Test
    public void scenario2(){
        String requestData = createOtherExpenseReqData("API", 2500, "12/21/2022 00:00:00", 2);
        System.out.println(requestData);

        // submit POST call
        Response response = postCreateExpenseRequest(requestData);

        // validate --> 200
        Assert.assertEquals(200, response.getStatusCode());

        // validate the name --> Electricity
        String actualExpName = JsonPath.read(response.asString(), "$.result.name");
        Assert.assertEquals("Rent", actualExpName);

    }

    @Test
    public void scenario3(){
        String requestData = createOtherExpenseReqData("API", 2500, "12/21/2022 00:00:00", 3);
        System.out.println(requestData);

        // submit POST call
        Response response = postCreateExpenseRequest(requestData);

        // validate --> 200
        Assert.assertEquals(200, response.getStatusCode());

        // validate the name --> Electricity
        String actualExpName = JsonPath.read(response.asString(), "$.result.name");
        Assert.assertEquals("Gas", actualExpName);

    }

    @Test
    public void scenario4(){
        String requestData = createOtherExpenseReqData("API", 2500, "12/21/2022 00:00:00", 4);
        System.out.println(requestData);

        // submit POST call
        Response response = postCreateExpenseRequest(requestData);

        // validate --> 200
        Assert.assertEquals(200, response.getStatusCode());

        // validate the name --> Electricity
        String actualExpName = JsonPath.read(response.asString(), "$.result.name");
        Assert.assertEquals("API", actualExpName);

    }

    @Test
    public void scenario5(){
        String requestData = createOtherExpenseReqData("API", 2500, "12/21/2022 00:00:00", 5);
        System.out.println(requestData);

        // submit POST call
        Response response = postCreateExpenseRequest(requestData);

        // validate --> 500
        Assert.assertEquals(500, response.getStatusCode());

        // validate the name --> Electricity
        String actualExpName = JsonPath.read(response.asString(), "$.error.message");
        Assert.assertEquals("An internal error occurred during your request!", actualExpName);
    }


}
