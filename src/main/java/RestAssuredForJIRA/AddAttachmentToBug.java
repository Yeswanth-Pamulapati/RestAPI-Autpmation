package RestAssuredForJIRA;

import Files.Payload;
import Files.Utils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AddAttachmentToBug {
    @Test
    public void addAttachmentToBug() {
        RestAssured.baseURI = Utils.baseURL();
        String createBugResponse = given().header("X-Atlassian-Token", "no-check").header("Authorization", "Basic cGFtdWxhcGF0aXlAZ21haWwuY29tOkFUQVRUM3hGZkdGMF9BNXE3SDBSM0ZzbThTZldZa2piQWU1WDMtcHJhM3NPSGI1eE9HMmVLUDJ6eEROS3UyMzgwR1k3bVduSmpUbjBGNS1Ob2JaVENHanR4cHhETGQwaG0zVTgwZmh2TG1QM09HbGlmdlVhTldaTGdKNnlZdmU0NjhNU2t0d0ZmRmFrM1lZQjZQcEdzUUVCbzdzYWFGLWhWaGFicGdFcHN3TmdCOWNDUjRrOG1QWT04OUE2NTBCRA==")
                .multiPart(new File("C:\\Users\\yashn\\OneDrive\\Pictures\\Screenshots\\UI Issue.png")).pathParam("id",10002)
                .when().post("/rest/api/3/issue/{id}/attachments")
                .then().log().all().statusCode(200).extract().asString();
        JsonPath js = new JsonPath(createBugResponse);
        Utils.BugID = js.getString("id");
        System.out.println(Utils.BugID);
    }
}
