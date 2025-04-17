package data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.github.javafaker.Faker;
import models.user.UserData;
import models.user.UserLogin;

@Getter
@Setter
@Accessors(chain = true)
public class UserFactory {

    private final Faker faker = new Faker();

    public UserData generateUser() {
        return generateUser(faker.name().username(), faker.internet().password(), 1);
    }

    public UserData generateUser(String username, String password, int userStatus) {
        return new UserData()
                .setId(faker.number().randomNumber())
                .setUsername(username)
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setPassword(password)
                .setPhone(faker.phoneNumber().cellPhone())
                .setUserStatus(userStatus);
    }


    public UserLogin generateUserLogin(String username, String password) {
        return UserLogin.builder()
                .username(username)
                .password(password)
                .build();
    }

}
