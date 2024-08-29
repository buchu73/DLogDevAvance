import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private PrintWriter writer;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (var stream = clientSocket.getInputStream();
             var reader = new BufferedReader(new InputStreamReader(stream));) {

            writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            // Read user name
            writer.println("Enter your name: ");
            String userName = reader.readLine();
            String message = userName + " has joined the chat.";
            System.out.println(message);
            broadcast(message);

            try {
                String messageReceived;
                while ((messageReceived = reader.readLine()) != null) {
                    message = userName + ": " + messageReceived;
                    System.out.println(message);
                    broadcast(message);
                }
            } catch (IOException e) {
                // Handle disconnection
            } finally {
                System.out.println(userName + " has left the chat.");
                broadcast(userName + " has left the chat.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcast(String message){
        Main.broadcast(this, message);
    }

    public void sendMessage(String message) {
        writer.println(message);
    }
}