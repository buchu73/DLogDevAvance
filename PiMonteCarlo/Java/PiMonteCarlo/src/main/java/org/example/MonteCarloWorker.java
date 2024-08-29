package org.example;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloWorker implements Runnable{

    private final int workerIndex;
    private final long nbPoints;
    private final CountDownLatch latch;
    private long insideCircle;

    public MonteCarloWorker(int nbWorker, long nbPoints, CountDownLatch latch){
        this.workerIndex = nbWorker;
        this.nbPoints = nbPoints;
        this.latch = latch;
    }

    public long getInsideCircle(){
        return this.insideCircle;
    }

    @Override
    public void run() {
//        Random random = new Random((int)workerIndex);

        try{
            for (long i = 0; i < nbPoints; i++)
            {
                double x = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);;
                double y = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
                if (x * x + y * y <= 1.0)
                {
                    insideCircle++;
                }
            }
        }
        finally {
            latch.countDown();
        }
    }

    private static double GenerateRandomPoint(Random random)
    {
        double randomNb = random.nextDouble();

        //scale it between -1, +1
        return (randomNb * 2) - 1;
    }
}
