package Files;

import io.restassured.path.json.JsonPath;

public class Utils {
	public static JsonPath rawTOJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}

}
