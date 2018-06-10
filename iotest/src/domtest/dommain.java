package domtest;

//1.����XML��ص�������
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.lang.model.util.Elements;
import javax.xml.parsers.*;

import java.io.*;

//ʹ��DOM����������XML�ĵ�����1
public class dommain {
	public static void main(String args[]) throws ParserConfigurationException,
			SAXException, IOException {
		// 2.���� DocumentBuilder
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		// 3.���ļ���������һ���ĵ�
		StringBuilder xmlStringBuilder = new StringBuilder();
		xmlStringBuilder.append("<?xml version=\"1.0\"?> <class name=\"po\">"
				+ "<div name=\"hello\">hhhhhh������</div>"
				+ "<div name=\"hello2\">hhhhhhbufasheng</div>"
				+ "<op name=\"hello3\">hhhhhdsaeng</op>"+ "</class>");
		// �ֽ�����������
		ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder
				.toString().getBytes("UTF-8"));
		Document doc = documentBuilder.parse(input);// �õ��ĵ�
		// 4.��ȡ��Ԫ��
		Element root = doc.getDocumentElement();
		// 5.�������
		// returns specific attribute
		System.out.println(root.getAttribute("name"));
		//���������ӽڵ�����Լ����ı�����
		System.out.println(root.getTextContent());
		// 6.�����Ԫ��
		// returns a list of subelements of specified name
		NodeList child = root.getElementsByTagName("div");
		for (int i = 0; i < child.getLength(); i++) {
			System.out.println(child.item(i).getNodeName() + " "
					+ child.item(i).getNodeType() + " "
					+ child.item(i).getTextContent());
		}
		System.out.println("�������������������������ķָ��ߡ�������������������");
		// returns a list of all child nodes
		NodeList child2 = root.getChildNodes();
		System.out.println("root�ӽڵ����"+child2.getLength());
		for (int i = 0; i < child2.getLength(); i++) {
			System.out.println(child2.item(i).getNodeName() + " "
					+ child2.item(i).getNodeType() + " "
					+ child2.item(i).getTextContent());
		}
	}
}