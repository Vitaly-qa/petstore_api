package steps;

import io.qameta.allure.Step;
import models.pet.Pet;
import specs.ResponseSpecs;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static specs.BaseRequestSpecs.baseRequestSpec;


public class PetApiSteps {

    @Step("Создаём питомца")
    public void createPet(Pet pet) {
        given()
                .spec(baseRequestSpec)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(ResponseSpecs.getFullLogResponseSpec(200))
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));
    }

    @Step("Обновляем информацию о питомце")
    public void updatePet(Pet pet) {
        given()
                .spec(baseRequestSpec)
                .body(pet)
                .when()
                .put("/pet")
                .then()
                .spec(ResponseSpecs.getFullLogResponseSpec(200))
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));
    }

    @Step("Удаляем питомца по ID {petId}")
    public void deletePet(Long petId) {
        given()
                .spec(baseRequestSpec)
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
                .spec(baseRequestSpec)
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(404)
                .body("message", equalTo("Pet not found"));
    }


    @Step("Поиск питомца по статусу")
    public void findPetByStatus(String status) {
        given()
                .spec(baseRequestSpec)
                .queryParam("status", status)
                .when()
                .get("/pet/findByStatus")
                .then()
                .spec(ResponseSpecs.getFullLogResponseSpec(200))
                .body("status", hasItem(status));
    }

    @Step("Добавление питомца без данных")
    public void createPetWithEmptyBody() {
        given()
                .spec(baseRequestSpec)
                .body("")
                .when()
                .post("/pet")
                .then()
                .spec(ResponseSpecs.getBaseResponseSpec(405))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("no data"));
    }

    public void createPetWithIdCheck(Pet pet, long petId) {

    }

}