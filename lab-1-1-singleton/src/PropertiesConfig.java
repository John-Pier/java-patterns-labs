import java.io.*;
import java.util.Properties;

public class PropertiesConfig {

    private static PropertiesConfig propertiesConfig;
    private final Properties properties;

    private PropertiesConfig(String path) throws IOException {
        properties = new Properties();
        properties.load(new FileReader(path));
    }

    public synchronized static PropertiesConfig getInstance(String initialPath) throws IOException{
        if (propertiesConfig == null)
        {
            propertiesConfig = new PropertiesConfig(initialPath);
        }
        return propertiesConfig;
    }

    public synchronized static PropertiesConfig getInstance() throws IOException{
        if (propertiesConfig == null)
        {
            propertiesConfig = new PropertiesConfig("lab-1-1-singleton/src/config.properties");
        }
        return propertiesConfig;
    }

    public Properties getProperties() {
        return properties;
    }
}
