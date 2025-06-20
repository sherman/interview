package org.sherman.interview.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class RandomPicker<T> {
    private static final Logger logger = LoggerFactory.getLogger(RandomPicker.class);

    private final List<T> modified;
    private int index = 0;

    public RandomPicker(List<T> items) {
        modified = new ArrayList<>(items);
        Collections.shuffle(modified);
    }

    public T nextRandom() {
        if (index >= modified.size()) {
            return null;
        }

        return modified.get(index++);
    }

    @Test
    public static void test() {
        var randomPicker = new RandomPicker<>(List.of(1, 2, 3, 4, 5));

        while (true) {
            var item = randomPicker.nextRandom();
            logger.info("{}", item);

            if (item == null) {
                break;
            }
        }
    }
}
