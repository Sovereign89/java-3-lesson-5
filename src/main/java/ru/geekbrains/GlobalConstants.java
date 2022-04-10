package ru.geekbrains;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class GlobalConstants {
    public static final int RACE_MAX_CARS = 5;
    public static final Semaphore semaphore = new Semaphore(RACE_MAX_CARS/ 2);
    public static final CyclicBarrier cyclicBarrier = new CyclicBarrier(RACE_MAX_CARS);
    public static final CountDownLatch countDownLatchStart = new CountDownLatch(RACE_MAX_CARS);
    public static final CountDownLatch countDownLatchFinish = new CountDownLatch(RACE_MAX_CARS);
    public static final ReentrantLock reentrantLock = new ReentrantLock();

}
