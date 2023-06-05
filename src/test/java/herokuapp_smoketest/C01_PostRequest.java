package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C01_PostRequest extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
    }

    When
        Send post request
    Then
        Status Code is 200
    And
        Body:
    {
    "bookingid": 5141,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}

     */

    public static int bookingId;

    @Test
    public void post01() {
        //Set the Url
        spec.pathParam("first", "booking");

        //Set the expected data
        BookingDatesPojo bookingdates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jim","Brown",111,true,bookingdates,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        BookingResponsePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingdates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingdates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

        bookingId = response.jsonPath().getInt("bookingid");
        //Post request ile oluşturulan bookingId değerine başka işlemlerde kullanılmak üzere diğer classlardan ulaşılabilmesi için
        //static bir conteyner içerisine atama yapıyoruz.
    }
}
