package tests;

import data.UserFactory;
import models.user.UserData;
import models.user.UserLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.UserApiSteps;
import static io.qameta.allure.Allure.step;
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
}

