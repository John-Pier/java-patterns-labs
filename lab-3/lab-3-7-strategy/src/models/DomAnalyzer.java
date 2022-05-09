package models;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DomAnalyzer implements XmlAnalyzer {
    private DocumentBuilder builder;
    private Transformer transformer;

    private NodeList students;
    private Node studentAverageNode;

    private double correctAverage = -1;
    private double currentAverage = -1;
    private final String path;

    {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DomAnalyzer(String path) {
        this.path = path;
    }

    public void parse() {
        if (builder == null || transformer == null) {
            System.err.println("Error, some instances is null!");
            return;
        }

        try {
            var document = builder.parse(path);
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, document.getDoctype().getSystemId());

            students = document.getElementsByTagName("student");
            NodeList studentSubjects = ((Element) students.item(0)).getElementsByTagName("subject");
            studentAverageNode = ((Element) students.item(0)).getElementsByTagName("average").item(0);

            correctAverage = getCorrectAverage(studentSubjects);
            currentAverage = Double.parseDouble(studentAverageNode.getTextContent());

            System.out.println("Correct Average: " + correctAverage + "\nCurrent Average: " + currentAverage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isNeedFix() {
        return correctAverage != currentAverage;
    }

    @Override
    public void fixAverage(String pathToWrite) throws Exception {
        if (builder == null || transformer == null || students == null || studentAverageNode == null) {
            System.err.println("Error, some instances is null!");
            return;
        }

        if (correctAverage != currentAverage) {
            studentAverageNode.setTextContent(String.valueOf(correctAverage));
        }

        System.out.println("Correct Average: " + correctAverage + "\nCurrent Average: " + currentAverage);

        createDocumentWithAverage(pathToWrite);
    }

    private void createDocumentWithAverage(String fileNamePath) throws TransformerException {
        var correctDocument = builder.newDocument();
        var source = new DOMSource(correctDocument);
        var streamResult = new StreamResult(fileNamePath);

        for (int i = 0; i < students.getLength(); i++) {
            correctDocument.appendChild(correctDocument.importNode(students.item(i), true));
        }

        transformer.transform(source, streamResult);
    }

    public double getAverage() {
        return currentAverage;
    }

    public double getCorrectAverage() {
        return correctAverage;
    }

    private double getCorrectAverage(NodeList nodeList) {
        double markSum = 0;
        for (int i = 0; i < nodeList.getLength(); i++) {
            markSum += Integer.parseInt(((Element) nodeList.item(i)).getAttribute("mark"));
        }
        return markSum / nodeList.getLength();
    }
}
