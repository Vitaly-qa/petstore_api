package tests;

import data.PetData;
import lombok.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static specs.PetResponse.petsResponseSpec;
import static specs.PetRequest.petRequestSpec;
import static specs.UserRequest.userRequestSpec;
import static specs.UserResponse.userResponseSpec;


public class ApiTests extends TestBase {

    @Test
    @DisplayName("Добавляем нового питомца в магазин")
    void addingANewPetToTheStore() {
        PetModel pet = new PetModel();
        pet.setName(PetData.generatePetName());
        pet.setStatus(PetData.generatePetStatus());

        given()
                .filter(withCustomTemplates())
                .spec(petRequestSpec)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(petsResponseSpec)
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));
    }

    @Test
    @DisplayName("Поиск питомцев по статусу")
    void petSearchByStatus() {
        PetStatusModel petStatus = new PetStatusModel();
        petStatus.setStatus("pending");
        given()
                .spec(petRequestSpec)
                .queryParam("status", petStatus.getStatus())
                .when()
                .get("/pet/findByStatus")
                .then()
                .spec(petsResponseSpec)
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
                .spec(petRequestSpec)
                .when()
                .put("/pet")
                .then()
                .spec(petsResponseSpec)
                .body("name", equalTo("Charlik"))
                .body("status", equalTo("sold"));
    }

    @Test
    @DisplayName("Создание нового пользователя")
    void createUser() {
        UserDataModel createUserData = new UserDataModel();
        given()
                .spec(userRequestSpec)
                .body(createUserData)
                .when()
                .post("/user")
                .then()
                .spec(userResponseSpec)
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
                .spec(userRequestSpec)
                .body(loginRequest)
                .when()
                .get("/user/login")
                .then()
                .spec(userResponseSpec)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());


    }

    @Test
    @DisplayName("Обновленные данные пользователя")
    void updatedUser() {
        UserUpdatedModel updatedUserData = new UserUpdatedModel();
        updatedUserData.setUsername("Vitalik QA Engineer");
        given()
                .spec(userRequestSpec)
                .body(updatedUserData)
                .when()
                .put("/user/{username}", "Vitalik QA Engineer")
                .then()
                .spec(userResponseSpec)
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
                .spec(userRequestSpec)
                .queryParam("username", "Vitalik")
                .queryParam("password", "7474")
                .when()
                .get("/user/logout")
                .then()
                .spec(userResponseSpec)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());


    }


}

