package tests;

import data.UserFactory;
import models.user.UserData;
import models.user.UserLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.UserApiSteps;
import static io.qameta.allure.Allure.step;
import static specs.BaseRequestSpecs.userRequestSpec;
import static specs.ResponseSpecs.userResponseSpec;



public class UserTests extends TestBase {

    UserApiSteps userSteps = new UserApiSteps(userRequestSpec, userResponseSpec);
    UserFactory userFactory = new UserFactory();

    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Создание нового пользователя")
    void createUser() {
        UserData createUserData = userFactory.generateUser();

        step("Создаем нового пользователя", () -> {
            userSteps.createUser(createUserData);
        });
    }

    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Успешный логин пользователя")
    void successfulUserLogin() {
        UserLogin loginRequest = userFactory.generateUserLogin("Vitalik", "7474");

        step("Выполняем логин пользователя", () -> {
            userSteps.loginUser(loginRequest);
        });
    }

    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Обновляем данные пользователя")
    void updatedUser() {
        UserData updatedUserData = userFactory.generateUserForUpdate();

        step("Обновляем данные пользователя", () -> {
            userSteps.updateUser(updatedUserData, "Vitalik QA");
        });
    }

    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Успешный разлогин пользователя")
    void successfulUserLogout() {
        userSteps.logoutUser("Vitalik QA", "7474");
    }

    @Test
    @Tag("User")
    @Tag("negative")
    @DisplayName("Поиск несуществующего пользователя")
    void searchNonExistentUser() {
        userSteps.searchNonExistentUser("NonExistentUser");
    }
}



