import models.*;

public class StrategyMain {
    public static void main(String[] args) {
        var path = "lab-3/lab-3-7-strategy/resources/data.xml";
        var newPath = "out/new-data.xml";

        var domAnalyzer = new DomAnalyzer(path);
        var saxAnalyzer = new SaxAnalyzer(path);

        var helper = new XmlStudentHelper(saxAnalyzer);

        try {
            helper.checkAndFix(newPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
