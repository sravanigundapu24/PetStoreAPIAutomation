package Practice;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class getAndAssertStatusCode {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		given().when().get("/posts/1").then().statusCode(200).log().all();

	}

}
