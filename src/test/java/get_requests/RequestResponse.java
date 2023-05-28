package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestResponse {
/*
    1) Postman manuel test için kullanılır
    2) Otomasyon için Rest-Assured library kullanıyoruz
    3) Otomasyonu yazabilmek için şu adımların izlenmesi gerekir:
        a) Gereksinimleri anlama
        b) Test case yazma:
            -Test yazmak için Gherkin Language kullanılır
            x) Given: Ön koşullar --> Url, Body ...
            y) When: Yapılacak işlemler --> Get, Put, Post ... requests...
            z) Then: Dönütler, çıktılar --> Assertion, close...
            t) And: Art arda yapılan aynı çoklu işlmeleri bağlamak için kullanılır
        c) Otomasyon kodlarını yazma:
            i) Set the url --> endpoint'i kur
            ii) Set the expected data --> beklenen veriyi kur
            iii) Send the request and get the response --> request'i gönder ve response'ı al
            iv) Do assertion --> Doğrulama yap
     */

    public static void main(String[] args) {

        //Get request nasıl yapılır:
        String url = "https://petstore.swagger.io/v2/pet/42";
        Response response = given().get(url);//import static io.restassured.RestAssured.given;
        // response.prettyPrint(); //prettyPrint() methodu ile responu consola yazdırdık.

        //Status code nasıl yazdırılır:
        System.out.println("Status Code = " + response.statusCode());

        //Content Type nasıl yazdırılır:
        System.out.println("Content Type = " + response.contentType());

        //Status Line nasıl yazdırılır:
        System.out.println("Status Line = "+ response.statusLine());

        // Header nasıl yazdırılır:
        System.out.println(response.header("Date"));
        System.out.println(response.header("Connection"));
        System.out.println(response.header("Server"));

        // Headers nasıl yazdırılır:
        System.out.println("******HEADERS*********");
        System.out.println(response.headers());

        // Time nasıl yazdırılır:
        System.out.println("\nTime = " + response.time());

    }
}
