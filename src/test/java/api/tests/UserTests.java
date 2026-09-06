package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.UserPayload;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	UserPayload userPayload = new UserPayload();
	public Logger logger;
	
	@BeforeClass
	public void setUpData()
	{
		faker = new Faker();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test
	(priority = 1)
	public void createUser()
	{
		logger.info("------------creating the user info");
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all()
		.and().statusCode(200);
		Assert.assertEquals(response.statusCode(), 200);
	}
	@Test
	(priority = 2)
	public void getUser()
	{
		logger.info("------------GEt User details---------");
		Response response = UserEndpoints.getUser(userPayload.getUsername());
		response.then().log().all()
		.and().statusCode(200);
		Assert.assertEquals(response.statusCode(), 200);
	}
	@Test
	(priority = 3)
	public void updateUser()
	{
		logger.info("---------------updating user----------");
		Response response = UserEndpoints.updateUser(userPayload,userPayload.getUsername());
		response.then().log().all()
		.and().statusCode(200);
		Assert.assertEquals(response.statusCode(), 200);
	}
	@Test
	(priority = 4)
	public void loginUser()
	{
		logger.info("----------------------login user-----------");
		Response response = UserEndpoints.getLogin(userPayload.getUsername(),userPayload.getPassword());
		response.then().log().all()
		.and().statusCode(200);
		Assert.assertEquals(response.statusCode(), 200);
	}
	@Test
	(priority = 5)
	public void logOutUser()
	{
		Response response = UserEndpoints.getLogout();
		response.then().log().all()
		.and().statusCode(200);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
}
