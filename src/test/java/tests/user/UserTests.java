package tests.user;

import io.qameta.allure.Step;
import lombok.user.UserDataModel;
import lombok.user.UserLoginModel;
import lombok.user.UserLogoutModel;
import lombok.user.UserUpdatedModel;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Создание нового пользователя")
    void createUser() {
        UserDataModel createUserData = new UserDataModel();

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
    @DisplayName("Успешный логин пользователя")
    void successfulUserLogin() {
        UserLoginModel loginRequest = new UserLoginModel();
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
    @DisplayName("Обновленные данные пользователя")
    void updatedUser() {
        UserUpdatedModel updatedUserData = new UserUpdatedModel();
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
    @DisplayName("Успешный разлогин пользователя")
    void successfulUserLogout() {
        UserLogoutModel logoutUserData = new UserLogoutModel();
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
