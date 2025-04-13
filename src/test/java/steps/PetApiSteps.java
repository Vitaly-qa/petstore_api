package steps;

import io.qameta.allure.Step;
import models.pet.Pet;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static specs.BaseRequestSpecs.petRequestSpec;
import static specs.ResponseSpecs.notFoundResponseSpec;
import static specs.ResponseSpecs.petsResponseSpec;

public class PetApiSteps {

    @Step("Создаём питомца")
    public void createPet(Pet pet) {
        given()
                .spec(petRequestSpec)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(petsResponseSpec)
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));
    }

    @Step("Создаём питомца и получаем его ID")
    public Long createPetAndReturnId(Pet pet) {
        return given()
                .spec(petRequestSpec)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(petsResponseSpec)
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()))
                .extract().path("id");
    }

    @Step("Обновляем информацию о питомце")
    public void updatePet(Pet pet) {
        given()
                .spec(petRequestSpec)
                .body(pet)
                .when()
                .put("/pet")
                .then()
                .spec(petsResponseSpec)
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));
    }

    @Step("Удаляем питомца по ID {petId}")
    public void deletePet(Long petId) {
        given()
                .spec(petRequestSpec)
                .pathParam("petId", petId)
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(200)
                .body("message", equalTo(String.valueOf(petId)));
    }

    @Step("Проверяем, что питомец удалён")
    public void checkPetIsDeleted(Long petId) {
        given()
                .spec(petRequestSpec)
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(404)
                .body("message", equalTo("Pet not found"));
    }

    @Step("Создаём питомца и проверяем ID")
    public void createPetWithIdCheck(Pet pet, int expectedId) {
        given()
                .spec(petRequestSpec)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(petsResponseSpec)
                .body("id", equalTo(expectedId))
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));
    }

    @Step("Поиск питомца по статусу")
    public void findPetByStatus(String status) {
        given()
                .spec(petRequestSpec)
                .queryParam("status", status)
                .when()
                .get("/pet/findByStatus")
                .then()
                .spec(petsResponseSpec)
                .body("status", hasItem(status));
    }

    @Step("Добавление питомца без данных")
    public void createPetWithEmptyBody() {
        given()
                .spec(petRequestSpec)
                .body("")
                .when()
                .post("/pet")
                .then()
                .spec(notFoundResponseSpec)
                .body("type", equalTo("unknown"))
                .body("message", equalTo("no data"));
    }

    // Метод в PetApiSteps
    public void createPetWithIdCheck(Pet pet, long petId) {
        // код, который использует petId типа long
    }
}
