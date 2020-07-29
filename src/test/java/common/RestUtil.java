package common;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestUtil {
    public static Response get(String URL){
        return  given().header("Content-Type", ContentType.JSON)
                .get(URL);
    }
}
