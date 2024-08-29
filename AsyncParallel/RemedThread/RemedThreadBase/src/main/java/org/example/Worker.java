package org.example;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    private final CountDownLatch latch;
    private final int NUMBER_OF_MESSAGES = 200;
    private final int workerIndex;

    public Worker(CountDownLatch latch, int workerIndex){
        this.latch = latch;
        this.workerIndex = workerIndex;
    }

    @Override
    public void run() {
        int messageCount = 1;

        try {
            while(messageCount <= NUMBER_OF_MESSAGES){
                System.out.println("Message " + messageCount + " from worker " + workerIndex);
                messageCount++;

                Thread.sleep(250);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            latch.countDown();
        }
    }
}
