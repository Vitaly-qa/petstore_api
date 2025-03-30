package tests;

import data.PetData;
import data.UserData;
import io.restassured.RestAssured;
import lombok.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;


public class ApiTests {

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
    }

    @Test
    @DisplayName("Добавляем нового питомца в магазин/")
    void addingANewPetToTheStore() {
        PetModel pet = new PetModel();
        pet.setName(PetData.generatePetName());  // Генерация случайного имени питомца
        pet.setStatus(PetData.generatePetStatus());

        given()
                .body(pet)
                .contentType(JSON)
                .when()
                .log().uri()
                .post("/pet")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));
    }

    @Test
    @DisplayName("Поиск питомцев по статусу")
    void petSearchByStatus() {
        PetStatusModel petStatus = new PetStatusModel();
        petStatus.setStatus("pending");
        given()
                .contentType(JSON)
                .queryParam("status", petStatus.getStatus())
                .when()
                .log().uri()
                .get("/pet/findByStatus")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("status", hasItem(petStatus.getStatus()));

    }

    @Test
    @DisplayName("Обновляем информацию о питомце")
    void updatingInformationAboutThePet() {
        PetModel pet = new PetModel();
        pet.setName("Charlik");
        pet.setStatus("sold");
        given()
                .body(pet)
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
    @DisplayName("Создание нового пользователя")
    void createUser() {
        UserDataModel createUserData = new UserDataModel();
        createUserData.setId(UserData.generateUserId());  // Генерация случайного ID
        createUserData.setUsername(UserData.generateUsername());  // Генерация случайного имени пользователя
        createUserData.setLastName(UserData.generateLastName());

        given()
                .body(createUserData)
                .contentType(JSON)
                .when()
                .post("/user")
                .then()
                .log().status()  // Логируем статус ответа
                .log().body()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());

    }

    @Test
    @DisplayName("Успешный логин пользователя")
    void successfulUserLogin() {
        UserLoginModel loginRequest = new UserLoginModel();
        loginRequest.setUsername("Vitalik");
        loginRequest.setPassword("7474");
        given()
                .body(loginRequest)
                .contentType(JSON)
                .body(loginRequest)
                .when()
                .log().uri()
                .get("/user/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue()); // Доработать убрать логин в папку


    }

    @Test
    @DisplayName("Обновленные данные пользователя")
    void updatedUser() {
        UserUpdatedModel updatedUserData = new UserUpdatedModel();
        updatedUserData.setUsername("Vitalik QA Engineer");
        given()
                .body(updatedUserData)
                .contentType(JSON)
                .when()
                .log().uri()
                .put("/user/{username}", "Vitalik QA Engineer")  // Параметр передается в путь
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());

    }


    @Test
    @DisplayName("Успешный разлогин пользователя")
    void successfulUserLogout() {
        UserLogoutModel logoutUserData = new UserLogoutModel();
        logoutUserData.setUsername("Vitalik QA Engineer");
        given()
                .contentType(JSON)
                .queryParam("username", "Vitalik")
                .queryParam("password", "7474")
                .when()
                .log().uri()
                .get("/user/logout")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());


    }


}

