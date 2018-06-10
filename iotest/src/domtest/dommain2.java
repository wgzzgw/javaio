package domtest;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//ʹ��DOM�����������ĵ�����2
public class dommain2 {
	public static void main(String args[]) {
		try {
			File inputFile = new File("testxml.xml");
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
			// 4.���Լ��
			System.out.println("Root element(���ڵ�����):"
					+ doc.getDocumentElement().getNodeName());
			// 5.��ȡ�ӽڵ� 3��student�ӽڵ�
			NodeList nList = doc.getElementsByTagName("student");
			System.out.println("----------------------------");
			// 6.�����ӽڵ� �����
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element(��ǰ�ӽڵ�" + temp + "����):"
						+ nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					// ���ڵ�ǿת��Ԫ�� �����ȡ����
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
