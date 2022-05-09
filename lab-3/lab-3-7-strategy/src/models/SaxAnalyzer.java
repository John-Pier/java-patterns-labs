package models;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.*;

public class SaxAnalyzer extends DefaultHandler implements XmlAnalyzer {

    private final SAXParserFactory factory = SAXParserFactory.newInstance();
    private final SAXParser saxParser;

    {
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private double correctAverage = -1;
    private double currentAverage = -1;
    private final String path;

    public SaxAnalyzer(String path) {
        this.path = path;
    }

    @Override
    public void parse() {
        if (saxParser == null || path == null) {
            System.err.println("Error, some instances is null!");
            return;
        }

        var handler = new StudentSaxHandler();

        try {
            this.saxParser.parse(this.path, handler);
            this.currentAverage = handler.average;
            this.correctAverage = handler.getCorrectAverage();
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fixAverage(String pathToWrite) {
        if (saxParser == null || pathToWrite == null) {
            System.err.println("Error, some instances is null!");
            return;
        }

        var streamResult = new StreamResult(pathToWrite);

        try {
            this.saxParser.parse(this.path, new StudentSaxWriteHandler(correctAverage, streamResult));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public double getAverage() {
        return currentAverage;
    }

    @Override
    public double getCorrectAverage() {
        return correctAverage;
    }

    @Override
    public boolean isNeedFix() {
        return currentAverage != correctAverage;
    }

    private static class StudentSaxHandler extends DefaultHandler {
        private final ArrayList<Double> numbers = new ArrayList<>();
        private boolean isAverage = false;
        public Double average;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equalsIgnoreCase("student")) {
                isAverage = false;
            } else if (qName.equalsIgnoreCase("subject")) {
                var markValue = attributes.getValue("mark");
                var mark = Double.parseDouble(markValue);
                numbers.add(mark);

            } else if (qName.equalsIgnoreCase("average")) {
                isAverage = true;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (isAverage) {
                var averageValue = new String(ch, start, length);
                average = Double.parseDouble(averageValue);
            }
        }

        public double getCorrectAverage() {
            return Arrays.stream(numbers.toArray()).mapToDouble(value -> (double) value).average().orElse(Double.NaN);
        }
    }

    private static class StudentSaxWriteHandler extends DefaultHandler {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        SAXTransformerFactory saxTransformerFactory;
        TransformerHandler transformerHandler;

        {
            if (!transformerFactory.getFeature(SAXTransformerFactory.FEATURE)) {
                throw new RuntimeException(
                        "Did not find a SAX-compatible TransformerFactory.");
            }
            saxTransformerFactory = (SAXTransformerFactory) transformerFactory;
            try {
                transformerHandler = saxTransformerFactory.newTransformerHandler();
            } catch (TransformerConfigurationException e) {
                throw new RuntimeException(e);
            }
        }

        private boolean isAverage = false;
        private final Double average;

        public StudentSaxWriteHandler(Double average, Result result) {
            this.average = average;
            transformerHandler.setResult(result);
        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            transformerHandler.startPrefixMapping(prefix, uri);
        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {
            transformerHandler.endPrefixMapping(prefix);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            this.transformerHandler.startElement(uri, localName, qName, attributes);
            if (qName.equalsIgnoreCase("average")) {
                isAverage = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            transformerHandler.endElement(uri, localName, qName);
        }

        @Override
        public void startDocument() throws SAXException {
            transformerHandler.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            transformerHandler.endDocument();
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (isAverage) {
                var result = String.valueOf(average).toCharArray();
                transformerHandler.characters(result, 0, result.length);
            } else {
                transformerHandler.characters(ch, start, length);
            }
        }
    }
}
