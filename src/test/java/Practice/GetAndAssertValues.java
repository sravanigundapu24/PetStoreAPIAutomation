package Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;

import org.hamcrest.collection.HasItemInArray;
import org.testng.Assert;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
	
	@Test
	public void putToFullyUpdateaResource() {

		Map<String, Object> body = new HashMap<>();
		body.put("title", "update sdet kit title");
		body.put("body", "updated rest assured kit");
		body.put("userId", "8");
		body.put("id", 1);

		Response response = given().contentType(ContentType.JSON).body(body).when()
				.put("https://jsonplaceholder.typicode.com/posts/1");
		response.then().statusCode(200).body("size()", greaterThan(0)).body("title", equalTo("update sdet kit title"))
				.body("body", equalTo("updated rest assured kit")).body("userId", equalTo("8")).log().all();
	}
	
	@Test
	public void patchTopartiallyUpdateaResource() {

		Map<String, Object> body = new HashMap<>();
		body.put("title", "update title only sdet kit title");

		Response response = given().contentType(ContentType.JSON).body(body).when()
				.patch("https://jsonplaceholder.typicode.com/posts/1");
		response.then().statusCode(200).body("size()", greaterThan(0)).body("title", equalTo("update title only sdet kit title"))
				.body("userId", equalTo(1)).log().all();
	}
	
	@Test
	public void deleteAndAssertStatus() {


		Response response = given().when()
				.delete("https://jsonplaceholder.typicode.com/posts/1");
		response.then().statusCode(200).log().all();
	}
	
	@Test
	public void extractValuefromResponse() {

		Map<String, Object> body = new HashMap<>();
		body.put("title", "title check");
		body.put("body", "rest assured kit");
		body.put("userId", "9");

		Response response = given().contentType(ContentType.JSON).body(body).when()
				.post("https://jsonplaceholder.typicode.com/posts");
		String title = response.then()
		.statusCode(201)
		.body("size()", greaterThan(0))
		.log().all()
		.extract().path("title");
		
		Assert.assertEquals(title, "title check");
	}
	
	@Test
	public void authenticateandExtractTokenPostAndPatchAndDelete()
	{
		Map<String,Object> cred = new HashMap<>();
		cred.put("username", "admin");
		cred.put("password","password123");
		
		
		String token = given().contentType(ContentType.JSON).body(cred).when().post("https://restful-booker.herokuapp.com/auth")
				.then().body("size()", greaterThan(0)).log().all().extract().path("token");
		System.out.print(token);
		

		Map<String,Object> bookingdates = new HashMap<>();
		
		bookingdates.put("checkin", "2026-01-01");
		bookingdates.put("checkout", "2026-01-05");
		
		Map<String,Object> newBooking = new HashMap<>();
		
		newBooking.put("firstname", "Temp");
		newBooking.put("lastname", "User");
		newBooking.put("totalprice", 100);
		newBooking.put("depositpaid", true);
		newBooking.put("bookingdates", bookingdates);
		
		int bookingid = given().contentType(ContentType.JSON).cookie("token", token).header("Accept", "application/json")
				.body(newBooking).when().post("https://restful-booker.herokuapp.com/booking").then().log().all().extract().path("bookingid");

		Map<String, Object> changes = new HashMap<>();
		changes.put("firstname", "Priya");
		changes.put("totalprice", 555);
		
		//patch data
		given().contentType(ContentType.JSON).body(changes).cookie("token", token).pathParam("id", bookingid)
		.when().patch("https://restful-booker.herokuapp.com/booking/{id}")
			.then().statusCode(200).log().all();
		
		//delete data
		given().cookie("token", token).pathParam("id", bookingid)
		.when().delete("https://restful-booker.herokuapp.com/booking/{id}")
			.then().statusCode(201).log().all();
		
	}
	
	@Test
	public void assertOnJsonArray()
	{
		given()
		.when()
		.get("https://jsonplaceholder.typicode.com/users")
		.then().statusCode(200).body("$",hasSize(10)).body("name",hasItem("Leanne Graham")).log().all();
	}
	
	@Test
	public void paginationWithQueryParam()
	{
		given()
		.queryParam("limit", 5)
		.queryParam("skip", 0)
		.queryParam("select", "title,price")
		.when().get("https://dummyjson.com/products")
		.then().statusCode(200).body("size()", greaterThan(0)).body("limit",equalTo(5)).body("skip",equalTo(0)).log().all().body("products.title", hasItem("Red Lipstick"));
	}
	
	@Test
	public void searchEndpoint()
	{
		given().queryParam("q", "phone")
		.when().get("https://dummyjson.com/products/search")
		.then().statusCode(200).body("size()", greaterThan(0)).log().all();
	}
	
	@Test
	public void verifyStatusCodeBodyTime()
	{
		given().queryParam("city", "pune")
		.when().get("https://httpbin.org/get")
		.then().statusCode(200).body("size()",greaterThan(0)).body("args.city",equalTo("pune")).time(lessThan(5000L),TimeUnit.MILLISECONDS);
	}
	
	@Test
	public void assertBodyFieldWithHamcrestMatchers()
	{
		given().pathParam("id",1)
		.when().get("https://dummyjson.com/products/{id}")
		.then().statusCode(200).log().all().body("title",equalTo("Essence Mascara Lash Princess")).body("brand",equalTo("Essence"));
	}
	
	@Test
	public void assertMemebershipInJsonArray()
	{
		given()
		.when().get("https://dummyjson.com/products/categories")
		.then().statusCode(200).body("slug",hasItems("beauty","fragrances"))
		.body("slug.size()",greaterThan(5));
	}
	
	@Test
	public void extractSingleValuefromJsonArray()
	{
		Float price = given()
		.when().get("https://dummyjson.com/products/1")
		.then().statusCode(200).extract().path("price");
		
		Assert.assertTrue(price>0);
		
	}
	
	@Test
	public void extractListofValues()
	{
			JsonPath products = given()
			.queryParam("limit",5)
			.queryParam("select","title")
			.when().get("https://dummyjson.com/products")
			.then().statusCode(200).extract().jsonPath();
			
			List<String> titles = products.getList("products.title",String.class);
			for(String s : titles)
			{
				System.out.println(s);
			}
	}
	
	@Test
	public void filterAJsonArraywithGpathFindALL()
	{
		JsonPath products = given()
				.queryParam("select","title,price")
				.when().get("https://dummyjson.com/products")
				.then().statusCode(200).extract().jsonPath();
		
		List<String> titles = products.getList("products.findAll{it.price <15}.title" ,String.class);
		for(String s : titles)
		{
			System.out.println(s);
		}
		Assert.assertNotNull(titles);
	}
	
	@Test
	public void aggregateJsonArray()
	{
		JsonPath products = given()
				.queryParam("select","title,price")
				.when().get("https://dummyjson.com/products")
				.then().statusCode(200).extract().jsonPath();
		
		Float maxprice = products.getFloat("products.price.max()");
		Float sumprice = products.getFloat("products.price.sum()");
				
			System.out.println(maxprice);
			System.out.println(sumprice);
		
	}
	
	
	

}
