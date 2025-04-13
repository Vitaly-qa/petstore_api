package tests;

import data.PetFactory;
import models.pet.Pet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import steps.PetApiSteps;
import static io.qameta.allure.Allure.step;



public class PetTests extends TestBase {

    PetFactory petFactory = new PetFactory();
    PetApiSteps petSteps = new PetApiSteps();

    @Test
    @Tag("Pet")
    @Tag("positive")
    @DisplayName("Добавляем нового питомца в магазин")
    void addingANewPetToTheStore() {
        Pet pet = petFactory.generatePet(); // имя и статус генерируются
        step("Создаём питомца", () -> petSteps.createPet(pet));
    }

    @ParameterizedTest
    @ValueSource(strings = {"available", "pending", "sold"})
    @Tag("Pet")
    @Tag("positive")
    @DisplayName("Создаём питомца с разными статусами")
    void createPetWithDifferentStatuses(String status) {
        Pet pet = petFactory.generatePetByStatus(status); // имя генерируется, статус передаётся
        step("Создаём питомца со статусом: " + status, () -> petSteps.createPet(pet));
    }

    @Test
    @Tag("Pet")
    @Tag("positive")
    @DisplayName("Поиск питомца по статусу")
    void petSearchByStatus() {
        String status = "pending";
        step("Поиск питомца по статусу", () -> petSteps.findPetByStatus(status));
    }

    @Test
    @Tag("Pet")
    @Tag("positive")
    @DisplayName("Создаём, обновляем и удаляем питомца")
    void createUpdateAndDeletePet() {
        Pet pet = petFactory.generatePet("Charlik", "available");
        long petId = System.currentTimeMillis(); // уникальный ID
        pet.setId(petId);

        step("Создаём питомца", () -> petSteps.createPetWithIdCheck(pet, petId));

        step("Обновляем питомца", () -> {
            pet.setStatus("sold");
            petSteps.updatePet(pet);
        });

        step("Удаляем питомца", () -> petSteps.deletePet(pet.getId()));

        step("Проверяем, что питомец удалён", () -> petSteps.checkPetIsDeleted(pet.getId()));
    }

    @Test
    @Tag("Pet")
    @Tag("negative")
    @DisplayName("Добавляем питомца в магазин без имени")
    void addingAPetToTheStoreWithoutAName() {
        step("Отправляем пустое тело запроса", petSteps::createPetWithEmptyBody);
    }
}