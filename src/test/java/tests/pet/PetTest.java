package tests.pet;

import data.PetDataRandom;
import lombok.pet.PetModel;
import lombok.pet.PetStatusModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static specs.pet.PetRequest.petRequestSpec;
import static specs.pet.PetResponse.petsResponseSpec;

public class PetTest extends TestBase {

    @Test
    @DisplayName("Добавляем нового питомца в магазин")
    void addingANewPetToTheStore() {
        PetModel pet = new PetModel();
        pet.setName(PetDataRandom.generatePetName());
        pet.setStatus(PetDataRandom.generatePetStatus());

        given(petRequestSpec)
                .filter(withCustomTemplates())
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(petsResponseSpec)
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));
    }

    @Test
    @DisplayName("Поиск питомцев по статусу")
    void petSearchByStatus() {
        PetStatusModel petStatus = new PetStatusModel();
        petStatus.setStatus("pending");
        given(petRequestSpec)
                .queryParam("status", petStatus.getStatus())
                .when()
                .get("/pet/findByStatus")
                .then()
                .spec(petsResponseSpec)
                .body("status", hasItem(petStatus.getStatus()));

    }

    @Test
    @DisplayName("Обновляем информацию о питомце")
    void updatingInformationAboutThePet() {
        PetModel pet = new PetModel();
        pet.setName("Charlik");
        pet.setStatus("sold");
        given(petRequestSpec)
                .body(pet)
                .when()
                .put("/pet")
                .then()
                .spec(petsResponseSpec)
                .body("name", equalTo("Charlik"))
                .body("status", equalTo("sold"));
    }
}
