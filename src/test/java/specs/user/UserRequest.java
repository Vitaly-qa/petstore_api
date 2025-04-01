package specs.user;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class UserRequest {
    public static RequestSpecification userRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .contentType(JSON);
}
