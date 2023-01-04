package functionsOfFiles;
import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import io.restassured.response.Response;
public class WriteFile {
    @Test
    public void writeDataToFile() throws IOException {

    // Content that we want to write into file
        String myData = "I'm learning to poop my shit into a file";

    // file path
//        String filePath = "src/test/java/resources/testData/responseData.json";

    // Creating file object from file path
//    File file = new File(filePath);

    // write myData content into file using FileUtils class
//    FileUtils.writeStringToFile(file, myData);


    String url = "https://gorest.co.in/public/v1/users";

    Response response = given()
            .when()
            .get(url);

    String strResponse = response.prettyPrint();
    // file path
    String filePath = "src/test/java/resources/testData/responseData.json";
    // create new object from filepath
    File file = new File(filePath);

    // writing our response to file
    FileUtils.writeStringToFile(file, strResponse);


    }
}
