package org.example;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);
        Compteur compteur = new Compteur();

        for (int i = 0; i != 3; ++i) {
            Worker worker = new Worker(i, latch, compteur);
            Thread thread = new Thread(worker);
            thread.start();
        }

        latch.await();

        System.out.println("Valeur du compteur :" + compteur.getCompteur());
     }
}