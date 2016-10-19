package org.sherman.interview.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Denis Gabaydulin
 * @since 19.10.16
 */
public class DoubleLinkedList implements Iterable<Integer> {
    private Item head;
    private Item tail;
    private int size;

    public int size() {
        return size();
    }

    public Integer getFirst() {
        if (head != null) {
            return head.elt;
        } else {
            return null;
        }
    }

    public Integer getLast() {
        if (tail != null) {
            return tail.elt;
        } else {
            return null;
        }
    }

    public void addFirst(int elt) {
        // TODO: implement me!
    }

    public void addLast(int elt) {
        Item next = new Item();
        next.elt = elt;

        if (head == null) {
            next.next = null;
            next.prev = null;
            head = next;
        } else {
            Item current = head;
            while (current.next != null) {
                current = current.next;
            }

            current.next = next;
            next.prev = current;
            tail = next;
        }

        if (tail == null) {
            tail = head;
        }

        size++;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private Item current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    int elt = current.elt;
                    current = current.next;
                    return elt;
                }

                throw new NoSuchElementException();
            }
        };
    }

    public Integer removeLast() {
        if (tail != null) {
            int elt = tail.elt;
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
            size--;
            return elt;
        } else {
            return null;
        }
    }

    public Integer removeFirst() {
        if (head != null) {
            int elt = head.elt;
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            size--;
            return elt;
        } else {
            return null;
        }
    }

    private static class Item {
        private Item next;
        private Item prev;
        private int elt;
    }
}
