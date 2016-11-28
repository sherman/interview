package org.sherman.interview.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis Gabaydulin
 * @since 28/11/2016
 */
public class WaitNotifier {
    private static final Logger log = LoggerFactory.getLogger(WaitNotifier.class);

    private final Object monitor = new Object();
    private final Waiter w = new Waiter();
    private final Notifier n = new Notifier();

    private class Waiter extends Thread {
        @Override
        public void run() {
            log.info(" Waiter started");
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                log.info(" Waiter notified");
            }
        }
    }

    private class Notifier extends Thread {
        @Override
        public void run() {
            log.info("Notifier started");
            synchronized (monitor) {
                monitor.notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifier waitNotifier = new WaitNotifier();
        waitNotifier.w.start();
        Thread.sleep(10);
        waitNotifier.n.start();
    }
}
