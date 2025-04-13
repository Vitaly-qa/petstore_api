package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class ResponseSpecs {
    public static ResponseSpecification userResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    // ResponseSpecification для успешного запроса (200)
    public static ResponseSpecification petsResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    // ResponseSpecification для ошибки 400 (Bad Request)
    public static ResponseSpecification notFoundResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(405)
            .build();
}

