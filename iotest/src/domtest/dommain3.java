package domtest;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//ʹ��DOM��������ѯXML�ĵ�����1
public class dommain3 {
	public static void main(String argv[]) {

		try {
			File inputFile = new File("testxml2.txt");
			// 1.����DocumentBuilder
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// 2.���ļ��л�ȡ�ĵ�
			Document doc = dBuilder.parse(inputFile);
			// 3.���������������ǰ�ڵ����������ڵ㣬ͨ��ɾ���յ� Text �ڵ㣬�Ѿ��ϲ��������ڵ� Text
			// �ڵ����淶���ĵ����÷����ڽ��нڵ�Ĳ����ɾ�������󣬶��ڼ��ĵ����Ľṹ�����á�
			// ��Ϊ�пո�
			doc.getDocumentElement().normalize();
			System.out.print("Root element: ");
			// 4.��ȡ���ڵ�����
			System.out.println(doc.getDocumentElement().getNodeName());
			// 5.��ȡ���нڵ�����Ϊsupercars�Ľڵ�
			NodeList nList = doc.getElementsByTagName("supercars");
			System.out.println("----------------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :");
				System.out.print(nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					//һ���ڵ㲻һ����Ԫ�أ���һ��Ԫ��һ���ǽڵ� �˴���Ϊ�ĵ��Ѿ�����normalize�����淶�����ʿ�ǿת
					Element eElement = (Element) nNode;
					System.out.print("company : ");
					// ��ȡԪ�ص�����
					System.out.println(eElement.getAttribute("company"));
					// ��ȡԪ�ص��ӽڵ�
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