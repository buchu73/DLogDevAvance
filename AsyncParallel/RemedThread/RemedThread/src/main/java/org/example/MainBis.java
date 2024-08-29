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

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_WORKER);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_WORKER);

        for (int i = 1; i <= NUMBER_OF_WORKER; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    for (int j = 0; j != 200; ++j){
                        System.out.println("Message " + j + " from worker " + finalI);
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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
