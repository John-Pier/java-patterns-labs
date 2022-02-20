import java.io.*;
import java.nio.charset.StandardCharsets;

public class StringStreamAdapter implements StreamAdapter {

    private final OutputStreamWriter stream;

    public StringStreamAdapter(OutputStream stream) {
        this.stream = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
    }

    public void writeStrings(String[] strings) throws IOException {
        for (String current: strings) {
            this.stream.write(current + "\n");
        }
        this.stream.flush();
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}