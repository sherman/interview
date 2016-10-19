package org.sherman.interview.lists;

import com.google.common.collect.Iterables;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;

/**
 * @author Denis Gabaydulin
 * @since 19.10.16
 */
public class DoubleLinkedListTest {
    @Test
    public void emptyIterator() {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        assertEquals(Iterables.size(linkedList), 0);
    }

    @Test
    public void addLast() {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        assertEqualsNoOrder(Iterables.toArray(linkedList, Integer.class), new Integer[]{1, 2, 3});
        assertEquals((int) linkedList.getFirst(), 1);
        assertEquals((int) linkedList.getLast(), 3);
    }

    @Test
    public void removeLast() {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        assertEquals((int) linkedList.removeLast(), 3);
        assertEqualsNoOrder(Iterables.toArray(linkedList, Integer.class), new Integer[]{1, 2});
        assertEquals((int) linkedList.getFirst(), 1);
        assertEquals((int) linkedList.getLast(), 2);
    }

    @Test
    public void removeFirst() {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        assertEquals((int)linkedList.removeFirst(), 1);
        assertEqualsNoOrder(Iterables.toArray(linkedList, Integer.class), new Integer[]{2, 3});
        assertEquals((int) linkedList.getFirst(), 2);
        assertEquals((int) linkedList.getLast(), 3);
    }
}
