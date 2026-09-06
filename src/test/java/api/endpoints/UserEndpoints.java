package api.endpoints;
import api.payloads.UserPayload;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	public static Response createUser(UserPayload payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload).when()
				.post(Routes.USER_post_createUser);
		return response;

	}
	
	public static Response getUser(String username) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("userName", username)
				.when()
				.get(Routes.USER_get_User);
		return response;

	}
	
	public static Response updateUser(UserPayload payload,String username) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("userName", username)
				.body(payload)
				.when()
				.post(Routes.USER_put_updateUser);
		return response;

	}
	
	public static Response deleteUser(String username) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("userName",username)
				.when()
				.delete(Routes.USER_delete_User);
		return response;

	}
	
	public static Response getLogin(String userName,String Passwrd) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.queryParam("userName",userName)
				.queryParam("password", Passwrd)
				.when()
				.get(Routes.USER_get_login);
		return response;
	}
	
	public static Response getLogout() {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.get(Routes.USER_get_logout);
		return response;
	}
	
	public static Response createUserWithList(UserPayload userpayload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(userpayload).when()
				.post(Routes.USER_get_login);
		return response;
	}
}
