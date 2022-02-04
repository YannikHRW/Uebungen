package de.hrw.dsalab.distsys.chat.xml_tool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMTool {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("/Users/yanni/eclipse-workspace/VS/Chat.xml"));

        NodeList nList = document.getElementsByTagName("example");

        for (int NodeAtPosition = 0; NodeAtPosition < nList.getLength(); NodeAtPosition++) {
            Node chat = nList.item(NodeAtPosition);
            Element e = (Element) chat;

            System.out.println(e.getElementsByTagName("*").item(0).getTextContent().trim());
            System.out.println(nList.item(NodeAtPosition));
            System.out.println(e);
            System.out.println();
        }
    }

}
