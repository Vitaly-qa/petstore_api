package tests;

import data.PetDataRandom;
import lombok.pet.Pet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static specs.pet.PetRequest.petRequestSpec;
import static specs.pet.PetResponse.notFoundResponseSpec;
import static specs.pet.PetResponse.petsResponseSpec;

public class PetTests extends TestBase {

    @Test
    @Tag("Pet")
    @Tag("positive")
    @DisplayName("Добавляем нового питомца в магазин")
    void addingANewPetToTheStore() {
        PetDataRandom petFactory = new PetDataRandom();
        Pet pet = petFactory.getPet(); // случайный питомец

        step("Создаем и отправляем данные о питомце для добавления в магазин", () -> {
            given()
                    .spec(petRequestSpec)
                    .body(pet)
                    .when()
                    .post("/pet")
                    .then()
                    .spec(petsResponseSpec)
                    .body("name", equalTo(pet.getName()))
                    .body("status", equalTo(pet.getStatus()));
        });
    }

    @Test
    @Tag("Pet")
    @Tag("positive")
    @DisplayName("Поиск питомца по статусу")
    void petSearchByStatus() {
        String status = "pending"; // Прямо указываем статус, без использования модели
        step("Поиск питомцев по статусу", () -> {
            given(petRequestSpec)
                    .queryParam("status", status) // Передаем статус напрямую
                    .when()
                    .get("/pet/findByStatus")
                    .then()
                    .spec(petsResponseSpec)
                    .body("status", hasItem(status));

        });
    }

    @Test
    @Tag("Pet")
    @Tag("positive")
    @DisplayName("Создаём, обновляем и удаляем питомца")
    void createUpdateAndDeletePet() {
        int petId = 1154616142;
        Pet pet = new Pet();
        pet.setId(petId);
        pet.setName("Charlik");
        pet.setStatus("available");

        step("Создаём питомца", () -> {
            given(petRequestSpec)
                    .body(pet)
                    .when()
                    .post("/pet")
                    .then()
                    .spec(petsResponseSpec)
                    .body("id", equalTo((int) petId))
                    .body("name", equalTo("Charlik"))
                    .body("status", equalTo("available"));
        });

        step("Обновляем информацию о питомце и проверяем статус", () -> {
            pet.setStatus("sold");

            given(petRequestSpec)
                    .body(pet)
                    .when()
                    .put("/pet")
                    .then()
                    .spec(petsResponseSpec)
                    .body("name", equalTo("Charlik"))
                    .body("status", equalTo("sold"));
        });

        step("Удаляем питомца", () -> {
            given(petRequestSpec)
                    .pathParam("petId", petId)
                    .when()
                    .delete("/pet/{petId}")
                    .then()
                    .statusCode(200)
                    .body("message", equalTo(String.valueOf(petId)));
        });

        step("Проверяем, что питомец удалён", () -> {
            given(petRequestSpec)
                    .pathParam("petId", petId)
                    .when()
                    .get("/pet/{petId}")
                    .then()
                    .statusCode(404)
                    .body("message", equalTo("Pet not found"));
        });
    }

    @Test
    @Tag("Pet")
    @Tag("negative")
    @DisplayName("Добавляем питомца в магазин без имени")
    void addingAPetToTheStoreWithoutAName() {
        step("Отправляем без данных питомца ", () -> {
            given(petRequestSpec)
                    .body("")
                    .when()
                    .post("/pet")
                    .then()
                    .spec(notFoundResponseSpec)
                    .body("type", equalTo("unknown"))
                    .body("message", equalTo("no data"));
        });
    }
}