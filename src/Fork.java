
public class Fork {

    private int id;
    private Thread holder;

    public Fork(int id) {
        this.id = id;
        holder = null;
    }

    public int getId() {
        return id;
    }

    public synchronized void acquire() throws InterruptedException {
        if (holder != null) wait();
        holder = Thread.currentThread();
    }

    public synchronized void release() {
        if (holder == Thread.currentThread())
            holder = null;
        notify();
    }

}