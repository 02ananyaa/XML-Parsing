package com.file;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// suppose we want to update  price of book id b004

public class UpdateXMLFile {
	public static void main(String args[]) {

		try {
			// Create a DocumentBuilderFactory instance
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			// Create a DocumentBuilder instance
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Path to the XML file
			String filePath = "src/main/resources/sample.xml";
			File xmlFile = new File(filePath);

			// Parse the XML file and create a Document object
			Document document = builder.parse(xmlFile);

			// Get all <book> elements
			NodeList bookList = document.getElementsByTagName("book");

			// Iterate through each <book> node
			for (int i = 0; i < bookList.getLength(); i++) {
				Node bookNode = bookList.item(i);

				// Check if the node is an element
				if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
					Element bookElement = (Element) bookNode;

					// Get the book ID
					String bookId = bookElement.getAttribute("id");

					// Check if this is the book with id="b004"
					if (bookId.equals("b004")) {
						// Modify the price of book with id="b004"
						NodeList priceNodeList = bookElement.getElementsByTagName("price");
						if (priceNodeList.getLength() > 0) {
							Element priceElement = (Element) priceNodeList.item(0);
							priceElement.setTextContent("50.00"); // Update the price value
							priceElement.setAttribute("currency", "USD"); // Update the currency if needed
						}
					}
				}
			}

			// Save the updated XML file after modification
			System.out.println("Updating XML File...");
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new FileOutputStream(xmlFile));

			// Save the changes to the XML file
			transformer.transform(source, result);

			System.out.println("\nXML file updated successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
