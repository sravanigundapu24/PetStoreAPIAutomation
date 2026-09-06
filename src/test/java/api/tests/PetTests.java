package api.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndpoints;
import api.payloads.PetCategory;
import api.payloads.PetPayload;
import api.payloads.PetTag;
import io.restassured.response.Response;

public class PetTests {

	
	Faker faker;
	PetPayload petPayload = new PetPayload();
	@BeforeClass
	public void setUpData()
	{
		faker = new Faker();

	    petPayload.setId(faker.idNumber().hashCode());

	    petPayload.setName(faker.name().firstName());

	    petPayload.setStatus("available");

	    // Category
	    PetCategory category = new PetCategory();
	    category.setId(faker.idNumber().hashCode());
	    category.setName(faker.name().firstName());

	    petPayload.setCategory(category);

	    // Photo URLs
	    List<String> photoUrls = new ArrayList<>();
	    photoUrls.add(faker.internet().url());

	    petPayload.setPhotoUrls(photoUrls);

	    // Tags
	    PetTag tag = new PetTag();
	    tag.setId(faker.idNumber().hashCode());
	    tag.setName(faker.name().firstName());

	    List<PetTag> tags = new ArrayList<>();
	    tags.add(tag);

	    petPayload.setTags(tags);
	
	}
	@Test
	(priority = 1)
	public void addNewPet()
	{
		Response response = PetEndpoints.addNewPet(petPayload);
		response.then().log().all().statusCode(200);
	}
	
	   // 2. Get Pet By ID
    @Test(priority = 2)
    public void getPetById() {

        Response response = PetEndpoints.getPetById(this.petPayload.getId());

        response.then()
                .log().all()
                .statusCode(200);
    }


    // 3. Update Pet
    @Test(priority = 3)
    public void updatePet() {

        petPayload.setName(faker.name().firstName());
        petPayload.setStatus("sold");

        Response response = PetEndpoints.updatePet(this.petPayload);

        response.then()
                .log().all()
                .statusCode(200);
    }


    // 4. Find Pets By Status
    @Test(priority = 4)
    public void getPetByStatus() {

        Response response = PetEndpoints.getPetByStatus("available");

        response.then()
                .log().all()
                .statusCode(200);
    }


    // 5. Update Pet With Form Data
    @Test(priority = 5)
    public void updatePetWithFormData() {

        String name = faker.name().firstName();

        Response response = PetEndpoints.updatePetWithFormData(
                this.petPayload.getId(),
                name,
                "available"
        );

        response.then()
                .log().all()
                .statusCode(200);
    }	


//    // 6. Upload Pet Image
//    @Test(priority = 6)
//    public void uploadPetImage() {
//
//        String filePath = "C:\\Users\\YourName\\Pictures\\pet.jpg";
//
//        Response response = PetEndpoints.uploadPetImage(
//                petPayload.getId(),
//                filePath,
//                "Pet image"
//        );
//
//        response.then()
//                .log().all()
//                .statusCode(200);
//    }


    // 7. Delete Pet
    @Test(priority = 7)
    public void deletePet() {

        Response response = PetEndpoints.deletePet(this.petPayload.getId());

        response.then()
                .log().all()
                .statusCode(200);
    }
}
