package Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class GetAndAssertValues {

	@Test(enabled = false)
	public void assertValues() {
		Response response = given().pathParam("postid", "2").when()
				.get("https://jsonplaceholder.typicode.com/posts/{postid}");

		response.then().body("userId", equalTo(1)).body("id", equalTo(2)).log().all();

	}

	@Test(enabled = false)
	public void getwithqueryParams() {
		Response response = given().queryParam("postId", "2").when()
				.get("https://jsonplaceholder.typicode.com/comments");

		response.then().body("size()", greaterThan(0)).body("postId", everyItem(equalTo(2))).log().all();

	}

	@Test
	public void sendARequestHeader() {

		Response response = given().header("X-Source", "sdet-kit").when().get("https://httpbin.org/headers");
		response.then().statusCode(200).body("headers.X-Source", equalTo("sdet-kit")).log().all();
	}

	@Test
	public void postAResource() {

		String body = "{\"title\": \"sdet kit title\",\"body\":\"rest assured kit\",\"userId\":\"5\"}";

		Response response = given().contentType(ContentType.JSON).body(body).when()
				.post("https://jsonplaceholder.typicode.com/posts");
		response.then().statusCode(201).body("size()", greaterThan(0)).body("title", equalTo("sdet kit title"))
				.body("body", equalTo("rest assured kit")).body("userId", equalTo("5")).log().all();
	}

	@Test
	public void postAResourceUsingJavaMap() {

		Map<String, Object> body = new HashMap<>();
		body.put("title", "sdet kit title");
		body.put("body", "rest assured kit");
		body.put("userId", "7");

		Response response = given().contentType(ContentType.JSON).body(body).when()
				.post("https://jsonplaceholder.typicode.com/posts");
		response.then().statusCode(201).body("size()", greaterThan(0)).body("title", equalTo("sdet kit title"))
				.body("body", equalTo("rest assured kit")).body("userId", equalTo("7")).log().all();
	}

}
