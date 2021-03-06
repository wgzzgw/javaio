package jacksontest;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
//使用JsonGenerator写入JSON
public class JacksonTester5 {
public static void main(String args[]) throws IOException{
	 JacksonTester5 tester = new JacksonTester5();
	JsonFactory jsonFactory=new JsonFactory();
	//写入数据类 JsonGenerator
	JsonGenerator jsonGenerator=
	jsonFactory.createJsonGenerator(new File(
			   "student6.json"), JsonEncoding.UTF8);
	jsonGenerator.writeStartObject();//此代码规定在写前敲入
	// "name" : "Mahesh Kumar"
	jsonGenerator.writeStringField("name", "Mahesh Kumar");
	 // "age" : 21
    jsonGenerator.writeNumberField("age", 21);
    // "verified" : false
    jsonGenerator.writeBooleanField("verified", false); 
    // "marks" : [100, 90, 85]
    jsonGenerator.writeFieldName("marks"); 
    // [
    jsonGenerator.writeStartArray(); 
    // 100, 90, 85
    jsonGenerator.writeNumber(100); 
    jsonGenerator.writeNumber(90); 
    jsonGenerator.writeNumber(85); 
    // ]
    jsonGenerator.writeEndArray(); 
    // }
    jsonGenerator.writeEndObject(); 
	jsonGenerator.close();//结束 不要忘记
	  //result student.json
    //{ 
    //   "name":"Mahesh Kumar",
    //   "age":21,
    //   "verified":false,
    //   "marks":[100,90,85]
    //}
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> dataMap = mapper.readValue(
				new File("student6.json"), Map.class);

		System.out.println(dataMap.get("name"));
		System.out.println(dataMap.get("age"));
		System.out.println(dataMap.get("verified"));
		System.out.println(dataMap.get("marks"));
}
}
