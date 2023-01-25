package io.algoexpert;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateSubsequence {
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        if (sequence.size() > array.size()) {
            return false;
        }

        var sequencePointer = 0;
        for (var element : array) {
            if (sequencePointer < sequence.size() && element == sequence.get(sequencePointer)) {
                sequencePointer++;
            }
        }

        return sequence.size() == sequencePointer;
    }

    @Test
    public void test() {
        Assert.assertTrue(isValidSubsequence(List.of(5, 1, 22, 25, 6, -1, 8, 10), List.of(1, 6, -1, 10)));
    }
}
