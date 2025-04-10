package specs.pet;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class PetRequest {
    public static RequestSpecification petRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .contentType(JSON);
}
