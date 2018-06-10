package jacksontest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

//树到JSON的转换
//使用JsonNode并将其写入到一个JSON文件，并读回创建了一棵树
public class JacksonTester3 {
	public static void main(String args[]) {
		JacksonTester3 tester = new JacksonTester3();
		try {
			ObjectMapper mapper = new ObjectMapper();

			JsonNode rootNode = mapper.createObjectNode();
			JsonNode marksNode = mapper.createArrayNode();
			// 添加三个数据 即int数组
			((ArrayNode) marksNode).add(100);
			((ArrayNode) marksNode).add(90);
			((ArrayNode) marksNode).add(85);
			((ObjectNode) rootNode).put("name", "Mahesh Kumar");
			((ObjectNode) rootNode).put("age", 21);
			((ObjectNode) rootNode).put("verified", false);
			((ObjectNode) rootNode).put("marks", marksNode);

			mapper.writeValue(new File("student3.json"), rootNode);

			rootNode = mapper.readTree(new File("student3.json"));

			JsonNode nameNode = rootNode.path("name");
			System.out.println("Name: " + nameNode.getTextValue());

			JsonNode ageNode = rootNode.path("age");
			System.out.println("Age: " + ageNode.getIntValue());

			JsonNode verifiedNode = rootNode.path("verified");
			System.out.println("Verified: "
					+ (verifiedNode.getBooleanValue() ? "Yes" : "No"));

			JsonNode marksNode1 = rootNode.path("marks");
			Iterator<JsonNode> iterator = marksNode1.getElements();
			System.out.print("Marks: [ ");
			while (iterator.hasNext()) {
				JsonNode marks = iterator.next();
				System.out.print(marks.getIntValue() + " ");
			}
			System.out.println("]");
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
