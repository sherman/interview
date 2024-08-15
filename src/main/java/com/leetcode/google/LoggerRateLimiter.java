package com.leetcode.google;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoggerRateLimiter {
    private static final Logger logger = LoggerFactory.getLogger(LoggerRateLimiter.class);

    private final Map<String, Integer> messageToTimestamp = new HashMap<>();

    public boolean shouldPrintMessage(int timestamp, String message) {
        var ts = messageToTimestamp.getOrDefault(message, -1);
        if (ts == -1) {
            messageToTimestamp.put(message, timestamp);
            return true;
        } else {
            if (timestamp - ts >= 10) {
                messageToTimestamp.put(message, timestamp);
                return true;
            } else {
                return false;
            }
        }
    }

    //[[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
    @Test
    public void test() {
        var data = new Message[] {
            new Message(1, "foo"),
            new Message(2, "bar"),
            new Message(3, "foo"),
            new Message(8, "bar"),
            new Message(10, "foo"),
            new Message(11, "foo")
        };

        var expected = new boolean[] {
            true, true, false, false, false, true
        };

        for (var i = 0; i < data.length; i++) {
            var message = data[i];
            logger.info("[{}]", message);
            Assert.assertEquals(shouldPrintMessage(message.ts(), message.message()), expected[i]);
        }
    }

    private record Message(int ts, String message) {
    }
}
