package org.example;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainBis {

    private static final int NUMBER_OF_WORKER = 3;
    private static final int NUMBER_OF_MESSAGES = 200;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_WORKER);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_WORKER);

        for (int i = 1; i <= NUMBER_OF_WORKER; i++) {
            int workerId = i;
            executorService.submit(() -> {
                try {
                    int messageCount = 1;
                    while(messageCount <= NUMBER_OF_MESSAGES){
                        System.out.println("Message " + messageCount + " from worker " + workerId);
                        messageCount++;

                        Thread.sleep(250);
                    }
                } catch (InterruptedException e) {
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
