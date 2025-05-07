package Files;

import io.restassured.path.json.JsonPath;

public class Utils {
	public static String BugID;
	public Utils(){
		this.BugID = BugID;
	}
	public static JsonPath rawTOJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}

	public static String baseURL() {
		return "https://pamulapatiy.atlassian.net";
	}
}
