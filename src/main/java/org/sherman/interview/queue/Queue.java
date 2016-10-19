package org.sherman.interview.queue;

import org.sherman.interview.lists.DoubleLinkedList;

/**
 * @author Denis Gabaydulin
 * @since 19.10.16
 */
public class Queue {
    private DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

    public void push(int elt) {
        doubleLinkedList.addLast(elt);
    }

    public Integer pop() {
        return doubleLinkedList.removeFirst();
    }
}
