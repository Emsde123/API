package junit;


import org.junit.*;


import static org.junit.Assert.*;

public class Assertions {


    @Test
    // assertTrue()
    // there are 2 types of assertTrue(), one has a message and one does not.
    public void test1(){
        int expectedStatusCode = 200;
        int actualStatusCode = 200;
        assertTrue(expectedStatusCode == 200);
        assertTrue("This shit tweaked", expectedStatusCode == 203);
    }


    // API calls in SOAP protocol will continually call until it gets a response. (stateful)
    // On the other hand, RESTful web services will act more like a text message, it doesn't care if it gets a response or not. (stateless)

    // RestFUL Web service
    // SOAP PROTOCOL

    // DELTA uses SOAP

    // RestAssured library is used to test Restful web services.
    // It allows us to automate API scenarios.

    // Nexus is a program that stores dependencies, we can say that we get our dependencies from Nexus in our interview.
    // NOT MVN.COM NOT MVN.COM NOT MVN.COM NOT MVN.COM NOT MVN.COM NOT MVN.COM NOT MVN.COM NOT MVN.COM NOT MVN.COM NOT MVN.COM



}
