package models;

import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.IOException;
import java.lang.reflect.Array;
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
        //
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
                System.out.println("student found");
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
                System.out.println("First Name: " + Arrays.toString(ch));

                average = Double.parseDouble(averageValue);
            }
        }

        public double getCorrectAverage() {
            return Arrays.stream(numbers.toArray()).mapToDouble(value -> (double) value).average().orElse(Double.NaN);
        }
    }
}
