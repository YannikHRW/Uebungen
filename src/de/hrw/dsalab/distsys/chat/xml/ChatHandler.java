package de.hrw.dsalab.distsys.chat.xml;


import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.ArrayList;

public class ChatHandler implements ContentHandler {

    //private ArrayList<Chat> alleChats = new ArrayList<>();
    private String currentValue;
    //private Chat chat;

    private String ip;
    private String port;
    private String nickname;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
/**
        if (localName.equals("chat")){
            chat = new Chat(null, null, null);
        }
**/
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

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
            new ChatMulticast_XML(ip.trim(), port.trim(), nickname.trim());
        }

    }

    public void characters(char[] ch, int start, int length){
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
