package models;

public class XmlStudentHelper {
    private XmlAnalyzer analyzer;

    public XmlStudentHelper(String path) {
        this(new DomAnalyzer(path));
    }

    public XmlStudentHelper(XmlAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void setAnalyzer(XmlAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void checkAndFix(String newPath) throws Exception {
        this.analyzer.parse();
        System.out.println("Correct Average: " + this.analyzer.getCorrectAverage());
        System.out.println("Current Average: " + this.analyzer.getAverage());
        if (this.analyzer.isNeedFix()) {
            System.out.println("Need Fix!");
            this.analyzer.fixAverage(newPath);
            System.out.println("Fix into new file: " + newPath);
        }
    }
}
