package models;

import configs.Config;

import java.io.*;
import java.net.Socket;

public class RemoteMultiple implements Multiple {

    @Override
    public double multiple(double a, double b) {
        double result = Double.NaN;
        try (Socket clientSocket = new Socket(Config.SERVER_HOST_NAME, Config.SERVER_PORT)) {
            var inputStream = new DataInputStream(clientSocket.getInputStream());
            var outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            outputStream.writeDouble(a);
            outputStream.writeDouble(b);
            outputStream.flush();

            result = inputStream.readDouble();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return result;
    }
}
