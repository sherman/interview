package org.sherman.interview.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class RandomPickerV2<T> {
    private static final Logger logger = LoggerFactory.getLogger(RandomPickerV2.class);

    private final Random random = new Random();
    private final List<T> items;
    private int size;

    public RandomPickerV2(List<T> items) {
        this.items = new ArrayList<>(items);
        size = items.size();
    }

    public T nextRandom() {
        if (size == 0) {
            return null;
        }

        int i = random.nextInt(size);
        T result = items.get(i);
        size--;
        items.set(i, items.get(size));
        return result;
    }

    @Test
    public static void test() {
        var randomPicker = new RandomPickerV2<>(List.of(1, 2, 3, 4, 5));

        while (true) {
            var item = randomPicker.nextRandom();
            logger.info("{}", item);

            if (item == null) {
                break;
            }
        }
    }
}
