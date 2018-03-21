package Engine;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class Xml {

	public void initiate(String mail, String prob, String desc, String d) {
		try {
			File inputFile = new File("config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("\n----- Search the XML document with xpath queries -----");
			// Query 1
			System.out.println("Query 1: ");
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();

			XPathExpression expr1 = xpath.compile("/XML/User/@*");
			NodeList n2 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < n2.getLength(); i++) {
				System.out.print(n2.item(i).getNodeName() + ":");
				System.out.println(n2.item(i).getFirstChild().getNodeValue() + " ");

			}
			
			// Query 2
			System.out.println("\nQuery 2: ");
			expr1 = xpath.compile("/XML/Paths/jMetalPath");
			String str = (String) expr1.evaluate(doc, XPathConstants.STRING);
			System.out.println("jMetalPath:" + str);

			System.out.println("\n----- Adding new element <Administrator> with attributes to the XML document -----");

			Element newElement1 = doc.createElement("Administrator");
			newElement1.setAttribute("Name", "André Neiva");
			newElement1.setAttribute("email-do-administrador", "agpna@iscte-iul.pt");

			// Adding new element User to the XML document (root node)
			System.out.println("----- Adding new element <User> to the XML document -----");

			Element newElement2 = doc.createElement("User");
			newElement2.setAttribute("Date", d);
			newElement2.setAttribute("Description", desc);
			newElement2.setAttribute("Problem", prob);
			newElement2.setAttribute("UserEmail", mail);

			// Add new nodes to XML document (root element)
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			Node n = doc.getDocumentElement();
			n.appendChild(newElement1);
			n.appendChild(newElement2);
			
			
			System.out.println("\nSave XML document.");
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StreamResult result = new StreamResult(new FileOutputStream("config.xml"));
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}