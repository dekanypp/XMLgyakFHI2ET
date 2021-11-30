package DOMreadFHI2ET;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.SourceDataLine;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryFHI2ET {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		File file = new File("FHI2ET.xml");
		// Parse-olás
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		// Root element kiirasa
		System.out.print("Gyökér elem: ");
		System.out.println(doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("boraszatok");

		// Minden pizzeria attribútum kiiratasa

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			System.out.println("\nElem nev : " + node.getNodeName());
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				NodeList nList2 = elem.getChildNodes();
				for (int j = 0; j < nList2.getLength(); j++) {
					Node node2 = nList2.item(j);
					if (node2.getNodeType() == Node.ELEMENT_NODE) {
						Element elem2 = (Element) node2;
						if (!node2.getNodeName().equals("cim")) {
							System.out.println(node2.getNodeName() + " : " + node2.getTextContent());
						} else {
							System.out.println("Cim:");

							NodeList nList3 = elem2.getChildNodes();
							for (int k = 0; k < nList3.getLength(); k++) {
								Node node3 = nList3.item(k);

								if (node3.getNodeType() == Node.ELEMENT_NODE) {

									System.out.println("	" + node3.getNodeName() + " : " + node3.getTextContent());
								}
							}
						}
					}
				}
			}
		}
		//Kiirja annak a pizzerianak a nevet, ami Pesten van
		System.out.println("\n Laboda nevu boraszat: \n");
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				NodeList nList2 = elem.getChildNodes();
				for (int j = 0; j < nList2.getLength(); j++) {
					Node node2 = nList2.item(j);
					if (node2.getNodeType() == Node.ELEMENT_NODE) {
						Element elem2 = (Element) node2;
						NodeList nList3 = elem2.getChildNodes();
						for (int k = 0; k < nList3.getLength(); k++) {
							Node node3 = nList3.item(k);
							if (node3.getNodeType() == Node.ELEMENT_NODE) {
								if (node3.getNodeName().equals("nev")) {
									if (node3.getTextContent().equals("Laboda")) {
										node2 = nList2.item(1);
										System.out.println(node2.getNodeName() + ": " + node2.getTextContent());
									}

								}

							}
						}

					}
				}
			}
		}
	}
}