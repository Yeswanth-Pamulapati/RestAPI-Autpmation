package Automation.RestAssuredPractice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryAPI {
	
	String postID;
	@Test(dataProvider = "getData")
	public void addABook(String isdm, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		
		//Post a new Book
		String postResponse = given().log().all().header("Content-Type", "application/json").body(Payload.addResource(isdm,aisle)).
		when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).extract().asString();
		JsonPath jp = new JsonPath(postResponse);
		postID = jp.getString("ID");
		System.out.println("ID: "+postID);
		System.out.println("Delete Resource........");
		given().log().all().body(Payload.deleteResource(postID)).header("Content-Type", "application/json").when().post("Library/DeleteBook.php").then().log().all().assertThat().statusCode(200);
		
	}
	
	
	@DataProvider
	public Object[][] getData(){
		return new Object[][] {{"ABahamutCastle","3272"},{"ArizonasArmfight","33"},{"OswaldsFued","111"}};
	}
	
}
