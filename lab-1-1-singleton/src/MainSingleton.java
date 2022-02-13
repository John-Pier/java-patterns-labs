import java.io.*;
import java.util.Properties;

public class MainSingleton {
    public static void main(String[] args) throws IOException {
        var properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        System.out.println(properties.getProperty("test_string"));
    }
}
