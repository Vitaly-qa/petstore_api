package data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.github.javafaker.Faker;
import models.user.UserData;
import models.user.UserLogin;
import models.user.UserLogout;

@Getter
@Setter
@Accessors(chain = true)
public class UserFactory {

    private final Faker faker = new Faker();

    // Метод для генерации данных для нового пользователя
    public UserData generateUser() {
        return generateUser(faker.name().username(), faker.internet().password(), 1);
    }

    // Метод для генерации данных для пользователя с конкретным именем, паролем и статусом
    public UserData generateUser(String username, String password, int userStatus) {
        return new UserData()
                .setId(faker.number().randomNumber())  // Преобразуем long в int
                .setUsername(username)
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setPassword(password)
                .setPhone(faker.phoneNumber().cellPhone())
                .setUserStatus(userStatus);
    }

    // Метод для генерации данных для логина пользователя
    public UserLogin generateUserLogin(String username, String password) {
        return new UserLogin()
                .setUsername(username)
                .setPassword(password);
    }

    // Метод для генерации данных для выхода пользователя
    public UserLogout generateUserLogout(String username) {
        return new UserLogout()
                .setUsername(username);  // Метод теперь возвращает сам объект UserLogout
    }

    // Метод для генерации данных для обновления пользователя
    public UserData generateUserForUpdate() {
        return new UserData()
                .setUsername(faker.name().username())
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setPhone(faker.phoneNumber().cellPhone());
    }
}
