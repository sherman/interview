package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Queue;

public class RpsLimiter {
    private final Queue<Request> window = new LinkedList<>();
    private final int maxRequests;
    private final int maxTimeMills;

    public RpsLimiter(int maxRequests, int maxTimeMills) {
        this.maxRequests = maxRequests;
        this.maxTimeMills = maxTimeMills;
    }

    public boolean isAccepted(@NotNull Request request) {
        long now = System.currentTimeMillis();

        do {
            Request r = window.peek();
            if (r == null) {
                break;
            }

            if (r.ts < now - maxTimeMills) {
                window.poll();
            } else {
                break;
            }
        } while (true);

        if (window.size() < maxRequests) {
            window.offer(request);
            return true;
        } else {
            return false;
        }
    }

    static class Request {
        final long ts;
        final long id;

        Request(long ts, long id) {
            this.ts = ts;
            this.id = id;
        }
    }
}
