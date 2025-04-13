package data;

import com.github.javafaker.Faker;
import models.pet.Pet;
import java.util.Optional;
import java.util.Set;

public class PetFactory {
    private final Faker faker = new Faker();
    private final Set<String> validStatuses = Set.of("available", "pending", "sold");

    public Pet generatePet() {
        return generatePet(faker.funnyName().name(), getRandomStatus());
    }

    public Pet generatePetByStatus(String status) {
        return generatePet(faker.funnyName().name(), status);
    }

    public Pet generatePet(String name, String status) {
        return new Pet()
                .setName(name)
                .setStatus(validateStatusOrThrow(status));
    }

    private String validateStatusOrThrow(String status) {
        return Optional.ofNullable(status)
                .filter(validStatuses::contains)
                .orElseThrow(() -> new IllegalArgumentException("Недопустимый статус: " + status));
    }

    private String getRandomStatus() {
        return faker.options().option(validStatuses.toArray(new String[0]));
    }
}
