import models.*;

public class StrategyMain {
    public static void main(String[] args) {
        if (args.length < 3) {
            return;
        }
        var path = args[0];
        var newPathOne = args[1];
        var newPathTwo = args[2];

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
