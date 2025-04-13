package steps;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.user.UserData;
import models.user.UserLogin;
import specs.ResponseSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static specs.BaseRequestSpecs.baseRequestSpec;

public class UserApiSteps {

    private final RequestSpecification getSuccessResponseSpec;
    private final ResponseSpecification userResponseSpec;

    public UserApiSteps(RequestSpecification userRequestSpec, ResponseSpecification userResponseSpec) {
        this.getSuccessResponseSpec = userRequestSpec;
        this.userResponseSpec = userResponseSpec;
    }

    // Метод для создания нового пользователя
    public void createUser(UserData userData) {
        given(baseRequestSpec)
                .body(userData)
                .when()
                .post("/user")
                .then()
                .spec(userResponseSpec)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());
    }

    // Метод для логина пользователя
    public void loginUser(UserLogin loginRequest) {
        given(baseRequestSpec)
                .body(loginRequest)
                .when()
                .get("/user/login")
                .then()
                .spec(ResponseSpecs.getSuccessResponseSpec())
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());
    }

    // Метод для разлогина пользователя
    public void logoutUser(String username, String password) {
        given(baseRequestSpec)
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get("/user/logout")
                .then()
                .spec(ResponseSpecs.getSuccessResponseSpec())
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());
    }

    // Метод для обновления данных пользователя
    public void updateUser(UserData updatedUserData, String username) {
        given(baseRequestSpec)
                .body(updatedUserData)
                .when()
                .put("/user/{username}", username)
                .then()
                .spec(ResponseSpecs.getSuccessResponseSpec())
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());
    }

    // Метод для поиска несуществующего пользователя
    public void searchNonExistentUser(String username) {
        given(baseRequestSpec)
                .log().all()
                .when()
                .get("/user/{username}", username)
                .then()
                .statusCode(404)
                .body("message", equalTo("User not found"));
    }

    @Step("Удаляем пользователя с именем: {username}")
    public void deleteUser(String username) {
        given()
                .spec(baseRequestSpec)
                .when()
                .delete("/user/{username}", username)
                .then()
                .statusCode(200)
                .body("message", equalTo(username));
    }
}


