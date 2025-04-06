package data;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.ClassOrderer;

import java.util.List;
import java.util.Random;
import java.util.Optional;


public class PetDataRandom {
    private static final Faker faker = new Faker();

    // Генерация случайного имени питомца
    public static String generatePetName() {
        return faker.name().firstName();  // Генерация случайного имени питомца
    }

    // Генерация случайного статуса питомца
    public static String generatePetStatus() {
        List<String> statuses = List.of("available", "pending", "sold");
        return Optional.ofNullable(statuses.get(new Random().nextInt(statuses.size())))
                .orElse("available");  // по умолчанию если что-то пошло не так
    }

}
