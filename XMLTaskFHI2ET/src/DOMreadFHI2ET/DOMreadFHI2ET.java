package DOMreadFHI2ET;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMreadFHI2ET {
	public static void main(String[] args) {
		try{
			File xmlDoc = new File("src/DOMreadFHI2ET/FHI2ET.xml");
			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuild = dbFact.newDocumentBuilder();
			Document doc = dBuild.parse(xmlDoc);

			//gyökér elem
			System.out.println("Gyökér elem: "+ doc.getDocumentElement().getNodeName());
			System.out.println("\n");
			
			//boraszat beolvasasa
			NodeList nList = doc.getElementsByTagName("boraszat");
			for(int i=0; i<nList.getLength();i++)
			{
				Node nNode = nList.item(i);
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode; 
					//borászat adatai
					System.out.println("boraszat Id: " + eElement.getAttribute("Btkod"));
					System.out.println("Boraszat neve: " + 
						eElement.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Boraszat alapitasa: " + 
						eElement.getElementsByTagName("alap").item(0).getTextContent());
					
					
					System.out.println("Boraszat cime: " + "\n"+
							eElement.getElementsByTagName("varos").item(0).getTextContent()+" "+eElement.getElementsByTagName("Irsz").item(0).getTextContent()
							+", "+eElement.getElementsByTagName("utca").item(0).getTextContent()+" "+
							eElement.getElementsByTagName("Hszam").item(0).getTextContent()+"." );
					
				}
			}
			System.out.println("________________________");
			
			NodeList nList11 = doc.getElementsByTagName("tulajdonos");
			for(int i=0; i<nList11.getLength();i++)
			{
				Node nNode = nList11.item(i);
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode; 
					//tulajdonos adatai
					System.out.println("tulajdonos Id: " + eElement.getAttribute("Tkod"));
					System.out.println("Tulajdonos neve: " + 
						eElement.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Tulajdonos születési ideje: " + 
						eElement.getElementsByTagName("Szid").item(0).getTextContent());
					System.out.println("Tulajdonos születési helye: " + 
							eElement.getElementsByTagName("Szh").item(0).getTextContent());
				}
			}
			
			System.out.println("________________________");
			
			NodeList nList1 = doc.getElementsByTagName("bor");
			for(int i=0; i<nList1.getLength();i++)
			{
				Node nNode = nList1.item(i);

				if(nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode; 
					//Bor adatai
					System.out.println("bor Id: " + eElement.getAttribute("Bkod"));
					System.out.println("Bor neve: " + 
						eElement.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Bor ara: " + 
						eElement.getElementsByTagName("ar").item(0).getTextContent());
					System.out.println("Bor fajta: " + 
							eElement.getElementsByTagName("fajta").item(0).getTextContent());
				}
			}
			System.out.println("________________________");
			
			NodeList nList111 = doc.getElementsByTagName("vevo");
			for(int i=0; i<nList111.getLength();i++)
			{
				Node nNode = nList111.item(i);

				if(nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode; 
					//vevõ adatai
					System.out.println("vevo Id: " + eElement.getAttribute("Vkod"));
					System.out.println("vevo neve: " + 
						eElement.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Vevo telefonszama: " + 
						eElement.getElementsByTagName("tszam").item(0).getTextContent());
					
					
					System.out.println("Vevo cime: " + "\n"+
							eElement.getElementsByTagName("varos").item(0).getTextContent()+" "+eElement.getElementsByTagName("irszam").item(0).getTextContent()
							+", "+eElement.getElementsByTagName("utca").item(0).getTextContent()+" "+
							eElement.getElementsByTagName("hszam").item(0).getTextContent()+"." );
					
				}
			}
			
			
			
		}		
		catch(Exception e){}

	}

}