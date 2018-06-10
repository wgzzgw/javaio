package domtest;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//使用DOM解析器解析文档例子2
public class dommain2 {
	public static void main(String args[]) {
		try {
			File inputFile = new File("testxml.xml");
			// 1.创建DocumentBuilder
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// 2.从文件中获取文档
			Document doc = dBuilder.parse(inputFile);
			// 3.这个方法将遍历当前节点的所有子孙节点，通过删除空的 Text 节点，已经合并所有相邻的 Text
			// 节点来规范化文档。该方法在进行节点的插入或删除操作后，对于简化文档树的结构很有用。
			// 因为有空格
			doc.getDocumentElement().normalize();
			// 4.属性检查
			System.out.println("Root element(根节点名字):"
					+ doc.getDocumentElement().getNodeName());
			// 5.获取子节点 3个student子节点
			NodeList nList = doc.getElementsByTagName("student");
			System.out.println("----------------------------");
			// 6.遍历子节点 并输出
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element(当前子节点" + temp + "名字):"
						+ nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					// 将节点强转成元素 方便获取属性
					Element eElement = (Element) nNode;
					System.out.println("Student roll no : "
							+ eElement.getAttribute("rollno"));
					System.out.println("First Name : "
							+ eElement.getElementsByTagName("firstname")
									.item(0).getTextContent());
					System.out.println("Last Name : "
							+ eElement.getElementsByTagName("lastname").item(0)
									.getTextContent());
					System.out.println("Nick Name : "
							+ eElement.getElementsByTagName("nickname").item(0)
									.getTextContent());
					System.out.println("Marks : "
							+ eElement.getElementsByTagName("marks").item(0)
									.getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
