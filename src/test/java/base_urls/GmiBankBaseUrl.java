package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AuthenticationGmiBank.generateToken;

public class GmiBankBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp()  {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer "+generateToken())
                .setBaseUri("https://gmibank.com/")
                .build();
    }
}

//https://app.swaggerhub.com./apis/yasinaniltechpro/GmiBank/0.0.1#/ -->Swagger dökümanı

//jwt --> json web token

//basic aut (username ve password olacak) veya Bearer token ile authorization yapabiliriz

/*
{
    "password":"Mark.123",
    "rememberMe":true,
    "username":"mark_twain"
}
 */
