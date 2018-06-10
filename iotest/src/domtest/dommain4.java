package domtest;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//ʹ��DOM�������޸�XML�ĵ�����
public class dommain4 {
	private static boolean debug = true;

	public static void main(String argv[]) {

		try {
			File inputFile = new File("testxml3.txt");
			// 1.����DocumentBuilder
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// 2.���ļ��л�ȡ�ĵ�
			Document doc = docBuilder.parse(inputFile);
			// 3.��ȡ�ĵ���һ���ڵ� �����ڵ�
			Node cars = doc.getFirstChild();
			System.out.println(cars.getNodeName());
			if (debug) {
				System.out.println(doc.getDocumentElement().getTagName());
			}
			// 4.��ȡ�ӽڵ� ���õ���supercar����ڵ�
			Node supercar = doc.getElementsByTagName("supercars").item(0);
			// update supercar attribute
			NamedNodeMap attr = supercar.getAttributes();// ���ؽڵ��map���� �����޸�
			Node nodeAttr = attr.getNamedItem("company");// ��ȡ�ڵ���������Ϊcompany�Ľڵ�
			nodeAttr.setTextContent("Lamborigini");// ��company���Զ�Ӧ��ֵ�޸�ΪLamborigini

			// loop the supercar child node
			NodeList list = supercar.getChildNodes();
			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					if ("carname".equals(eElement.getNodeName())) {
						if ("Ferrari 101".equals(eElement.getTextContent())) {
							eElement.setTextContent("Lamborigini 001");
						}
						if ("Ferrari 202".equals(eElement.getTextContent()))
							eElement.setTextContent("Lamborigini 002");
					}
				}
			}
			NodeList childNodes = cars.getChildNodes();
			for (int count = 0; count < childNodes.getLength(); count++) {
				Node node = childNodes.item(count);
				if ("luxurycars".equals(node.getNodeName()))
					// �Ƴ���luxurycars����ڵ�
					cars.removeChild(node);
			}
			// write the content on console
			//1.
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			//2.
			Transformer transformer = transformerFactory.newTransformer();
			//3.
			DOMSource source = new DOMSource(doc);
			System.out.println("-----------Modified File-----------");
			//4.
			StreamResult consoleResult = new StreamResult(System.out);
			//5.
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
