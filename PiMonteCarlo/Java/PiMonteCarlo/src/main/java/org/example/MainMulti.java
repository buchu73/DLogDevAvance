package org.example;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MainMulti {
    private static final long NumPoints = 10000000000L; // Nombre total de points
    private static final int NumThreads = 12;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(NumThreads);

        long startTime = System.nanoTime();

        MonteCarloWorker[] workers = new MonteCarloWorker[NumThreads];
        for (int i = 0 ; i != NumThreads; ++i) {
            MonteCarloWorker worker = new MonteCarloWorker(i, NumPoints / NumThreads, latch);
            workers[i] = worker;
            Thread thread = new Thread(worker);
            thread.start();
        }

        latch.await();

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        double elapsedTimeInSeconds = elapsedTime / 1_000_000_000.0;

        long insideCircleTotal = 0;
        for (MonteCarloWorker worker : workers) {
            insideCircleTotal += worker.getInsideCircle();
        }

        // Estimation de Pi
        double piEstimate = 4.0 * insideCircleTotal / NumPoints;

        System.out.println("Estimated Pi: " + piEstimate + ", computed in " + elapsedTimeInSeconds + " seconds");
    }
}
