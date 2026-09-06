package api.endpoints;

import api.payloads.UserPayload;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpointsUsingPropertiesFile {

	
	static ResourceBundle getUrl()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUser(UserPayload payload) {
		String route = getUrl().getObject("USER_post_createUser").toString();
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(route);
		return response;

	}

	public static Response getUser(String username) {
		String route = getUrl().getObject("USER_get_User").toString();

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("userName", username).when().get(route);
		return response;

	}

	public static Response updateUser(UserPayload payload, String username) {
		String route = getUrl().getObject("USER_put_updateUser").toString();
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("userName", username).body(payload).when().post(route);
		return response;

	}

	public static Response deleteUser(String username) {
		String route = getUrl().getObject("USER_delete_User").toString();
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("userName", username).when().delete(route);
		return response;

	}

	public static Response getLogin(String userName, String Passwrd) {
		String route = getUrl().getObject("USER_get_login").toString();
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.queryParam("userName", userName).queryParam("password", Passwrd).when().get(route);
		return response;
	}

	public static Response getLogout() {
		String route = getUrl().getObject("USER_get_logout").toString();
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).when()
				.get(route);
		return response;
	}

	public static Response createUserWithList(UserPayload userpayload) {
		String route = getUrl().getObject("USER_get_login").toString();
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(userpayload).when()
				.post(Routes.USER_get_login);
		return response;
	}
}
