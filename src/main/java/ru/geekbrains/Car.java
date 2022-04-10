package ru.geekbrains;

public class Car implements Runnable {

    private static int CARS_COUNT = 0;

    private static boolean winner = false;

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 1000));
            GlobalConstants.countDownLatchStart.countDown();
            System.out.println(this.name + " готов");
            GlobalConstants.cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        GlobalConstants.countDownLatchFinish.countDown();
        winner(this.getName());
    }

    public boolean winner(String name) {
        if (!winner) {
            try {
                GlobalConstants.reentrantLock.lock();
                winner = true;
                System.out.println("Победил " + name);
            } finally {
                GlobalConstants.reentrantLock.unlock();
            }
            return true;
        } else {
            return false;
        }
    }
}
