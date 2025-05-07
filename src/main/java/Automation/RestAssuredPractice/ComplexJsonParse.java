package Automation.RestAssuredPractice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import Files.Utils;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	@Test
	public void parseComplexJSON() throws IOException {
		JsonPath jspath = Utils.rawTOJson(Payload.getComplexJson());
		int count =  jspath.getInt("courses.size()");
		//only apply .size() method to an array of JSON.
		System.out.println(count);
		int purchaseAmount = jspath.getInt("dashboard.purchaseAmount");
		//get the total purchase amount
		System.out.println(purchaseAmount);
		String firstCourse = jspath.getString("courses[0].title");
		System.out.println(firstCourse);
		//Print all course titles and their prices/
		HashMap<String, Integer> courseDetails = new HashMap<String, Integer>();
		for(int i=0;i<count;i++) {
			String title = jspath.getString("courses["+i+"].title");
			int price = jspath.getInt("courses["+i+"].price");
			courseDetails.put(title, price);
		}
		System.out.println(courseDetails);
		//Get no. of copies sold for RPA
		int RPACourseCount =0;
		for(int i=0;i<count;i++) {
			if(jspath.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
				RPACourseCount = jspath.getInt("courses["+i+"].copies");
				break;
			}
			
		}
		System.out.println(RPACourseCount);
		    
		// Sum the individual prices and compare with total purchase amount.\
		int calculatedSum = 0;
		for(int i=0;i<count;i++) {
			 {
				 calculatedSum+= jspath.getInt("courses["+i+"].copies")*jspath.getInt("courses["+i+"].price");
			}
			
		}
		System.out.println(calculatedSum);
	   Assert.assertEquals(purchaseAmount, calculatedSum);
	}
}
