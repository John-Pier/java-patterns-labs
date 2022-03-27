package models;

import java.io.OutputStream;

public interface Command {
    void execute(OutputStream stream);
}
