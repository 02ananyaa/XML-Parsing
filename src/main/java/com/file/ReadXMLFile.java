package com.file;

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

public class ReadXMLFile {
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {

		// Create a DocumentBuilderFactory instance
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// Create a DocumentBuilder instance
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Path to the XML file
		String filePath = "src/main/resources/sample.xml";
		File xmlFile = new File(filePath);

		// Parse the xml file and create a document object
		Document document = builder.parse(xmlFile);

		// Get the root element
		Element root = document.getDocumentElement();
		System.out.println("Root Element: " + root.getNodeName());

		// Get all Child nodes of root node
		NodeList nodeList = root.getChildNodes();

		// Iterating through all child nodes
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			// Check if the node is an element
			if (node.getNodeType() == node.ELEMENT_NODE) {
				Element element = (Element) node;

				// extract attributes
				String bookId = element.getAttribute("id");
				System.out.println("\nBook ID: " + bookId);

				// Extract child elements
				String title = element.getElementsByTagName("title").item(0).getTextContent();
				String author = element.getElementsByTagName("author").item(0).getTextContent();
				String genre = element.getElementsByTagName("genre").item(0).getTextContent();
				String price = element.getElementsByTagName("price").item(0).getTextContent();
				String currency = element.getElementsByTagName("price").item(0).getAttributes().getNamedItem("currency")
						.getNodeValue();
				String publishDate = element.getElementsByTagName("publish_date").item(0).getTextContent();

				// Print book details
				System.out.println("Title: " + title);
				System.out.println("Author: " + author);
				System.out.println("Genre: " + genre);
				System.out.println("Price: " + price + " " + currency);
				System.out.println("Publish Date: " + publishDate);
			}
		}

	}
}
