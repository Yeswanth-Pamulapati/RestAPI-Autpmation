package RestAssuredForJIRA;

import Files.Payload;
import groovy.json.JsonParser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.Scanner;

import static io.restassured.RestAssured.*;

public class CreateAnIssue {
    @Test
    public void createABug(){
        baseURI="https://pamulapatiy.atlassian.net";

        String summary="Going back forth between H&W and Loan details page causing UI issues-Automation.";
        String description = "Going back forth between H&W and Loan details page causing UI issues causing breaking of the page.";
        //Create a new bug
        String createBugResponse = given().header("Content-Type","application/json").header("Authorization","Basic cGFtdWxhcGF0aXlAZ21haWwuY29tOkFUQVRUM3hGZkdGMF9BNXE3SDBSM0ZzbThTZldZa2piQWU1WDMtcHJhM3NPSGI1eE9HMmVLUDJ6eEROS3UyMzgwR1k3bVduSmpUbjBGNS1Ob2JaVENHanR4cHhETGQwaG0zVTgwZmh2TG1QM09HbGlmdlVhTldaTGdKNnlZdmU0NjhNU2t0d0ZmRmFrM1lZQjZQcEdzUUVCbzdzYWFGLWhWaGFicGdFcHN3TmdCOWNDUjRrOG1QWT04OUE2NTBCRA==")
                .body(Payload.addAButTOJira(summary,description))
                .when().post("/rest/api/3/issue")
                .then().log().all().statusCode(201).extract().asString();
        JsonPath js = new JsonPath(createBugResponse);
        String issueID = js.getString("id");
        System.out.println(issueID);
    }
}
