package de.hrw.dsalab.distsys.chat.xml_tool;


import de.hrw.dsalab.distsys.chat.xml.ChatMulticast_XML;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import javax.swing.text.html.parser.DTD;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class ChatHandlerTool implements ContentHandler {

    private String currentValue;
    private String currentElement;
    private boolean firstElement = true;
    private HashMap<String, StringBuffer> hashMap;
    private ArrayList<StringBuffer> list;
    private ArrayList<String> elementList;
    private int currentUnclosedIndex = -1;
    private int currentIndex = -1;
    private boolean ignore;
    private String until;

    public ChatHandlerTool() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();
        elementList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

        if (!ignore) {

            StringBuffer element = new StringBuffer("<!ELEMENT " + localName + " ");

            if (Objects.equals(currentElement, localName)) {
                list.get(currentUnclosedIndex -1).append("(" + localName + "*)>");
                ignore = true;
                until = localName;  //... closes

            } else {
                list.add(element);
                elementList.add(localName);
                currentElement = localName;
                currentIndex++;
                currentUnclosedIndex++;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (ignore && !Objects.equals(localName, until)) {
            currentValue = null;
            return;
        }

        if (Objects.equals(localName, currentElement)) {

            currentUnclosedIndex--;

            if (currentValue != null) {
                list.get(currentIndex).append("(#PCDATA)>");
            }

        } else {

            list.get(currentUnclosedIndex).append("(");

            for (int i = currentUnclosedIndex + 1; i <= currentIndex; i++) {
                list.get(currentUnclosedIndex).append(elementList.get(i) + ",");
            }
            list.get(currentUnclosedIndex).deleteCharAt(list.get(currentUnclosedIndex).length() - 1);
            list.get(currentUnclosedIndex).append(")>");

        }

        currentValue = null;
        currentElement = localName;
        until = null;
        ignore = false;

        if (localName.equals("example")) {
            for (StringBuffer element : list) {
                System.out.println(element);
            }
        }


/**
 if (localName.equals("ip")){
 ip = currentValue;
 }

 if (localName.equals("port")){
 port = currentValue;
 }

 if (localName.equals("nickname")){
 nickname = currentValue;
 }

 if (localName.equals("chat")){
 // new ChatMulticast_XML(ip.trim(), port.trim(), nickname.trim());
 }
 **/
    }

    public void characters(char[] ch, int start, int length) {
        currentValue = new String(ch, start, length);
    }


    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }


    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }
}
