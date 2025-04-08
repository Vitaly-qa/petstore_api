package data;

import com.github.javafaker.Faker;
import lombok.pet.Pet;

import java.util.Optional;
import java.util.Set;

public class PetDataRandom {
    private final Faker faker = new Faker();
    private final Set<String> validStatuses = Set.of("available", "pending", "sold");

    public Pet getPet() {
        return getPet(faker.funnyName().name(), faker.options().option(validStatuses.toArray(new String[0])));
    }

    public Pet getPet(String name, String status) {
        return new Pet()
                .setName(name)
                .setStatus(validateStatusOrThrow(status));
    }

    private String validateStatusOrThrow(String status) {
        return Optional.ofNullable(status)
                .filter(validStatuses::contains)
                .orElseThrow(() -> new IllegalArgumentException("Недопустимый статус: " + status));
    }
}
