package de.hrw.dsalab.distsys.chat.xml_tool;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLGenerator {

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("example");
            doc.appendChild(rootElement);

            // letter
            Element letter = doc.createElement("letter");
            rootElement.appendChild(letter);

            // name
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode("Manuela Musterfrau"));
            letter.appendChild(name);

            // street
            Element street = doc.createElement("street");
            street.appendChild(doc.createTextNode("Mustergasse 3a"));
            letter.appendChild(street);


            // als XML schreiben
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("dein Pfad.dtd"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
