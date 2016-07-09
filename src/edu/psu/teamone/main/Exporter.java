package edu.psu.teamone.main;

import java.io.File;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Exporter {

	public static void export(File file, Schedule schedule) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}

		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("schedule");
		doc.appendChild(rootElement);

		for (Entry<Section, Meeting> entry : schedule.getSections().entrySet()) {
			Element section = doc.createElement("section");
			rootElement.appendChild(section);
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return;
		}
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);

		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		System.out.println("File saved!");
	}

}
