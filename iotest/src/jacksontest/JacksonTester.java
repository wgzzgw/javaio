package jacksontest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;

//将Java对象序列化到一个JSON文件，然后再读取JSON文件获取转换为对象
public class JacksonTester {
	public static void main(String args[]) throws JsonProcessingException, IOException {
		/*
		 * //第一步：创建ObjectMapper对象。它是一个可重复使用的对象。 ObjectMapper mapper = new
		 * ObjectMapper(); String jsonString =
		 * "{\"name\":\"Mahesh\", \"age\":21}";
		 * 
		 * // map json to student try { // 将JSON格式的数据转为java对象并打印
		 * //第二步：反序列化JSON到对象
		 * //从JSON对象使用readValue()方法来获取。通过JSON字符串和对象类型作为参数JSON字符串/来源。 Student
		 * student = mapper.readValue(jsonString, Student.class);
		 * System.out.println(student);
		 * 
		 * //将对象转为JSON格式的数据并打印 //第三步：序列化对象到JSON
		 * //使用writeValueAsString()方法来获取对象的JSON字符串表示。
		 * mapper.enable(SerializationConfig
		 * .Feature.INDENT_OUTPUT);//此句格式化JSON打印 便于查看 jsonString =
		 * mapper.writeValueAsString(student); System.out.println(jsonString);
		 * 
		 * } catch (JsonParseException e) { e.printStackTrace(); } catch
		 * (JsonMappingException e) { e.printStackTrace(); } catch (IOException
		 * e) { e.printStackTrace(); } }
		 */
		/*JacksonTester tester = new JacksonTester();
		try {
			Student student = new Student();
			student.setAge(10);
			student.setName("Mahesh");
			tester.writeJSON(student);

			Student student1 = tester.readJSON();
			System.out.println(student1);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	/*	 JacksonTester tester = new JacksonTester();
         try {
        	 //创建核心类 ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            //键值对 值Object
            Map<String,Object> studentDataMap = new HashMap<String,Object>(); 
            int[] marks = {1,2,3};

            Student student = new Student();
            student.setAge(10);
            student.setName("Mahesh");
            // JAVA Object
            studentDataMap.put("student", student);
            // JAVA String
            studentDataMap.put("name", "Mahesh Kumar");   		
            // JAVA Boolean
            studentDataMap.put("verified", Boolean.FALSE);
            // Array
            studentDataMap.put("marks", marks);
            //把HashMap这个数据结构转成JSON格式数据并 保存在文件
            mapper.writeValue(new File("student1.json"), studentDataMap);
            mapper.writeValue(new File("boolean1.json"), marks);//测试单独 无错
            //result student.json
			//{ 
            //   "student":{"name":"Mahesh","age":10},
            //   "marks":[1,2,3],
            //   "verified":false,
            //   "name":"Mahesh Kumar"
            //}
            //读取JSON文件数据并转成Map对象
            studentDataMap = mapper.readValue(new File("student1.json"), Map.class);
            System.out.println(studentDataMap.get("student"));
            System.out.println(studentDataMap.get("name"));
            System.out.println(studentDataMap.get("verified"));
            System.out.println(studentDataMap.get("marks"));
      } catch (JsonParseException e) {
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
            e.printStackTrace();
      }
*/
		/*JacksonTester jacksonTester=new JacksonTester();
		try{
			ObjectMapper objectMapper=new ObjectMapper();
			 Map userDataMap = new HashMap();
			 UserData studentData = new UserData();
			 int[] marks = {1,2,3};

	            Student student = new Student();
	            student.setAge(10);
	            student.setName("Mahesh");
	            // JAVA Object
	            studentData.setStudent(student);
	            // JAVA String
	            studentData.setName("Mahesh Kumar");
	            // JAVA Boolean
	            studentData.setVerified(Boolean.FALSE);
	            // Array
	            studentData.setMarks(marks);
	           //map数据包裹多层
	            userDataMap.put("studentData1", studentData);
	            objectMapper.writeValue(new File("student2.json"), userDataMap);
	            //{
	            //   "studentData1":
	            //	 {
	            //		"student":
	            //		{
	            //			"name":"Mahesh",
	            //			"age":10
	            //      },
	            //      "name":"Mahesh Kumar",
	            //      "verified":false,
	            //      "marks":[1,2,3]
	            //   }
	            //}
	          //TypeReference -- 让Jackson Json在List/Map中识别自己的Object
		           TypeReference ref = new TypeReference<Map<String, UserData>>() { };
	            userDataMap = objectMapper.readValue(new File("student2.json"), ref);
	            System.out.println(((UserData) userDataMap.get("studentData1")).getStudent());
	            System.out.println(((UserData) userDataMap.get("studentData1")).getName());
	            System.out.println(( (UserData) userDataMap.get("studentData1")).getVerified());
	            System.out.println(Arrays.toString(( (UserData) userDataMap.get("studentData1")).getMarks()));
		}catch (JsonParseException e) {
	         e.printStackTrace();
	      } catch (JsonMappingException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	            e.printStackTrace();
	      }*/
		//Create an ObjectMapper instance
		ObjectMapper mapper = new ObjectMapper();	
		String jsonString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85],"
				+ "\"name\":\"Mahesh Kumar2\", \"age\":212,\"verified\":false,\"marks\": [100,90,852]}";
		System.out.println(mapper.writeValueAsString(jsonString).toString());
		//create tree from JSON
		JsonNode rootNode = mapper.readTree(jsonString);
		System.out.println(rootNode);
		JsonNode nameNode = rootNode.path("name");
		System.out.println("Name: "+ nameNode.getTextValue());
		JsonNode ageNode=rootNode.path("age");
		System.out.println("Age: "+ageNode.getIntValue());
		JsonNode verifiedNode=rootNode.path("verified");
		System.out.println("VerifiedNode: "+verifiedNode.getBooleanValue());
		JsonNode marksNode=rootNode.path("marks");
		//对于数组需要迭代输出
		Iterator iterator = marksNode.getElements();
		System.out.print("MarksNode: ");
		while (iterator.hasNext()) {
			System.out.print(iterator.next()+" ");
		}
		System.out.println();
	}

	private void writeJSON(Student student) throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		/*mapper.writeValueAsString返回值是一个JSON字符串*/
		mapper.writeValue(new File("student.json"), student);
	}

	private Student readJSON() throws JsonParseException, JsonMappingException,
			IOException {
		ObjectMapper mapper = new ObjectMapper();
		// readValue方法可接收 1.字符串（符合JSON格式的） 2.JSON文件
		Student student = mapper.readValue(new File("student.json"),
				Student.class);
		return student;
	}
}

class Student {
	private String name;
	private int age;

	public Student() {
	}

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

	// 重写对象打印语句
	public String toString() {
		// 非json格式的数据
		return "Student [ name: " + name + ", age: " + age + " ]";
	}
}

class UserData {
	private Student student;// 强引用
	private String name;
	private Boolean verified;
	private int[] marks;

	public UserData() {
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public int[] getMarks() {
		return marks;
	}

	public void setMarks(int[] marks) {
		this.marks = marks;
	}
}