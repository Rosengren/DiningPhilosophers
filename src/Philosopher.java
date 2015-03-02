import java.util.Random;

public class Philosopher implements Runnable {

    private static final int MAX_SLEEP_TIME = 1000;

    private int id;
    private int eatingTimeInMillis;
    private Random rand = new Random();
    private Fork firstFork, secondFork;


    public Philosopher(int id, int eatingTime, Fork left, Fork right) {
        this.id = id;
        eatingTimeInMillis = eatingTime;
        if (left.getId() < right.getId()) {
            firstFork = left;
            secondFork = right;
        } else {
            firstFork = right;
            secondFork = left;
        }
    }

    private void sleep() {
        System.out.println("Philosopher " + id + " is sleeping");
        try {
            Thread.sleep(rand.nextInt(MAX_SLEEP_TIME));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pickUpForks() {
        System.out.println("Philosopher " + id + " is picking up forks");
        try {
            firstFork.acquire();
            secondFork.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void releaseForks() {
        System.out.println("Philosopher " + id + " is putting down forks");
        firstFork.releaseIfMine();
        secondFork.releaseIfMine();
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating");
        Thread.sleep(eatingTimeInMillis);
    }

    @Override
    public void run() {

        try {
            while (true) {
                System.out.println("Philosopher " + id + " is thinking");
                pickUpForks();
                eat();
                releaseForks();
                sleep();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}