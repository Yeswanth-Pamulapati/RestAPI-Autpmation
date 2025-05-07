package Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

public class Payload {
	public static File file;
	public static String addPlace() throws IOException {
		file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\payload.json");
		String body = FileUtils.readFileToString(file,StandardCharsets.UTF_8);
		return body;
		
	}
	public static String getComplexJson() throws IOException {
		file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\ComplexJson.json");
		String body = FileUtils.readFileToString(file,StandardCharsets.UTF_8);
		//Another way
	     //String body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\ComplexJson.json")));
		return body;
		
	}
	public static String addResource(String isbn,String aisle) {
		String body = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}";
		return body;
	}
	
	public static String deleteResource(String ID) {
		String body = "\r\n"
				+ "{\r\n"
				+ "    \"ID\": \""+ID+"\"\r\n"
				+ "}";
		return body;
	}

	public static String addAButTOJira(String summary,String description){
		String body = "{\n" +
				"  \"fields\": {\n" +
				"    \"project\": {\n" +
				"      \"key\": \"SCRUM\"\n" +
				"    },\n" +
				"    \"summary\": \""+summary+"\",\n" +
				"    \"description\": {\n" +
				"      \"type\": \"doc\",\n" +
				"      \"version\": 1,\n" +
				"      \"content\": [\n" +
				"        {\n" +
				"          \"type\": \"paragraph\",\n" +
				"          \"content\": [\n" +
				"            {\n" +
				"              \"type\": \"text\",\n" +
				"              \"text\": \""+description+"\"\n" +
				"            }\n" +
				"          ]\n" +
				"        }\n" +
				"      ]\n" +
				"    },\n" +
				"    \"issuetype\": {\n" +
				"      \"name\": \"Bug\"\n" +
				"    }\n" +
				"  }\n" +
				"}\n";
		return body;
	}

}
