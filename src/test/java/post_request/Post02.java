package post_request;

import base_urls.HerOkuAppBaseUrl;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.Map;

public class Post02 extends HerOkuAppBaseUrl {
           /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
    When
       I send POST Request to the Url
    Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
    */

    @Test
    public void post02() {
        //set the url
        spec.pathParam("first", "booking");

        //set the expected data
        HerOkuAppTestData obj = new HerOkuAppTestData();
        Map<String, String> bookingdatesMap =  obj.bookingdatesMapMethod("2021-09-09","2021-09-21");
        Map<String, Object> expectedData =  obj.expectedDataMethod("John", "Doe", 11111, true, bookingdatesMap, "");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
    }
}
