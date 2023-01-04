package functionsOfFiles;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JSONSchemaValidator {

    @Test
    public void printResponse(){
    String url = "https://gorest.co.in/public/v2/users?page=2";
    Response response = given()
                        .when()
                            .get(url);

    // the below method tells selenium to go check the JSON file we created and added the converted JSON schema to.
        // It will check that the data format matches our get request
    response.then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"));
    response.prettyPrint();
    }

}
