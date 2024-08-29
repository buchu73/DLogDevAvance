package org.example;

import java.util.Random;

public class MainMono {

    private static final long NumPoints = 10000000000L; // Nombre total de points

    private static final Random random = new Random();

    public static void main(String[] args) {
        long insideCircle = 0;

        long startTime = System.nanoTime();

        // Génération des points et comptage de ceux à l'intérieur du cercle
        for (long i = 0; i < NumPoints; i++)
        {
            double x = GenerateRandomPoint();
            double y = GenerateRandomPoint();
            if (x * x + y * y <= 1.0)
            {
                insideCircle++;
            }
        }

        // Estimation de Pi
        double piEstimate = 4.0 * insideCircle / NumPoints;

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        double elapsedTimeInSeconds = elapsedTime / 1_000_000_000.0;

        System.out.println("Estimated Pi: " + piEstimate + ", computed in " + elapsedTimeInSeconds + " seconds");
    }

    private static double GenerateRandomPoint()
    {
        double randomNb = random.nextDouble();

        //scale it between -1, +1
        return (randomNb * 2) - 1;
    }
}