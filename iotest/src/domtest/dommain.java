package domtest;

//1.导入XML相关的软件包
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.lang.model.util.Elements;
import javax.xml.parsers.*;

import java.io.*;

//使用DOM解析器解析XML文档例子1
public class dommain {
	public static void main(String args[]) throws ParserConfigurationException,
			SAXException, IOException {
		// 2.创建 DocumentBuilder
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		// 3.从文件或流创建一个文档
		StringBuilder xmlStringBuilder = new StringBuilder();
		xmlStringBuilder.append("<?xml version=\"1.0\"?> <class name=\"po\">"
				+ "<div name=\"hello\">hhhhhh发生的</div>"
				+ "<div name=\"hello2\">hhhhhhbufasheng</div>"
				+ "<op name=\"hello3\">hhhhhdsaeng</op>"+ "</class>");
		// 字节数组输入流
		ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder
				.toString().getBytes("UTF-8"));
		Document doc = documentBuilder.parse(input);// 得到文档
		// 4.提取根元素
		Element root = doc.getDocumentElement();
		// 5.检查属性
		// returns specific attribute
		System.out.println(root.getAttribute("name"));
		//返回所有子节点包括自己的文本内容
		System.out.println(root.getTextContent());
		// 6.检查子元素
		// returns a list of subelements of specified name
		NodeList child = root.getElementsByTagName("div");
		for (int i = 0; i < child.getLength(); i++) {
			System.out.println(child.item(i).getNodeName() + " "
					+ child.item(i).getNodeType() + " "
					+ child.item(i).getTextContent());
		}
		System.out.println("——————————华丽的分割线——————————");
		// returns a list of all child nodes
		NodeList child2 = root.getChildNodes();
		System.out.println("root子节点个数"+child2.getLength());
		for (int i = 0; i < child2.getLength(); i++) {
			System.out.println(child2.item(i).getNodeName() + " "
					+ child2.item(i).getNodeType() + " "
					+ child2.item(i).getTextContent());
		}
	}
}
