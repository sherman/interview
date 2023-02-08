package org.sherman.interview.top;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.sherman.interview.nth_element.AscendingNthElement;
import org.sherman.interview.nth_element.DescendingNthElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class Top {
    private static final Logger logger = LoggerFactory.getLogger(Top.class);

    @Test
    public void addWithoutPartition() {
        var priorityQueue = new PriorityQueue<>(Comparator.comparing((Function<Integer, Integer>) integer -> integer).reversed());
        var top = new TopKeeper(new ListWithMinimum(10));

        var data = new int[10];
        for (var i = 0; i < 10; i++) {
            data[i] = i;
        }

        var list = Arrays.stream(data).boxed().collect(Collectors.toList());
        Collections.shuffle(list);

        for (var k : list) {
            priorityQueue.add(k);
            top.add(k);
        }

        var items1 = new ArrayList<>();
        var items2 = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            items1.add(priorityQueue.poll());
            items2.add(top.pop());
        }
        logger.info("[{}]", items1);
        logger.info("[{}]", items2);
    }

    @Test
    public void addWithPartition1() {
        var data = new int[25];
        for (var i = 0; i < data.length; i++) {
            data[i] = i;
        }

        var list = Arrays.stream(data).boxed().collect(Collectors.toList());
        Collections.shuffle(list);

        for (var i = 0; i < 1000; i++) {
            var priorityQueue = new PriorityQueue<>(Comparator.comparing((Function<Integer, Integer>) integer -> integer).reversed());
            for (var k : list) {
                priorityQueue.add(k);
            }

            var items1 = new ArrayList<>();
            while (!priorityQueue.isEmpty()) {
                items1.add(priorityQueue.poll());
            }
        }

        List<Integer> res = new ArrayList<>();
        var b = System.currentTimeMillis();
        for (var i = 0; i < 1000; i++) {
            var priorityQueue = new PriorityQueue<>(Comparator.comparing((Function<Integer, Integer>) integer -> integer).reversed());
            for (var k : list) {
                priorityQueue.add(k);
            }

            var items1 = new ArrayList<Integer>();
            while (!priorityQueue.isEmpty()) {
                items1.add(priorityQueue.poll());
                if (items1.size() == 10) {
                    break;
                }
            }
            res = items1;
        }
        logger.info("R: [{}]", res);
        logger.info("T: [{}]", System.currentTimeMillis() - b);


        //logger.info("[{}]", items1);
        //logger.info("[{}]", items2);
    }

    @Test
    public void addWithPartition2() {
        var data = new int[25];
        for (var i = 0; i < data.length; i++) {
            data[i] = i;
        }

        var list = Arrays.stream(data).boxed().collect(Collectors.toList());
        Collections.shuffle(list);

        for (var i = 0; i < 1000; i++) {
            var top = new TopKeeper(new ListWithMinimum(10));
            for (var k : list) {
                top.add(k);
            }

            var items1 = new ArrayList<>();
            while (!top.isEmpty()) {
                items1.add(top.pop());
            }
        }

        List<Integer> res = new ArrayList<>();
        var b = System.currentTimeMillis();
        for (var i = 0; i < 1000; i++) {
            var top = new TopKeeper(new ListWithMinimum(10));
            for (var k : list) {
                top.add(k);
            }

            var items1 = new ArrayList<Integer>();
            while (!top.isEmpty()) {
                items1.add(top.pop());
            }
            res = items1;
        }

        logger.info("R: [{}]", res);
        logger.info("T: [{}]", System.currentTimeMillis() - b);


        //logger.info("[{}]", items1);
        //logger.info("[{}]", items2);
    }

    private static class TopKeeper {
        private final ListWithMinimum list;
        private boolean finalize;

        private TopKeeper(ListWithMinimum list) {
            this.list = list;
        }

        public void complete() {
            if (finalize) {
                return;
            }

            list.partition();
            Collections.sort(list.items);
            finalize = true;
        }

        public int pop() {
            complete();
            return list.pop();
        }

        public boolean add(int value) {
            Preconditions.checkArgument(!finalize, "Can't add top finalized top");
            return list.add(value);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
    }

    private static class ListWithMinimum {
        private final int size;
        private int minIndex;
        private List<Integer> items;

        private ListWithMinimum(int size) {
            this.size = size;
            this.items = new ArrayList<>(this.size * 2);

        }

        public boolean add(int value) {
            if (items.size() < size) {
                if (items.isEmpty() || items.get(minIndex) > value) {
                    minIndex = items.size();
                    items.add(value);
                    return true;
                }
            } else if (value <= items.get(minIndex)) {
                return false;
            }

            items.add(value);

            if (items.size() == size << 1) {
                partition();
            }

            return true;
        }

        public int pop() {
            if (items.size() > 0) {
                return items.remove(items.size() - 1);
            } else {
                return 0;
            }
        }

        private void partition() {
            DescendingNthElement.nthElement(items, 0, items.size(), this.size - 1);
            var length = items.size();
            for (var i = size; i < length; i++) {
                items.remove(size);
            }
            minIndex = size - 1;
        }

        public boolean isEmpty() {
            return items.isEmpty();
        }
    }
}
