package org.sherman.interview.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 23/02/2016
 */
public class SequenceTest {
    private static final Logger log = LoggerFactory.getLogger(SequenceTest.class);

    @Test
    public void getSums() {
        int[] values = new int[]{1, 2, 3, 5, 7, 9};

        List<Integer> input = Arrays.stream(new int[]{0, 1, 2, 3, 4, 5}).boxed().collect(Collectors.toList());
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        generate(input, output, combinations, values.length);

        Set<Set<Integer>> found = new HashSet<>();

        combinations.stream()
                .map(HashSet::new)
                .filter(combination -> !found.contains(combination))
                .filter(combination -> combination.stream().reduce(0, (a, b) -> a + values[b]) == 10)
                .forEach(combination -> {
                    log.info("{}", combination);
                    found.add(combination);
                });
    }

    private static void generate(List<Integer> input, List<Integer> output, List<List<Integer>> combinations, int depth) {
        if (depth == 0) {
            combinations.add(new ArrayList<>(output));
        } else {
            for (int i = 0; i < input.size(); i++) {
                output.add(input.get(i));
                generate(input, output, combinations, depth - 1);
                output.remove(output.size() - 1);
            }
        }
    }
}
