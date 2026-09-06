package api.tests;

import org.testng.annotations.Test;

import api.endpoints.StoreEndpoints;
import api.endpoints.UserEndpoints;
import api.payloads.StorePayload;
import api.payloads.UserPayload;
import api.utilities.XLDataProvider;
import io.restassured.response.Response;

public class DataDrivenTests {

	// User DataDriven Tests
	@Test(priority = 1, dataProvider = "UserData", dataProviderClass = XLDataProvider.class)
	public void postUserData(String id, String username, String firstName, String lastName, String email,
			String password, String phone, String userStatus) {

		UserPayload userPayload = new UserPayload();
		userPayload.setId(Integer.parseInt(id));
		userPayload.setUsername(username);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		userPayload.setUserStatus(Integer.parseInt(userStatus));

		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all().statusCode(200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = XLDataProvider.class)
	public void deleteUsers(String usernames) {
		Response response = UserEndpoints.deleteUser(usernames);
		response.then().log().all();
	}

	// Store
	@Test(priority = 3, dataProvider = "StoreData", dataProviderClass = XLDataProvider.class)
	public void getAllStoreData(String orderId, String petId, String quantity, String shipDate, String status,
			String complete) {

		StorePayload storepayload = new StorePayload();

		storepayload.setId(Integer.parseInt(orderId));
		storepayload.setPetId(Integer.parseInt(petId));
		storepayload.setQuantity(Integer.parseInt(quantity));
		storepayload.setShipDate(shipDate);
		storepayload.setStatus(status);
		storepayload.setComplete(Boolean.parseBoolean(complete));

		Response response = StoreEndpoints.placeOrder(storepayload);
		response.then().log().all().statusCode(200);
	}
}
