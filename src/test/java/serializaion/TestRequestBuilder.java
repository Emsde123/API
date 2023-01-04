package serializaion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class TestRequestBuilder {

    // Creating JSON from Java object is called SERIALIZATION
    // We do this for smaller amounts of data, we must create private variables with corresponding names and data types.
    @Test
    public void createRequestData() throws JsonProcessingException {
        // Creating JSON data from Java object
        GoRestRequestBuilder reqBuilder = new GoRestRequestBuilder();
        reqBuilder.setName("Titans Test");
        reqBuilder.setEmail("TitansTest@Yoll.io");
        reqBuilder.setGender("male");
        reqBuilder.setStatus("active");

        // Convert Java object to String using Object mapper class.
        ObjectMapper objMap = new ObjectMapper();
        // throws JsonProcessingException
     objMap.writerWithDefaultPrettyPrinter().writeValueAsString(reqBuilder);
     // Writing Json data to a string with ObjMap and then printing it for below result
        String jsonData =  objMap.writerWithDefaultPrettyPrinter().writeValueAsString(reqBuilder);
        System.out.println(jsonData);
        //{
        //  "name" : "Titans Test",
        //  "gender" : "male",
        //  "email" : "TitansTest@Yoll.io",
        //  "status" : "active"
        //}

        // By using encapsulation in our framework we've achieved serialization.
        // Serialization:
        // Automate POST requests
        // Implement encapsulation concept
        // add Jackson Databind library
        // Use ObjectMapper class
        //

    }

}
