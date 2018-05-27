package Engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml {

	public void write(Problem prob) {
		try {
			Calendar cal = Calendar.getInstance();
			String filename = prob.getTitle() +".xml";
			File inputFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();// dBuilder.parse(inputFile);
			Element root = doc.createElement("File");
			doc.appendChild(root);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();

			XPathExpression expr1 = xpath.compile("/XML/User/@*");
			NodeList n2 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);

			Element newElement1 = doc.createElement("User");
			System.out.println("element - " + newElement1);
			newElement1.setAttribute("Name", prob.getUser().getMail());
			root.appendChild(newElement1);

			Element newElement2 = doc.createElement("Problem");
			newElement2.setAttribute("Date", prob.getDate());
			newElement2.setAttribute("Description", prob.getDescription());
			newElement2.setAttribute("Problem", prob.getProblem());
			newElement2.setAttribute("UserEmail", prob.getEmail());
			root.appendChild(newElement2);
			Element newElement3 = doc.createElement(prob.getVarName());
			for (int i = 0; i < prob.getVariables().size(); i++) {
				if (i == 0)
					newElement3.setAttribute("Type", prob.getType());
				prob.getVariables().get(i).setValues();
				System.out.println("1 -" + prob.getVariables());
				System.out.println("2 -" + prob.getVariables().get(i).getName());
				System.out.println("3 -" + prob.getVariables().get(i).getMin());
				System.out.println("4 -" + prob.getVariables().get(i).getMax());
				if (prob.getType() == "Binary")
					newElement3.setAttribute(prob.getVariables().get(i).getName(), prob.getVariables().get(i).getMin());
				else
					newElement3.setAttribute(prob.getVariables().get(i).getName(),
							prob.getVariables().get(i).getMin() + " - " + prob.getVariables().get(i).getMax());
			}
			root.appendChild(newElement3);
			Element newElement4 = doc.createElement("Objectives");
			for (int i = 0; i < prob.getObjectives().size(); i++) {
				newElement4.setAttribute("Objective", prob.getObjectives().get(i));
			}
			root.appendChild(newElement4);
			Element newElement5 = doc.createElement("Algorithms");
			newElement5.setAttribute("Type", prob.getAlgType());
			newElement5.setAttribute("JarPAth", prob.getJarPath());
			for (int i = 0; i < prob.getAlgorithms().length; i++) {
				newElement5.setAttribute("Algorithm", prob.getAlgorithms()[i]);
			}
			
			root.appendChild(newElement5);
			// Node n = doc.getDocumentElement();
			// n.appendChild(newElement1);
			// n.appendChild(newElement2);

			System.out.println("\nSave XML document.");
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StreamResult result = new StreamResult(new FileOutputStream(filename));
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
 
	public Node read(String filename) {
		System.out.println("Entrei no read");
		File inputFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("File");
			Node n = nList.item(0);
			;
			System.out.println("XML -" + n.getAttributes().item(1));
			return n;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ficheiro nao existe");
			e.printStackTrace();
		}
		return null;
	}

}