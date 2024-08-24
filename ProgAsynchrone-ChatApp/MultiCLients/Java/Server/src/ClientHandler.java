import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (var stream = clientSocket.getInputStream();
             var reader = new BufferedReader(new InputStreamReader(stream));
             var writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true)) {

            // Read user name
            writer.println("Enter your name: ");
            String userName = reader.readLine();
            System.out.println(userName + " has joined the chat.");

            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println(userName + ": " + message);
                }
            } catch (IOException e) {
                // Handle disconnection
            } finally {
                System.out.println(userName + " has left the chat.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}