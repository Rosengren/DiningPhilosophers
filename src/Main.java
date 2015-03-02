import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void displayMetrics(List<Philosopher> philosophers) {
        System.out.println("--------- METRICS ---------\n");
        for (Philosopher p : philosophers)
            p.printMetrics();
        System.out.println("--------- ------- ---------");
    }

    private static final int TOTAL_RUN_TIME_IN_MILLIS = 60000;

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            System.out.println("Missing arguments:\n\t - Number of philosophers" +
                    "\n\t - Number of number of milliseconds that each philosopher takes to eat.");
        }

        int numberOfPhilosophers = Integer.parseInt(args[0]);
        int timeToEatInMilliseconds = Integer.parseInt(args[1]);

        ArrayList<Fork> forks = new ArrayList<Fork>();
        ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();

        for (int i = 0; i < numberOfPhilosophers; i ++) forks.add(new Fork(i));

        for (int i = 0; i < numberOfPhilosophers; i++) {
            int next = (i == numberOfPhilosophers - 1) ? 0: i + 1;
            philosophers.add(new Philosopher(i, timeToEatInMilliseconds, forks.get(i), forks.get(next)));
        }

        for (Philosopher p : philosophers)
            new Thread(p).start();

        Thread.sleep(TOTAL_RUN_TIME_IN_MILLIS);

        for (Philosopher p : philosophers)
            p.terminate();

        displayMetrics(philosophers);
        System.out.println("Exiting Application");
        System.exit(0);
    }
}