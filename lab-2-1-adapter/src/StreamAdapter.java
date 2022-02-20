import java.io.*;

public interface StreamAdapter extends Closeable {
    void writeStrings(String[] strings) throws IOException;
}
