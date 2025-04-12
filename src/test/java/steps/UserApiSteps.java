package steps;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.user.UserData;
import models.user.UserLogin;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApiSteps {

    private final RequestSpecification userRequestSpec;
    private final ResponseSpecification userResponseSpec;

    public UserApiSteps(RequestSpecification userRequestSpec, ResponseSpecification userResponseSpec) {
        this.userRequestSpec = userRequestSpec;
        this.userResponseSpec = userResponseSpec;
    }

    // Метод для создания нового пользователя
    public void createUser(UserData userData) {
        given(userRequestSpec)
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
        given(userRequestSpec)
                .body(loginRequest)
                .when()
                .get("/user/login")
                .then()
                .spec(userResponseSpec)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());
    }

    // Метод для разлогина пользователя
    public void logoutUser(String username, String password) {
        given(userRequestSpec)
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get("/user/logout")
                .then()
                .spec(userResponseSpec)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());
    }

    // Метод для обновления данных пользователя
    public void updateUser(UserData updatedUserData, String username) {
        given(userRequestSpec)
                .body(updatedUserData)
                .when()
                .put("/user/{username}", username)
                .then()
                .spec(userResponseSpec)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", notNullValue());
    }

    // Метод для поиска несуществующего пользователя
    public void searchNonExistentUser(String username) {
        given(userRequestSpec)
                .log().all()
                .when()
                .get("/user/{username}", username)
                .then()
                .statusCode(404)
                .body("message", equalTo("User not found"));
    }
}


