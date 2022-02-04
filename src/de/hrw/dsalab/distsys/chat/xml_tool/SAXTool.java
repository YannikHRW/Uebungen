package de.hrw.dsalab.distsys.chat.xml_tool;

import de.hrw.dsalab.distsys.chat.xml.ChatHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SAXTool {

    public static void main(String[] args) {

        try{

            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            FileReader reader = new FileReader("/Users/yanni/eclipse-workspace/VS/Chat.xml");
            InputSource inputSource = new InputSource(reader);

            xmlReader.setContentHandler(new ChatHandlerTool());
            xmlReader.parse(inputSource);

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (SAXException e){
            e.printStackTrace();
        }

    }

}
