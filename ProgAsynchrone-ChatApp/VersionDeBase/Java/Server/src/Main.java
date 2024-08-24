import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Main {
    private static final int PORT = 12345;

    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.submit(() -> handleClientMessagesAsync(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClientMessagesAsync(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            writer.write("Enter your name: ");
            writer.newLine();
            writer.flush();
            String userName = reader.readLine();
            System.out.println(userName + " has joined the chat.");

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(userName + ": " + message);
            }
        } catch (IOException e) {
            // Handle disconnection
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("A user has left the chat.");
        }
    }
}