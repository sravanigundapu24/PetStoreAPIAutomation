package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payloads.StorePayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndpoints {
	// Place Order
	public static Response placeOrder(StorePayload storepayload) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(storepayload).when()
				.post(Routes.post_placeOrder);

		return response;
	}

	// Get Pet Inventory
	public static Response getPetInventory() {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
				.when().get(Routes.get_petInventory);

		return response;
	}

	// Get Purchase Order
	public static Response getPurchaseOrder(int orderId) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("orderId", orderId)
				.when().get(Routes.get_purchaseOrder);

		return response;
	}

	// Delete Order
	public static Response deleteOrder(int orderId) {
		Response response = given().pathParam("orderId", orderId)
				.when().delete(Routes.delete_Order);

		return response;
	}

}
