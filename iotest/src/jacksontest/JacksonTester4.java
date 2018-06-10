package jacksontest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

//从树到Java对象转换
//使用JsonNode并将其写入到一个JSON文件，并回读然后将一个Student对象其转换为创建了一棵树
class Student2 {
	   String name;
	   int age;
	   boolean verified;
	   int[] marks;
	   public String getName() {
	      return name;
	   }
	   public void setName(String name) {
	      this.name = name;
	   }
	   public int getAge() {
	      return age;
	   }
	   public void setAge(int age) {
	      this.age = age;
	   }
	   public boolean isVerified() {
	      return verified;
	   }
	   public void setVerified(boolean verified) {
	      this.verified = verified;
	   }
	   public int[] getMarks() {
	      return marks;
	   }
	   public void setMarks(int[] marks) {
	      this.marks = marks;
	   }
	}
public class JacksonTester4 {
	 public static void main(String args[]){
	  JacksonTester4 tester = new JacksonTester4();
      try {
         ObjectMapper mapper = new ObjectMapper();

         JsonNode rootNode = mapper.createObjectNode();
         JsonNode marksNode = mapper.createArrayNode();
         ((ArrayNode)marksNode).add(100);
         ((ArrayNode)marksNode).add(90);
         ((ArrayNode)marksNode).add(85);
         ((ObjectNode) rootNode).put("name", "Mahesh Kumar");
         ((ObjectNode) rootNode).put("age", 21);
         ((ObjectNode) rootNode).put("verified", false);
         ((ObjectNode) rootNode).put("marks",marksNode);

         mapper.writeValue(new File("student4.json"), rootNode);

         rootNode = mapper.readTree(new File("student4.json"));

         Student2 student = mapper.treeToValue(rootNode, Student2.class);//将树转为Java对象

         System.out.println("Name: "+ student.getName());
         System.out.println("Age: " + student.getAge());
         System.out.println("Verified: " + (student.isVerified() ? "Yes":"No"));
         System.out.println("Marks: "+Arrays.toString(student.getMarks()));
      } catch (JsonParseException e1) {
         e1.printStackTrace();
      } catch (JsonMappingException e2) {
         e2.printStackTrace();
      } catch (IOException e3) {
         e3.printStackTrace();
      }
}
}