package com.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Denis Gabaydulin
 * @since 29/06/2016
 */
public class SimpleArraySum {
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Stream<String> stream = in.lines().limit(2);
        stream.skip(1).map(
                line -> Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .sum()
        )
                .findFirst()
                .ifPresent(System.out::println);

    }
}
