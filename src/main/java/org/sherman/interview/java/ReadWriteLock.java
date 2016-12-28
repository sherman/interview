package org.sherman.interview.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis Gabaydulin
 * @since 28/12/2016
 */
public class ReadWriteLock {
    private static final Logger log = LoggerFactory.getLogger(ReadWriteLock.class);

    private final Object monitor = new Object();
    private boolean writeBlocked;
    private int readBlocked;

    public void lockWrite() throws InterruptedException {
        synchronized (monitor) {
            log.info("pre write lock");
            while (writeBlocked || readBlocked > 0) {
                monitor.wait();
            }
            writeBlocked = true;
            log.info("post write lock");
        }
    }

    public void unlockWrite() {
        synchronized (monitor) {
            log.info("pre write unlock");
            if (writeBlocked) {
                writeBlocked = false;
                monitor.notifyAll();
            }
            log.info("post write unlock");
        }
    }

    public void lockRead() throws InterruptedException {
        synchronized (monitor) {
            log.info("pre read lock");
            while (writeBlocked) {
                monitor.wait();
            }
            readBlocked++;
            log.info("post read lock");
        }
    }

    public void unlockRead() {
        synchronized (monitor) {
            log.info("pre read unlock");
            if (!writeBlocked && readBlocked > 0) {
                readBlocked--;
                monitor.notifyAll();
            }
            log.info("post read unlock");
        }
    }
}
