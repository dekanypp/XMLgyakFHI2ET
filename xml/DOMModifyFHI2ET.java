package DOMreadFHI2ET;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyFHI2ET {
    public static void main(String[] args) {
        String filePath = "src/DOMreadFHI2ET/FHI2ET.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Módosítja a boraszat székhelyének címét
            updateElementValue(doc);

            // Törli a tulajdonos címét
            deleteElement(doc);

            // Hozzáad egy "tipus" elemet a borhoz
            addElement(doc);

            // Kiírja egy fileba a módosításokat
            writeXMLFile(doc);

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
    }

    private static void writeXMLFile(Document doc)
    throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("D:\\uj.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        System.out.println("XML módosítása sikeres volt");
    }

     //Elem hozzáadása a bor-hoz.
    private static void addElement(Document doc) {
    	NodeList boraszatok = doc.getElementsByTagName("bor");
        Element bor = null;
        
        // loop for each user
        for (int i = 0; i < boraszatok.getLength(); i++) {
            bor = (Element) boraszatok.item(i); 
            Element id = doc.createElement("szarmazasi-hely");
            id.appendChild(doc.createTextNode("pokol-bugyra"));
            bor.appendChild(id);
        }
    }

    //Egy elem törlése
    private static void deleteElement(Document doc) {
    	NodeList boraszatok = doc.getElementsByTagName("tulajdonos");
        Element tulajdonos = null;
        // loop for each user
        for (int i = 0; i < boraszatok.getLength(); i++) {
            tulajdonos = (Element) boraszatok.item(i);
            Node Szid = tulajdonos.getElementsByTagName("Szid").item(0);
            tulajdonos.removeChild(Szid);
        }
    }

    //elem frissítése az XML doksiban.
    private static void updateElementValue(Document doc) {
        NodeList boraszatok = doc.getElementsByTagName("boraszat");
        Element boraszat = null;
        // loop for each user
        for (int i = 0; i < boraszatok.getLength(); i++) {
            boraszat = (Element) boraszatok.item(i);
            Node alap = boraszat.getElementsByTagName("alap").item(0).getFirstChild();
            alap.setNodeValue("9999");
        }
    }
}