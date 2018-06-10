package domtest;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//使用DOM解析器查询XML文档例子1
public class dommain3 {
	public static void main(String argv[]) {

		try {
			File inputFile = new File("testxml2.txt");
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
			System.out.print("Root element: ");
			// 4.获取根节点属性
			System.out.println(doc.getDocumentElement().getNodeName());
			// 5.获取所有节点名字为supercars的节点
			NodeList nList = doc.getElementsByTagName("supercars");
			System.out.println("----------------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :");
				System.out.print(nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					//一个节点不一定是元素，但一个元素一定是节点 此处因为文档已经调用normalize方法规范化，故可强转
					Element eElement = (Element) nNode;
					System.out.print("company : ");
					// 获取元素的属性
					System.out.println(eElement.getAttribute("company"));
					// 获取元素的子节点
					NodeList carNameList = eElement
							.getElementsByTagName("carname");
					for (int count = 0; count < carNameList.getLength(); count++) {
						Node node1 = carNameList.item(count);
						if (node1.getNodeType() == node1.ELEMENT_NODE) {
							Element car = (Element) node1;
							System.out.print("car name : ");
							System.out.println(car.getTextContent());
							System.out.print("car type : ");
							System.out.println(car.getAttribute("type"));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
