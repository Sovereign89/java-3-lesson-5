package ru.geekbrains;

public class Tunnel extends Stage {

    public Tunnel() {
        this.length = (int) (Math.random() * 100);
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                GlobalConstants.semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                GlobalConstants.semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
