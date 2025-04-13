package tests;

import data.UserFactory;
import models.user.UserData;
import models.user.UserLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specs.ResponseSpecs;
import steps.UserApiSteps;
import static io.qameta.allure.Allure.step;
import static specs.BaseRequestSpecs.baseRequestSpec;




public class UserTests extends TestBase {

    UserApiSteps userSteps = new UserApiSteps(baseRequestSpec, ResponseSpecs.getSuccessResponseSpec());  // Вызов метода для получения спецификации
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

    @Test
    @Tag("User")
    @Tag("positive")
    @DisplayName("Удаление существующего пользователя по имени")
    void deleteExistingUserByUsername() {
        String username = "Vitalik QA"; // имя пользователя, которого мы будем удалять

        step("Удаляем существующего пользователя по имени", () -> {
            userSteps.deleteUser(username);
        });

        step("Проверяем, что пользователь удален", () -> {
            userSteps.searchNonExistentUser(username);
        });
    }
}



