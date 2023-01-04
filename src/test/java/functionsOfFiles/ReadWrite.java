package functionsOfFiles;
import java.io.File;
import java.io.IOException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class ReadWrite {
    //1- Read Request Data file and assign it to a String Variable
    //2- Submit POST request to GoRest API
    //3- Write response to a responseData file.

    @Test
    public void testReadWrite() throws IOException {

   // 1
        String requestFilePath = "src/test/java/resources/testData/GorestRequestData.json";
        File reqFile = new File(requestFilePath);
        FileUtils.readFileToString(reqFile);
        String reqData = FileUtils.readFileToString(reqFile);

    // 2
        String url = "https://gorest.co.in/public/v2/users";
        Response resp = given().
                header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75").
                body(reqData).
                contentType(ContentType.JSON).
                when().post(url);

    //print
            resp.prettyPrint();
    //validate status code RestAssured
            resp.then().statusCode(201);
    //validate status code with JUnit
        assertEquals(201, resp.getStatusCode());


    //3
        String responseFilePath = "src/test/java/resources/testData/responseData.json";
        File respFile = new File(responseFilePath);
    // Write response data to file
        FileUtils.writeStringToFile(respFile, resp.asPrettyString());


    // schema validation (the purpose is to validate the data structure)
        resp.then().assertThat().body(matchesJsonSchemaInClasspath("newSchema.json"));

    // What data types does JSON handle?
        // String
        // int
        // boolean
        // Array



    }


}
