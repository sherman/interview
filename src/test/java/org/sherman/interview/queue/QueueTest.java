package org.sherman.interview.queue;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 19.10.16
 */
public class QueueTest {
    @Test
    public void push() {
        Queue queue = new Queue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertEquals((int)queue.pop(), 1);
        assertEquals((int)queue.pop(), 2);
        assertEquals((int)queue.pop(), 3);
        queue.push(4);
        assertEquals((int)queue.pop(), 4);
    }
}
