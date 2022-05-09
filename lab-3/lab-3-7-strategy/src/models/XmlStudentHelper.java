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
        if (this.analyzer.isNeedFix()) {
            this.analyzer.fixAverage(newPath);
        }
    }
}
