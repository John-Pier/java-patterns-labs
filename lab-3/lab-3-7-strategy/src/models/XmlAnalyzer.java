package models;

public interface XmlAnalyzer {
    void parse();

    void fixAverage(String pathToWrite) throws Exception;

    double getAverage();

    double getCorrectAverage();

    boolean isNeedFix();
}
