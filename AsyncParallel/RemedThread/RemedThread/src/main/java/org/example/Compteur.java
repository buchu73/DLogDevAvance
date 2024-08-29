package org.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Compteur {

    private AtomicInteger compteur = new AtomicInteger();

    //private Lock lock = new ReentrantLock();

//    public synchronized void increment() throws InterruptedException {
//        compteur++;
//        Thread.sleep(50);
//    }

//    public void increment() throws InterruptedException {
//        lock.lock();
//
//        compteur++;
//        Thread.sleep(50);
//
//        lock.unlock();
//    }

    public void increment() throws InterruptedException {
        compteur.incrementAndGet();
        Thread.sleep(50);
    }

    public int getCompteur() {
        return compteur.get();
    }
}
