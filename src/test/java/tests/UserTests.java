package tests;

import data.UserFactory;
import models.user.UserData;
import models.user.UserLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.UserApiSteps;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.*;
import static specs.BaseRequestSpecs.baseRequestSpec;


public class UserTests extends TestBase {

    UserApiSteps userSteps = new UserApiSteps(baseRequestSpec);
    UserFactory userFactory = new UserFactory();

    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Cоздание, логин, разлогин и удаление пользователя")
    void fullCycleTest() {

        UserData UserData = userFactory.generateUser();
        String username = UserData.getUsername();
        String password = UserData.getPassword();

        step("Создаем нового пользователя", () -> {
            userSteps.createUser(UserData);
        });


        UserLogin loginRequest = userFactory.generateUserLogin(username, password);
        step("Выполняем логин пользователя", () -> {
            userSteps.loginUser(loginRequest);
        });


        step("Выполняем разлогин пользователя", () -> {
            userSteps.logoutUser();
        });


        step("Удаляем пользователя", () -> {
            userSteps.deleteUser(username);
        });


        step("Проверяем, что пользователь удален", () -> {
            userSteps.searchNonExistentUser(username);
        });
    }


    @Test
    @Tag("User")
    @Tag("negative")
    @DisplayName("Поиск несуществующего пользователя")
    void searchNonExistentUser() {
        String nonExistentUsername = "NonExistentUser";

        UserApiSteps userApiSteps = new UserApiSteps(baseRequestSpec);

        step("Поиск несуществующего пользователя", () -> {
            userApiSteps.searchUser(nonExistentUsername)
                    .statusCode(404)
                    .body("message", equalTo("User not found"));
        });
    }

    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Обновляем данные пользователя (сгенерированное имя)")
    void updatedUsersWithGeneratedName() {
        UserData userData = userFactory.generateUser();
        UserApiSteps userApiSteps = new UserApiSteps(baseRequestSpec);

        step("Обновляем данные пользователя", () -> {
            userApiSteps.updateUser(userData.getUsername(), userData)
                    .body("code", is(200))
                    .body("type", is("unknown"))
                    .body("message", notNullValue());
        });
    }
}




