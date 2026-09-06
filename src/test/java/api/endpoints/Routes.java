package api.endpoints;

public class Routes {

	public static String baseUrl = "https://petstore.swagger.io/v2";

	// user module
	public static String USER_post_createUser = baseUrl + "/user";
	
	public static String USER_get_User = baseUrl + "/user/{userName}";
	
	public static String USER_delete_User = baseUrl + "/user/{userName}";
	
	public static String USER_put_updateUser = baseUrl + "/user/{userName}";
	
	public static String USER_post_createUserwithList = baseUrl + "/user/createWithList";
	
	public static String USER_get_login = baseUrl + "/user/login";
	
	public static String USER_get_logout = baseUrl + "/user/logout";

	// store module
	public static String post_placeOrder = baseUrl + "/store/order";
	
	public static String get_petInventory = baseUrl + "/store/inventory";
	
	public static String get_purchaseOrder = baseUrl + "/store/order/{orderId}";
	
	public static String delete_Order = baseUrl + "/store/order/{orderId}";

	// Pet Module

	public static String PET_post_addNewPet = baseUrl + "/pet";

	public static String PET_put_updatePet = baseUrl + "/pet";

	public static String PET_get_petByStatus = baseUrl + "/pet/findByStatus";

	public static String PET_get_petById = baseUrl + "/pet/{petId}";

	public static String PET_post_updatePetWithFormData = baseUrl + "/pet/{petId}";

	public static String PET_post_uploadPetImage = baseUrl + "/pet/{petId}/uploadImage";

	public static String PET_delete_pet = baseUrl + "/pet/{petId}";
}
