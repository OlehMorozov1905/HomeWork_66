package ait.elevator;

import ait.elevator.model.Elevator;
import ait.elevator.task.Truck;

public class ElevatorAppl {
    private static final int N_TRUCK = 100000;
    private static final int N_RACES = 10;
    private static final int CAPACITY = 20;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Elevator lenin = new Elevator("V.I.Lenin");
        Elevator stalin = new Elevator("I.V.Stalin");

        Truck[] trucks = new Truck[N_TRUCK];
        for (int i = 0; i < trucks.length; i++) {
            trucks[i] = new Truck(N_RACES, CAPACITY, lenin, stalin);
        }

        Thread[] threads = new Thread[N_TRUCK];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(trucks[i]);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        double executionTimeInSeconds = executionTime / 1000.0;

        System.out.println("Elevator " + lenin.getName() + " has " + lenin.getCurrentVolume());
        System.out.println("Elevator " + stalin.getName() + " has " + stalin.getCurrentVolume());
        System.out.println("Total execution time: " + executionTimeInSeconds + " seconds");
    }
}

