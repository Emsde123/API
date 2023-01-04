package functionsOfFiles;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ReadFile {
    @Test
    public void readDataFromFile() throws IOException {




    // path of the file
String filePath = "src/test/java/resources/testData/GorestRequestData.json";

// create an object of FIle class to read the file
File file = new File(filePath);

// read file
    FileUtils.readFileToString(file);

    // Read the data and assign it to String
        String myData = FileUtils.readFileToString(file);

        String url = "https://gorest.co.in/public/v1/users";
        Response response = given()
                                .header("Authorization", "Bearer 3c14d8c415a13ae682f1934c83f004836f44f1325a3a0c38b68c55f03ade3e75")
                                .body(myData)
                .contentType(ContentType.JSON)
                .when().post(url);

        System.out.println(myData);

     }
}
