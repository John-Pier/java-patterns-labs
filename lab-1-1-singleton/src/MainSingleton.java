import java.io.*;
public class MainSingleton {
    public static void main(String[] args) throws IOException {
        var properties = PropertiesConfig.getInstance().getProperties();
        System.out.println(properties.getProperty("test_string"));
    }
}
