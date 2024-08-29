package org.example;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class ClientsPool {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static final int NUMBER_OF_CLIENTS = 3;
    private static final int MESSAGE_INTERVAL_MS = 200;
    private static final int NUMBER_OF_MESSAGES_PER_CLIENT = 200;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_CLIENTS);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_CLIENTS);

        for (int i = 1; i <= NUMBER_OF_CLIENTS; i++) {
            int clientId = i;
            executorService.submit(() -> {
                try (Socket client = new Socket(SERVER_ADDRESS, SERVER_PORT);
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8))) {

                    String clientName = "Client-" + clientId;
                    writer.write(clientName);
                    writer.newLine();
                    writer.flush();

                    // Task to listen for incoming messages
                    CompletableFuture.runAsync(() -> {
                        String serverMessage;
                        try {
                            while ((serverMessage = reader.readLine()) != null) {
                                System.out.println(clientName + " received: " + serverMessage);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    int messagesSent = 1;
                    // Periodically send messages with a timestamp
                    while (messagesSent <= NUMBER_OF_MESSAGES_PER_CLIENT) {
                        String message = "Message " + messagesSent + " from " + clientName;
                        writer.write(message);
                        writer.newLine();
                        writer.flush();
                        messagesSent++;
                        Thread.sleep(MESSAGE_INTERVAL_MS);
                    }

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
