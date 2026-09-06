
	package api.tests;

	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import com.github.javafaker.Faker;

	import api.endpoints.StoreEndpoints;
	import api.payloads.StorePayload;
	import io.restassured.response.Response;

	public class storeTests {

	    Faker faker;
	    StorePayload storePayload = new StorePayload();


	    @BeforeClass
	    public void setUpData()
	    {
	        faker = new Faker();

	        storePayload.setId(faker.number().numberBetween(1, 1000));

	        storePayload.setPetId(faker.number().numberBetween(1, 1000));

	        storePayload.setQuantity(faker.number().numberBetween(1, 10));

	        storePayload.setShipDate(
	                java.time.Instant.now().toString()
	        );

	        storePayload.setStatus("placed");

	        storePayload.setComplete(false);
	    }


	    // 1. Place Order
	    @Test(priority = 1)
	    public void placeOrder()
	    {
	        Response response = StoreEndpoints.placeOrder(storePayload);

	        response.then()
	                .log().all()
	                .statusCode(200);
	    }


	    // 2. Get Pet Inventory
	    @Test(priority = 2)
	    public void getPetInventory()
	    {
	        Response response = StoreEndpoints.getPetInventory();

	        response.then()
	                .log().all()
	                .statusCode(200);
	    }


	    // 3. Get Purchase Order
	    @Test(priority = 3)
	    public void getPurchaseOrder()
	    {
	        Response response = StoreEndpoints.getPurchaseOrder(
	                storePayload.getId()
	        );

	        response.then()
	                .log().all()
	                .statusCode(200);
	    }


	    // 4. Delete Order
	    @Test(priority = 4)
	    public void deleteOrder()
	    {
	        Response response = StoreEndpoints.deleteOrder(
	                storePayload.getId()
	        );

	        response.then()
	                .log().all()
	                .statusCode(200);
	    }
	}
	
