package ait.numbers.model;


import ait.numbers.task.OneGroupSum;

public class ThreadGroupSum extends GroupSum{
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // TODO Homework: reduce sum numbers of numberGroups, use Threads
        OneGroupSum[] tasks = new OneGroupSum[numberGroups.length];
        Thread[] threads = new Thread[numberGroups.length];

        for (int i = 0; i < numberGroups.length; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        try {
            for (int i = 0; i < numberGroups.length; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int result = 0;
        for (OneGroupSum task : tasks) {
            result += task.getSum();
        }

        return result;
    }
}
