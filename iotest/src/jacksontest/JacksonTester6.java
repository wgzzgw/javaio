package jacksontest;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;

//使用JsonParser 读取JSON
//使用JsonFactory.createJsonParser()方法创建JsonParser，
//并使用它的nextToken()方法来读取每个JSON字符串作为标记。检查每个令牌和相应的过程。
public class JacksonTester6 {
	public static void main(String args[]) {
		JacksonTester6 tester = new JacksonTester6();
		try {
			JsonFactory jasonFactory = new JsonFactory();

			JsonGenerator jsonGenerator = jasonFactory.createJsonGenerator(
					new File("student7.json"), JsonEncoding.UTF8);
			jsonGenerator.writeStartObject();// 1
			jsonGenerator.writeStringField("name", "Mahesh Kumar"); // 2
			jsonGenerator.writeNumberField("age", 21);// 3
			jsonGenerator.writeBooleanField("verified", false); // 4
			jsonGenerator.writeFieldName("marks"); // 5
			jsonGenerator.writeStartArray(); // [//6
			jsonGenerator.writeNumber(100); // 7
			jsonGenerator.writeNumber(90); // 8
			jsonGenerator.writeNumber(85); // 9
			jsonGenerator.writeEndArray(); // ]//10
			jsonGenerator.writeEndObject(); // 11
			jsonGenerator.close(); // 12

			// result student.json
			// {
			// "name":"Mahesh Kumar",
			// "age":21,
			// "verified":false,
			// "marks":[100,90,85]
			// }

			JsonParser jsonParser = jasonFactory.createJsonParser(new File(
					"student7.json"));
			while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
				// get the current token
				String fieldname = jsonParser.getCurrentName();
				if ("name".equals(fieldname)) {
					// move to next token
					jsonParser.nextToken();
					System.out.println(jsonParser.getText());
				}
				if ("age".equals(fieldname)) {
					// move to next token
					jsonParser.nextToken();
					System.out.println(jsonParser.getNumberValue());
				}
				if ("verified".equals(fieldname)) {
					// move to next token
					jsonParser.nextToken();
					System.out.println(jsonParser.getBooleanValue());
				}
				if ("marks".equals(fieldname)) {
					// move to [
					jsonParser.nextToken();
					// loop till token equal to "]"
					while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
						System.out.println(jsonParser.getNumberValue());
					}
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
