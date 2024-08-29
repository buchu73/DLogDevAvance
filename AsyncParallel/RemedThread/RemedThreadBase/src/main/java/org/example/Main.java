package org.example;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numberOfWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numberOfWorkers);

        for (int i = 0; i < numberOfWorkers; i++) {
            new Thread(new Worker(latch, i)).start();
        }

        latch.await(); // Wait until all workers have finished
        System.out.println("All workers are done.");
    }
}