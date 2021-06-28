package com.axity.crud.to;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtil {

	public static Element getRoot(String xml) {
		Element root = null;
		if (StringUtils.isNotEmpty(xml)) {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				InputStream is = new ByteArrayInputStream(xml.getBytes());

				Document document = builder.parse(is);
				root = document.getDocumentElement();
			} catch (ParserConfigurationException | SAXException | IOException e) {

			}
		}
		return root;
	}

	public static Node getNode(String name, Element element) {
		return getNode(name, element, 0);
	}

	public static Node getNode(String name, Element element, int index) {
		Node node = null;
		if (StringUtils.isNotBlank(name) && element != null) {
			NodeList nodeList = element.getElementsByTagName(name);
			if (nodeList != null && nodeList.getLength() > 0) {
				node = nodeList.item(index);
			}
		}
		return node;
	}

	public static String getText(String xml, String tag) {
		String text = null;

		if (StringUtils.isNotBlank(xml) && StringUtils.isNotBlank(tag)) {
			Element root = getRoot(xml);
			Node node = getNode(tag, root);
			if (node != null) {
				text = node.getTextContent();
			}
		}
		return text;
	}

}
