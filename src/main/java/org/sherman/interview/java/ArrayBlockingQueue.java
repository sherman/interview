package org.sherman.interview.java;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Denis Gabaydulin
 * @since 26.10.16
 */
public class ArrayBlockingQueue<T> {
    private static final Logger log = LoggerFactory.getLogger(ArrayBlockingQueue.class);

    private final Object[] data;
    private final int maxSize;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private int size;

    private int putIndex;
    private int takeIndex;

    public ArrayBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        this.data = new Object[maxSize];
    }

    public void offer(@NotNull T elt) throws InterruptedException {
        try {
            lock.lockInterruptibly();

            while (size == maxSize) {
                notFull.await();
            }

            data[putIndex] = elt;

            if (++putIndex == data.length) {
                putIndex = 0;
            }
            size++;

            log.info("p: {}, t: {}, s:{}", putIndex, takeIndex, size);

            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        try {
            lock.lockInterruptibly();

            while (size == 0) {
                notEmpty.await();
            }

            T elt = (T) data[takeIndex];
            data[takeIndex] = null;

            if (++takeIndex == data.length) {
                takeIndex = 0;
            }
            size--;

            log.info("p: {}, t: {}, s:{}", putIndex, takeIndex, size);

            notFull.signal();

            return elt;
        } finally {
            lock.unlock();
        }
    }
}
