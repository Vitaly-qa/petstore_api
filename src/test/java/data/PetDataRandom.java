package data;

import com.github.javafaker.Faker;

public class PetDataRandom {
    private static final Faker faker = new Faker();

    // Генерация случайного имени питомца
    public static String generatePetName() {
        return faker.name().firstName();  // Генерация случайного имени питомца
    }

    // Генерация случайного статуса питомца
    public static String generatePetStatus() {
        String[] statuses = {"available", "pending", "sold"};
        return statuses[faker.random().nextInt(statuses.length)];  // Случайный статус из списка
    }
}
