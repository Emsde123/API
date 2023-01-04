package mealB;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class MealBReusableMethods {
    private final String tokenURL = "http://dev-mb.yoll.io/api/tokenauth/authenticate";
    private final String username="ElshanRasul";
    private final String password= "Elshan123";
    private final String createExpenseURL = "http://dev-mb.yoll.io/api/expenses/create";


    public String createOtherExpenseReqData(String name, double amount, String dateTime, int expenseNameId){
        OtherExpenseBuilder reqObj = new OtherExpenseBuilder();
        reqObj.setName(name);
        reqObj.setAmount(amount);
        reqObj.setExpenseType("Other");
        reqObj.setExpenseDateTime(dateTime);
        reqObj.setOtherExpenseNameId(expenseNameId);

        // convert OtherExpenseBuilder object to String
        String requestData = convertObjectToString(reqObj);
        return requestData;
    }

    public Response postCreateExpenseRequest(String requestData){
        Response response = given().
                            header("Authorization", "Bearer " + getToken()).
                            body(requestData).
                            contentType(ContentType.JSON).
                            when().
                            post(createExpenseURL);
        response.prettyPrint();
        return response;
    }


    // We've already created a method to generate a token, this method will retrieve that token.
    public String getToken(){
       String token = JsonPath.read(generateToken(), "$.result.accessToken");
       return token;
    }

    public String generateToken(){
        String reqData = createTokenRequestData(); // <-- we can directly assign to String, or we can pass method to body param, both are the same.
        // submit POST request to Token API
        Response response =  given().
                body(reqData).
                contentType(ContentType.JSON).
                when().
                post(tokenURL);
        return response.prettyPrint();
    }
    public static String convertObjectToString(Object object) {

        String strReqData = null;
        try{
            ObjectMapper objMap = new ObjectMapper();
            strReqData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(object);

        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return strReqData;
    }


    public String createTokenRequestData(){

        // Create Token request payload
        MealBTokenRequestBuilder tokenObject = new MealBTokenRequestBuilder();
        tokenObject.setUsernameOrEmailAddress(username);
        tokenObject.setPassword(password);
        // convert to String
        String payload = convertObjectToString(tokenObject);

        System.out.println(payload);
        return payload;
    }




}
