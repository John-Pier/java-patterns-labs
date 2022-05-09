import models.*;

public class StrategyMain {
    public static void main(String[] args) {
        var path = "lab-3/lab-3-7-strategy/resources/data.xml";
        var newPathOne = "out/new-data-sax.xml";
        var newPathTwo = "out/new-data-dom.xml";

        var domAnalyzer = new DomAnalyzer(path);
        var saxAnalyzer = new SaxAnalyzer(path);

        var helper = new XmlStudentHelper(saxAnalyzer);

        try {
            helper.checkAndFix(newPathOne);
        } catch (Exception e) {
            e.printStackTrace();
        }

        helper.setAnalyzer(domAnalyzer);
        try {
            helper.checkAndFix(newPathTwo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
