import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
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

        while(true);
    }
}