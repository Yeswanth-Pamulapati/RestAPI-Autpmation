package APIWithOAuth;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import Files.Utils;

public class APIWithoAuth {
        public static void main(String args[]){

            
            String response = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
            .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
            .formParam("grant_type", "client_credentials")
            .formParam("scope", "trust")
            .when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
            .then().extract().asString();
            JsonPath  js = Utils.rawTOJson(response);
            String accessToken = js.getString("access_token");
            //System.out.println(access_token);
            System.out.println("\n*****************Get the Response*************\n");
            String getRespose = given().queryParam("access_token",accessToken)
            .when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
            System.out.println("Response Body: "+ getRespose);
                
           
        }
}
