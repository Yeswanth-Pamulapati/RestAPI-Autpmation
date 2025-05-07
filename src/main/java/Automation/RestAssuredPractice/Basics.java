package Automation.RestAssuredPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.Assert;

import com.google.common.io.Files;

import Files.Payload;
import Files.Utils;

public class Basics {
	public static void main(String args[]) throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//Post new address and store the response in a string.
		System.out.println("\nPost new address and store the response in a string\n");
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		.body(Payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("status", equalTo("OK"),"scope",equalTo("APP"))
		.header("Server",equalTo("Apache/2.4.52 (Ubuntu)")).extract().asString();
		System.out.println(response);
		JsonPath jsonPath = Utils.rawTOJson(response);
		String placeID = jsonPath.getString("place_id");
		System.out.println(placeID);
		System.out.println("\nUpdate the address\n");
		//Update the address
		String latestAddress = "7490 Dublin boulevard, Dublin, CA, USA, 10234";
		given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json").body("{\r\n"
				+ "  \"place_id\": \""+placeID+"\",\r\n"
				+ "  \"address\": \""+latestAddress+"\",\r\n"
				+ "  \"key\": \"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		System.out.println("\nGet the address\n");
		//Get the address
		String responseForGet = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeID)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		JsonPath getResponseJson = Utils.rawTOJson(responseForGet);
		String updatedAddress = getResponseJson.getString("address");
		Assert.assertEquals(updatedAddress,latestAddress);
		
	}

}
