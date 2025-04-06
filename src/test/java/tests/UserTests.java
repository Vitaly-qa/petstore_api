package tests;

import lombok.user.UserData;
import lombok.user.UserLogin;
import lombok.user.UserLogout;
import lombok.user.UserUpdated;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static specs.user.UserRequest.userRequestSpec;
import static specs.user.UserResponse.userResponseSpec;

public class UserTests extends TestBase {

    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Создание нового пользователя")
    void createUser() {
        UserData createUserData = new UserData();

        step("Создаем нового пользователя", () -> {
            given(userRequestSpec)
                    .body(createUserData)
                    .when()
                    .post("/user")
                    .then()
                    .spec(userResponseSpec)
                    .body("code", is(200))
                    .body("type", is("unknown"))
                    .body("message", notNullValue());

        });
    }

    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Успешный логин пользователя")
    void successfulUserLogin() {
        UserLogin loginRequest = new UserLogin();
        loginRequest.setUsername("Vitalik");
         loginRequest.setPassword("7474");

        step("Выполняем логин пользователя", () -> {
            given(userRequestSpec)
                    .body(loginRequest)
                    .when()
                    .get("/user/login")
                    .then()
                    .spec(userResponseSpec)
                    .body("code", is(200))
                    .body("type", is("unknown"))
                    .body("message", notNullValue());


        });
    }

    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Обновляем данные пользователя")
    void updatedUser() {
        UserUpdated updatedUserData = new UserUpdated();
        updatedUserData.setUsername("Vitalik QA Engineer");

        step("Обновляем данные пользователя", () -> {
            given(userRequestSpec)
                    .body(updatedUserData)
                    .when()
                    .put("/user/{username}", "Vitalik QA Engine")
                    .then()
                    .spec(userResponseSpec)
                    .body("code", is(200))
                    .body("type", is("unknown"))
                    .body("message", notNullValue());

        });
    }

    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Успешный разлогин пользователя")
    void successfulUserLogout() {
        UserLogout logoutUserData = new UserLogout();
        logoutUserData.setUsername("Vitalik QA Engineer");

        step("Выполняем разлогин пользователя", () -> {
            given(userRequestSpec)
                    .queryParam("username", "Vitalik QA Engineer")
                    .queryParam("password", "7474")
                    .when()
                    .get("/user/logout")
                    .then()
                    .spec(userResponseSpec)
                    .body("code", is(200))
                    .body("type", is("unknown"))
                    .body("message", notNullValue());

        });
    }



    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Обновляем данные пользователя")
    void updatedUsers() {
        UserUpdated updatedUserData = new UserUpdated();
        updatedUserData.setUsername("Vitalik QA Engineer");

        step("Обновляем данные пользователя", () -> {
            given(userRequestSpec)
                    .body(updatedUserData)
                    .when()
                    .put("/user/{username}", "Vitalik QA Engine")
                    .then()
                    .spec(userResponseSpec)
                    .body("code", is(200))
                    .body("type", is("unknown"))
                    .body("message", notNullValue());

        });
    }


    @Test
    @Tag("User")
    @Tag("negative")
    @DisplayName("Поиск несуществующего пользователя")
    void searchNonExistentUser() {
        String nonExistentUsername = "NonExistentUser"; // Пример несуществующего пользователя

        step("Поиск несуществующего пользователя", () -> {
            given(userRequestSpec)
                    .log().all()
                    .when()
                    .get("/user/{username}", nonExistentUsername) // Запрос на поиск пользователя
                    .then()
                    .statusCode(404) // Ожидаем код ошибки 404 для несуществующего пользователя
                    .body("message", equalTo("User not found")); // Ожидаем, что в ответе будет сообщение "User not found"
        });
    }
}
