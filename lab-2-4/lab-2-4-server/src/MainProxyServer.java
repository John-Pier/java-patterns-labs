import java.io.*;
import java.net.*;

public class MainProxyServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(Config.SERVER_PORT)) {
            System.out.println("Сервер запущен");
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                runTask(socket);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void runTask(Socket clientSocket) {
        System.out.println("Соединение с клиентом успешно: " + clientSocket.getLocalAddress() + ":" + clientSocket.getLocalPort());
        try (clientSocket) {
            var inputStream = new ObjectInputStream(clientSocket.getInputStream());
            var outputStream = new DataOutputStream(clientSocket.getOutputStream());

            double first = inputStream.readDouble();
            double second = inputStream.readDouble();

            System.out.println("Результат: " + first * second);
            outputStream.writeDouble(first * second);
            outputStream.flush();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
