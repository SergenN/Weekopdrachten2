package Opdracht11;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Manager {
    public static final int MAX_RESOURCES = 5;
    private int availableResources = MAX_RESOURCES;
    private LinkedList<Worker> paused = new LinkedList<>();

    /**
     * Decrease availableResources by count resources.
     * return 0 if sufficient resources available,
     * otherwise return -1
     */
    public synchronized int decreaseCount(int count, Worker worker) {
        if (availableResources < count){
            paused.push(worker);
            return -1;
        } else {
            availableResources -= count;
            return 0;
        }
    }

    public synchronized void increaseCount(int count) {
        availableResources += count;
        if (!paused.isEmpty()){
            Worker last = paused.getLast();
            last.releaseResources();
            paused.removeLast();
        }
    }
}
