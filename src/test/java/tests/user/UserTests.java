package tests.user;

import lombok.user.UserData;
import lombok.user.UserLogin;
import lombok.user.UserLogout;
import lombok.user.UserUpdated;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static specs.user.UserRequest.userRequestSpec;
import static specs.user.UserResponse.userResponseSpec;

public class UserTests extends TestBase {

    @Test
    @Tag("User")
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
    @DisplayName("Обновляем данные пользователя")
    void updatedUser() {
        UserUpdated updatedUserData = new UserUpdated();
        updatedUserData.setUsername("Vitalik QA Engineer");

        step("Обновляем данные пользователя", () -> {
            given(userRequestSpec)
                    .body(updatedUserData)
                    .when()
                    .put("/user/{username}", "Vitalik QA Engineer")
                    .then()
                    .spec(userResponseSpec)
                    .body("code", is(200))
                    .body("type", is("unknown"))
                    .body("message", notNullValue());

        });
    }

    @Test
    @Tag("User")
    @DisplayName("Успешный разлогин пользователя")
    void successfulUserLogout() {
        UserLogout logoutUserData = new UserLogout();
        logoutUserData.setUsername("Vitalik QA Engineer");

        step("Выполняем разлогин пользователя", () -> {
            given(userRequestSpec)
                    .queryParam("username", "Vitalik")
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
}
