package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.user.UserData;
import models.user.UserLogin;
import static io.restassured.RestAssured.given;
import static specs.ResponseSpecs.getFullLogResponseSpec;

public class UserApiSteps {

    private final RequestSpecification requestSpec;

    public UserApiSteps(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    @Step("Создаем пользователя")
    public void createUser(UserData user) {
        given(requestSpec)
                .body(user)
                .when()
                .post("/user")
                .then()
                .spec(getFullLogResponseSpec(200));
    }

    @Step("Логиним пользователя")
    public void loginUser(UserLogin userLogin) {
        given(requestSpec)
                .queryParam("username", userLogin.getUsername())
                .queryParam("password", userLogin.getPassword())
                .when()
                .get("/user/login")
                .then()
                .spec(getFullLogResponseSpec(200));
    }

    @Step("Разлогиниваем пользователя")
    public void logoutUser() {
        given(requestSpec)
                .when()
                .get("/user/logout")
                .then()
                .spec(getFullLogResponseSpec(200));
    }

    @Step("Удаляем пользователя: {username}")
    public void deleteUser(String username) {
        given(requestSpec)
                .when()
                .delete("/user/{username}", username)
                .then()
                .spec(getFullLogResponseSpec(200));
    }

    @Step("Проверяем, что пользователь {username} не существует")
    public void searchNonExistentUser(String username) {
        given(requestSpec)
                .when()
                .get("/user/{username}", username)
                .then()
                .spec(getFullLogResponseSpec(404)); // Ожидаем 404
    }

    @Step("Обновляем пользователя {existingUsername}")
    public ValidatableResponse updateUser(String existingUsername, UserData updatedUserData) {
        return given(requestSpec)
                .body(updatedUserData)
                .when()
                .put("/user/{username}", existingUsername)
                .then();
    }

    @Step("Поиск пользователя {username}")
    public ValidatableResponse searchUser(String username) {
        return given(requestSpec)
                .when()
                .get("/user/{username}", username)
                .then();
    }
}


