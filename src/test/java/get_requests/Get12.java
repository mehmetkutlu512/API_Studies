package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojos;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2587
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                {
                    "meta": null,
                    "data": {
                        "id": 2587,
                        "name": "Ganapati Prajapat",
                        "email": "prajapat_ganapati@okeefe.org",
                        "gender": "female",
                        "status": "active"
                    }
                }
    */

    @Test
    public void get12() {
        //Set the url
        spec.pathParams("first","users","second","2587");

        //Set the expected data
        GoRestDataPojos goRestDataPojos = new GoRestDataPojos("Ganapati Prajapat", "prajapat_ganapati@okeefe.org", "female", "active");
        GoRestPojo expectedData = new GoRestPojo(null, goRestDataPojos);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();


        //Do assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(goRestDataPojos.getName(),actualData.getData().getName());
        assertEquals(goRestDataPojos.getEmail(),actualData.getData().getEmail());
        assertEquals(goRestDataPojos.getGender(),actualData.getData().getGender());
        assertEquals(goRestDataPojos.getStatus(),actualData.getData().getStatus());

    }
}
