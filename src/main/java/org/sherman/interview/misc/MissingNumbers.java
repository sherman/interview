package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

/**
 * @author Denis Gabaydulin
 * @since 06/12/2016
 */
public class MissingNumbers {
    private static final Logger log = LoggerFactory.getLogger(MissingNumbers.class);

    private MissingNumbers() {
    }

    public static int findMissingPositiveNumber(@NotNull int[] numbers) {
        if (numbers.length == 0) {
            return 1;
        }

        // shift all non-positive elements to the left
        int negative = 0;
        int positive = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] <= 0) {
                for (int j = negative; j < i; j++) {
                    if (numbers[j] > 0) {
                        int temp = numbers[j];
                        numbers[j] = numbers[i];
                        numbers[i] = temp;
                        negative++;
                        break;
                    }
                }
            } else {
                positive++;
            }
        }

        negative = numbers.length - positive;

        log.info("{}", numbers);

        for (int i = 0; i < numbers.length; i++) {
            int index = numbers[i];

            if (index <= 0 || index >= numbers.length - negative) {
                continue;
            }

            // swap elements if i-th elt doesn't match to it position
            while (numbers[i] != i - negative + 1) {
                if (index + negative - 1 < numbers.length && numbers[index + negative - 1] != index) {
                    int temp = numbers[index + negative - 1];
                    numbers[index + negative - 1] = index;
                    numbers[i] = temp;
                    index = numbers[i];
                } else {
                    break;
                }
            }
        }

        log.info("{} {}", numbers, negative);

        for (int i = negative; i < numbers.length; i++) {
            int expected = i - negative + 1;
            if (numbers[i] > 0) {
                if (numbers[i] != expected) {
                    return expected;
                }
            }
        }

        return numbers.length - negative + 1;
    }

    /**
     * @param numbers is an array of consecutive positive integers, starting from one
     */
    public static int findRemoved(@NotNull int[] numbers) {
        checkArgument(stream(numbers).allMatch(v -> v > 0), "Only positive numbers accepted!");

        int sum = range(1, numbers.length + 2).sum();

        for (int v : numbers) {
            sum -= v;
        }

        return sum;
    }
}
