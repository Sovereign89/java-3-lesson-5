package ru.geekbrains;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.StartRace();
    }

    public void StartRace() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(), new Tunnel(), new Road(), new Tunnel());
        Car[] cars = new Car[GlobalConstants.RACE_MAX_CARS];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 25));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            GlobalConstants.countDownLatchStart.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            GlobalConstants.countDownLatchFinish.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

