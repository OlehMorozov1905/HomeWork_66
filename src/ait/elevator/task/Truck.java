package ait.elevator.task;

import ait.elevator.model.Elevator;

public class Truck implements Runnable{
    private static Object monitor1 = new Object();
    private static Object monitor2 = new Object();
    private int nRaces;
    private int capacity;
    private Elevator elevator1;
    private Elevator elevator2;

    public Truck(int nRaces, int capacity, Elevator elevator1, Elevator elevator2) {
        this.nRaces = nRaces;
        this.capacity = capacity;
        this.elevator1 = elevator1;
        this.elevator2 = elevator2;
    }

    @Override
    public void run() {
        for (int i = 0; i < nRaces; i++) {
            synchronized (monitor1) {
                elevator1.add(capacity / 2);
            }
            synchronized (monitor2) {
                elevator2.add(capacity / 2);
            }
        }
    }
}
