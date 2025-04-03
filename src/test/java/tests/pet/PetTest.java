package tests.pet;

import data.PetDataRandom;
import lombok.pet.Pet;
import lombok.pet.PetStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static specs.pet.PetRequest.petRequestSpec;
import static specs.pet.PetResponse.petsResponseSpec;

public class PetTest extends TestBase {

    @Test
    @Tag("Pet")
    @DisplayName("Добавляем нового питомца в магазин")
    void addingANewPetToTheStore() {
        Pet pet = new Pet();
        pet.setName(PetDataRandom.generatePetName());
        pet.setStatus(PetDataRandom.generatePetStatus());

        step("Создаем и отправляем данные о питомце для добавления в магазин", () -> {
            given(petRequestSpec)
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
    @DisplayName("Поиск питомцев по статусу")
    void petSearchByStatus() {
        PetStatus petStatus = new PetStatus();
        petStatus.setStatus("pending");

        step("Поиск питомцев по статусу", () -> {
            given(petRequestSpec)
                    .queryParam("status", petStatus.getStatus())
                    .when()
                    .get("/pet/findByStatus")
                    .then()
                    .spec(petsResponseSpec)
                    .body("status", hasItem(petStatus.getStatus()));

        });
    }

    @Test
    @Tag("Pet")
    @DisplayName("Обновляем информацию о питомце")
    void updatingInformationAboutThePet() {
        Pet pet = new Pet();
        pet.setName("Charlik");
        pet.setStatus("sold");

        step("Обновляем информацию о питомце и проверяем статус", () -> {
            given(petRequestSpec)
                    .body(pet)
                    .when()
                    .put("/pet")
                    .then()
                    .spec(petsResponseSpec)
                    .body("name", equalTo("Charlik"))
                    .body("status", equalTo("sold"));
        });
    }
}
