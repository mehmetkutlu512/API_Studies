package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GmiBankBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp()  {
        spec = new RequestSpecBuilder().setContentType(ContentType.JSON).addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJrX3R3YWluIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9NQU5BR0VSIiwiZXhwIjoxNjg2MTU5OTQwfQ.0b_sKSyzaHJiYZWQOIVSi4xFZbtuCCWr4m3RYQYy3T4V1WlGFQ42GW2omCyRDERNq4m4AozN87kdSk22fnoQzw").setBaseUri("https://gmibank.com/").build();
    }
}
