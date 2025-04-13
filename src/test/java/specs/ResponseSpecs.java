package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class ResponseSpecs {

    // Метод для получения ResponseSpecification для успешного запроса (200)
    public static ResponseSpecification getSuccessResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200) // Статус 200 для успешных запросов
                .log(STATUS)  // Логирование статуса
                .log(BODY)    // Логирование тела ответа
                .build();     // Строим спецификацию
    }

    // Метод для получения ResponseSpecification для ошибки с указанным статусом
    public static ResponseSpecification getErrorResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)  // Указываем динамический код ошибки
                .log(STATUS)  // Логируем статус ошибки
                .log(BODY)    // Логируем тело ошибки
                .build();     // Строим спецификацию
    }
}

