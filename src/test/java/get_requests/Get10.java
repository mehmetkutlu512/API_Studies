package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get10 extends GoRestBaseUrl {
      /*
       Given
           https://gorest.co.in/public/v1/users
       When
           User send GET Request
       Then
           The value of "pagination limit" is 10
       And
           The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
       And
           The number of users should  be 10
       And
           We have at least one "active" status
       And
           "Gov. Vrinda Panicker", "Sen. Devika Embranthiri" and "Rev. Jay Shukla" are among the users
       And
           The female users are less than or equals to male users
           (Kadın kullanıcı sayısı erkek kullanıcı sayısından küçük yada eşit olamlı)
    */

    @Test
    public void get10() {
        //Set the url
        spec.pathParam("first", "users");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        response.then().
                statusCode(200)
                .body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Chandini Trivedi", "Balagovind Pandey", "Yogesh Pilla"));

        //Kadın ve erkek sayılarını karşılaştıralım
        JsonPath jsonPath = response.jsonPath();
        List<String> gendrList = jsonPath.getList("data.gender");
        System.out.println("gendrList = " + gendrList);

        int kadinSayisi = 0;
        for (String w : gendrList){
            if (w.equalsIgnoreCase("female")){
                kadinSayisi++;
            }
        }
        System.out.println("kadinSayisi = " + kadinSayisi);
        assertTrue(kadinSayisi<=(gendrList.size()-kadinSayisi));

        //2.Yol Grovy;

    }
}
