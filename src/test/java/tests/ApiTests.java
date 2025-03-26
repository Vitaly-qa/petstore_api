package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
    }

    @Test
    @DisplayName("Добавляем нового питомца в магазин")
    void addingANewPetToTheStore() {
       String petData = "{\"id\":0,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"Nora\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";
    given()
            .body(petData)
            .contentType(JSON)
            .when()
            .log().uri()
            .post("/pet")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("name", equalTo("Nora"))
            .body("status", equalTo("available"));
    }

    @Test
    @DisplayName("Обновляем информацию о питомце")
    void updatingInformationAboutThePet() {
        String petData = "{\"id\":1,\"category\":{\"id\":9223372036854775807,\"name\":\"string\"},\"name\":\"Charlik\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"sold\"}";
        given()
                .body(petData)
                .contentType(JSON)
                .when()
                .log().uri()
                .put("/pet")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("Charlik"))
                .body("status", equalTo("sold"));
    }

    @Test
    @DisplayName("Поиск питомцев по статусу")
    void petSearchByStatus() {
        given()
                .contentType(JSON)
                .queryParam("status","pending")
                .when()
                .log().uri()
                .get("/pet/findByStatus")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("status", hasItem ("pending"));

    }

    @Test
    @DisplayName("Поиск питомца по ID")
    void petSearchByID(){
        given()
                .contentType(JSON)
                .pathParam("petId","9223372036854775807")
                .when()
                .log().uri()
                .get("/pet/{petId}")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(9223372036854775807L));
    }

    @Test
    @DisplayName("Удаление питомца по ID")
    void deletingAPetByID() {
        given()
                .contentType(JSON)
                .pathParam("petId","9223372036854775807" )
                .when()
                .log().uri()
                .delete("/pet/{petId}")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);

    }

    @Test
    @DisplayName("Добавление заказа для питомца")
    void addingAnOrderForAPet() {
        String orderData = "{\"id\":9223372036854775807,\"petId\":0,\"quantity\":0,\"status\":\"placed\",\"complete\":true}";
        given()
                .body(orderData)
                .contentType(JSON)
                .when()
                .log().uri()
                .post("/order")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("status", equalTo("placed")); // что не так в этом тесте ?

    }

    @Test
    void createUser() {
        String createUserData = "{\"id\":0,\"username\":\"fdf\",\"firstName\":\"dfd\",\"lastName\":\"fdf\",\"email\":\"fdf@gsgsg.ru\",\"password\":\"7474\",\"phone\":\"48484\",\"userStatus\":0}";

        given()
                .body(createUserData)
                .contentType(JSON)
                .when()
                .post("/user")
                .then()
                .log().status()  // Логируем статус ответа
                .log().body()    // Логируем тело ответа
                .statusCode(200)
                .body("username", is("fdf"))
                .body("firstName", is("dfd"))
                .body("email", is("fdf@gsgsg.ru"))
                .body("phone", is("48484"))
                .body("id", notNullValue())
                .body("userStatus", is(0)); // Проверка, что статус пользователя равен 0
    }


    }

