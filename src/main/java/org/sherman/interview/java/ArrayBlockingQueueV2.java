package org.sherman.interview.java;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis Gabaydulin
 * @since 26.10.16
 */
public class ArrayBlockingQueueV2<T> {
    private static final Logger log = LoggerFactory.getLogger(ArrayBlockingQueueV2.class);

    private final Object[] data;
    private final int maxSize;

    private int size;

    private int putIndex;
    private int takeIndex;

    public ArrayBlockingQueueV2(int maxSize) {
        this.maxSize = maxSize;
        this.data = new Object[maxSize];
    }

    public void offer(@NotNull T elt) throws InterruptedException {
        synchronized (this) {
            while (size == maxSize) {
                wait();
            }

            data[putIndex] = elt;

            if (++putIndex == data.length) {
                putIndex = 0;
            }
            size++;

            log.info("p: {}, t: {}, s:{}", putIndex, takeIndex, size);

            notify();
        }
    }

    public T take() throws InterruptedException {
        synchronized (this) {
            while (size == 0) {
                wait();
            }

            T elt = (T) data[takeIndex];
            data[takeIndex] = null;

            if (++takeIndex == data.length) {
                takeIndex = 0;
            }
            size--;

            log.info("p: {}, t: {}, s:{}", putIndex, takeIndex, size);

            notify();

            return elt;
        }
    }
}
