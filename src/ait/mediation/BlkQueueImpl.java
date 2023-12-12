package ait.mediation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlkQueueImpl<T> implements BlkQueue<T> {
    private final Queue<T> queue;
    private final int maxSize;
    private final Lock lock;
    private final Condition notFullCondition;
    private final Condition notEmptyCondition;
    public BlkQueueImpl(int maxSize) {
        this.queue = new LinkedList<>();
        this.maxSize = maxSize;
        this.lock = new ReentrantLock();
        this.notFullCondition = lock.newCondition();
        this.notEmptyCondition = lock.newCondition();
    }

    @Override
    public void push(T message) {
        lock.lock();
        try {
            while (queue.size() == maxSize) {
                try {
                    notFullCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(message);
            notEmptyCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T pop() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    notEmptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T message = queue.poll();
            notFullCondition.signal();
            return message;
        } finally {
            lock.unlock();
        }
    }
}
