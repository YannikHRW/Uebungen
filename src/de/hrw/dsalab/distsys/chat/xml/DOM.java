package de.hrw.dsalab.distsys.chat.xml;

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

public class DOM {

    public static void main(String [] args) throws SAXException, IOException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse( new File("/Users/yanni/eclipse-workspace/VS/Chat.xml"));

        NodeList nList = document.getElementsByTagName("chat");

        for(int NodeAtPosition = 0; NodeAtPosition < nList.getLength(); NodeAtPosition++){
            Node chat = nList.item(NodeAtPosition);
            Element e = (Element) chat;

            System.out.println("Dieser Brief ist von "+ e.getElementsByTagName("ip").item(0).getTextContent().trim() +
                    "; wohnhaft in der " + e.getElementsByTagName("port").item(0).getTextContent().trim() + " " + e.getElementsByTagName("nickname").item(0).getTextContent().trim());
        }
    }

}
