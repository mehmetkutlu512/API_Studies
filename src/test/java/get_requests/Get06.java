package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {
   /*
  Given
      https://restful-booker.herokuapp.com/booking/26
  When
      User send a GET request to the URL
  Then
      HTTP Status Code should be 200
  And
      Response content type is "application/json"
  And
      Response body should be like;
           {
              "firstname": "Josh",
              "lastname": "Allen",
              "totalprice": 111,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2018-01-01",
                  "checkout": "2019-01-01"
              },
              "additionalneeds": "super bowls"
           }
    */

    @Test
    public void get06() {
        //set the url
        spec.pathParams("first", "booking", "second", 32);

        //set the expected data

        //send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //do assertion
        //1. yol:
        response.then().
                statusCode(200).
                body("firstname", equalTo("Josh"),
                        "lastname", equalTo("Allen"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("super bowls"));

        //2. Yol: Json Path
        JsonPath jsonPath = response.jsonPath();// jsonPath() methodu ile response'ı jsonPath objesine çevirdik.

        //jsonPath objesi ile dataya spesifik olarak ulaşabiliriz.
        assertEquals("Josh",jsonPath.getString("firstname"));
        assertEquals("Allen", jsonPath.getString("lastname"));
        assertEquals("111", jsonPath.getString("totalprice"));//111'i int veya String olarak alabiliriz.
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
        assertEquals("super bowls", jsonPath.getString("additionalneeds"));

        //3. Yol: TestNG Soft Assertion
        //Soft Assertion adımları:
        //1. Soft Assertion objesi oluştur.
        SoftAssert softAssert = new SoftAssert();

        //2.Assertion yap
        softAssert.assertEquals(jsonPath.getString("firstname"), "Josh", "Firstname uyuşmadı");//mesaj koyabiliriz
        softAssert.assertEquals(jsonPath.getString("lastname"), "Allen");
        softAssert.assertEquals(jsonPath.getString("totalprice"),"111");
        softAssert.assertTrue(jsonPath.getBoolean("depositpaid"), "depositpaid uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01", "checkin uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"), "super bowls");


        //3. assertAll() methodunu kullan
        softAssert.assertAll();

    }
}
