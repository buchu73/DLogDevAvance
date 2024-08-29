package org.example;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    private final int index;
    private final CountDownLatch latch;
    private final Compteur compteur;

    public Worker(int workerIndex, CountDownLatch latch, Compteur compteur){
        this.index = workerIndex;
        this.latch = latch;
        this.compteur = compteur;
    }
    
    @Override
    public void run() {
        try{
            for (int i = 0; i != 200; ++i){
//                System.out.println("Message " + i + " from worker " + index);
                this.compteur.increment();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            latch.countDown();
        }
    }
}
