package org.sherman.interview.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class RandomPickerV2<T> {
    private static final Logger logger = LoggerFactory.getLogger(RandomPickerV2.class);

    private final Random random = new Random();
    private final List<T> modified;

    public RandomPickerV2(List<T> items) {
        modified = new ArrayList<>(items);
        Collections.shuffle(modified);
    }

    public T nextRandom() {
        if (modified.isEmpty()) {
            return null;
        }

        var index = random.nextInt(0, modified.size());
        var element = modified.get(index);
        modified.remove(index);

        return element;
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
