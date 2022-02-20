import java.io.*;
import java.nio.charset.StandardCharsets;

public class MainAdapter {
    private static final String[] strings = new String[] {"1", "2", "3", "test-1"};
    private static final String filePath = "out/test.b";

    public static void main(String[] args) {
        try(var outputStream = new FileOutputStream(filePath)) {
            StreamAdapter adapter = new StringStreamAdapter(outputStream);
            adapter.writeStrings(strings);

            // TEST
            var inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
            while (inputStream.ready()) {
                System.out.println(inputStream.readLine());
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}