package serializaion;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"gender", "name","email", "status"}) // <--- This annotation allows us to have our JSON properties in a specific order

// THIS CLASS IS A POJO/BEAN CLASS
// POJO =  (POJO stands for Plain Old Java Object)
// BEAN = Getters and Setters correlating to the POJO
public class GoRestRequestBuilder {

    // Creating JSON from Java object is called SERIALIZATION
    // We do this for smaller amounts of data, we must create private variables with corresponding names and data types.

    // @JsonProperty("first-name")  <-- this will allow us to change the variable to the name of the JSON property
    //                                  if the JSON property contains characters not allowed in Java variables.
   private String name;
   private String gender;
    private String email;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
