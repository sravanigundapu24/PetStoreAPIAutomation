package api.endpoints;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import api.payloads.PetPayload;
import io.restassured.response.Response;

public class PetEndpoints {

	public static Response addNewPet(PetPayload petPayload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(petPayload).when()
				.post(Routes.PET_post_addNewPet);
		return response;
	}

	// Update Pet
	public static Response updatePet(PetPayload petPayload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(petPayload).when()
				.put(Routes.PET_put_updatePet);
		return response;
	}
	// Find Pets By Status

	public static Response getPetByStatus(String status) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).queryParam("status", status)
				.when().get(Routes.PET_get_petByStatus);

		return response;
	}

	// Get Pet By ID
	public static Response getPetById(int petId) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("petId", petId)
				.when().get(Routes.PET_get_petById);

		return response;
	}

	// Update Pet With Form Data
	public static Response updatePetWithFormData(int petId, String name, String status) {
		Response response = given().pathParam("petId", petId).contentType(ContentType.URLENC).accept(ContentType.JSON)
				.formParam("name", name).formParam("status", status).when().post(Routes.PET_post_updatePetWithFormData);

		return response;
	}

	// Upload Pet Image
	public static Response uploadPetImage(int petId, String filePath, String additionalMetadata) {
		Response response = given().pathParam("petId", petId).accept(ContentType.JSON)
				.multiPart("file", new java.io.File(filePath)).multiPart("additionalMetadata", additionalMetadata)
				.when().post(Routes.PET_post_uploadPetImage);

		return response;
	}

	// Delete Pet
	public static Response deletePet(int petId) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("petId", petId)
				.when().delete(Routes.PET_delete_pet);

		return response;
	}
}
