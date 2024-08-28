import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Main {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            ExecutorService executor = Executors.newFixedThreadPool(2);

            // Start the task for receiving messages
            Future<?> receiveTask = executor.submit(() -> receiveMessagesAsync(socket));

            Thread.sleep(100);

            // Start the task for sending messages
            Future<?> sendTask = executor.submit(() -> sendMessagesAsync(socket));

            // Wait for both tasks to complete
            receiveTask.get();
            sendTask.get();

            executor.shutdown();
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void receiveMessagesAsync(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("\r" + message);
                System.out.print("You: ");
            }
        } catch (IOException e) {
            // Handle disconnection
        }
    }

    private static void sendMessagesAsync(Socket socket) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                writer.write(userInput);
                writer.newLine();
                writer.flush();
                System.out.print("You: ");
            }
        } catch (IOException e) {
            // Handle disconnection
        }
    }
}
