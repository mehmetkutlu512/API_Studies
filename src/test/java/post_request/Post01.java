package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Post01 extends JsonPlaceHolderBaseUrl {
        /*
         Given
           1)  https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
                }
        When
         I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test//String ile yapılan
    public void post01() {
        //Set the url
        spec.pathParam("first", "todos");

        //set the expected data -->Json datayı Stringe çevirerek Java objesiolarak kullanıyoruz.
        String payLoad = " {\n\"userId\": 55,\n\"title\": \"Tidy your room\",\n\"completed\": false\n}";
        //String ile post yapmak kolay yöntemdir. Fakat Assert işlemi için tavsiye edilmez.


        //send the request and get the response
        Response response = given(spec).body(payLoad).post("{first}");
        response.prettyPrint();

        //do assertion
        assertEquals(201, response.statusCode());

        JsonPath jsonPath = response.jsonPath();
        //Assert kısmında hard code kullanmamaya özen gösteririz.
        assertEquals(55, jsonPath.getInt("userId") );
        assertEquals("Tidy your room", jsonPath.getString("title") );
        assertFalse(jsonPath.getBoolean("completed"));

    }
//{
//                 "userId": 55,
//                 "title": "Tidy your room",
//                 "completed": false
//                }
    @Test //Map ile
    public void post01Map() {
        //Set the url
        spec.pathParam("first", "todos");

        //set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");//Serialization işlemi yapıldı:
                                                                              //Java objesi Jackson vasıtasıyla Json dataya çevrildi.
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);//De-Serilization işlemi yapıldı:Json datayı Java objesi Map'e çevirdik.Jackson vasıtasıyla.
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));


    }
}
