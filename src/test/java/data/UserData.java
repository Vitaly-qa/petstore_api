package data;

import com.github.javafaker.Faker;

public class UserData {

    private static final Faker faker = new Faker();

    // Генерация случайного имени пользователя
    public static String generateUsername() {
        return faker.name().username();  // Генерация случайного имени пользователя
    }

    // Генерация случайной фамилии пользователя
    public static String generateLastName() {
        return faker.name().lastName();  // Генерация случайной фамилии
    }

    // Генерация случайного ID пользователя
    public static int generateUserId() {
        return faker.number().numberBetween(1, 1000);  // Генерация случайного ID от 1 до 1000
    }
}
