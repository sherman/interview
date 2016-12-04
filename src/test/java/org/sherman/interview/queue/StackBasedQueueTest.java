package org.sherman.interview.queue;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 04.12.16
 */
public class StackBasedQueueTest {
    @Test
    public void pop() {
        StackBasedQueue<Integer> queue = new StackBasedQueue<>();
        queue.push(1);
        assertEquals(queue.pop(), Integer.valueOf(1));
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertEquals(queue.pop(), Integer.valueOf(1));
        assertEquals(queue.pop(), Integer.valueOf(2));
        assertEquals(queue.pop(), Integer.valueOf(3));
    }
}
