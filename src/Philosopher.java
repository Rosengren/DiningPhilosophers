import java.util.Random;

public class Philosopher implements Runnable {

    private static final int MAX_SLEEP_TIME = 1000;

    private int id;
    private int eatingTimeInMillis;
    private Random rand = new Random();
    private Fork firstFork, secondFork;
    private volatile boolean running = true;

    /** Metrics **/
    private int timesEaten = 0;
    private int timeSpentEating = 0;
    private int timeSpentThinking = 0;


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

    public void printMetrics() {
        System.out.println(" - Philosopher " + id + ":");
        System.out.println("\tTime Spent Thinking: " + timeSpentThinking + " milliseconds");
        System.out.println("\tTime Spent Eating: " + timeSpentEating + " milliseconds");
        System.out.println("\tTimes Eaten: " + timesEaten + "\n");
    }

    private void think() {
        System.out.println("Philosopher " + id + " is thinking");
        try {
            int thinkingTime = rand.nextInt(MAX_SLEEP_TIME);
            timeSpentThinking += thinkingTime;
            Thread.sleep(thinkingTime);
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
        firstFork.release();
        secondFork.release();
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating");
        timeSpentEating += eatingTimeInMillis;
        Thread.sleep(eatingTimeInMillis);
    }

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {

        try {
            while (running) {
                pickUpForks();
                eat();
                releaseForks();
                timesEaten++;
                think();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}