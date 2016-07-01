package com.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * @author Denis Gabaydulin
 * @since 01/07/2016
 */
public class CountSorting {
    public static void main(String[] args) throws IOException {
        InputStreamReader inoutStream = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(inoutStream);

        List<String> lines = readLines(buffer);

        buffer.close();

        StringJoiner joiner = new StringJoiner(" ");

        getResult(lines)
                .forEach(frequency -> joiner.add(String.valueOf(frequency)));

        System.out.print(joiner);
    }

    public static List<String> readLines(BufferedReader bufferedReader) throws IOException {
        List<String> lines = new ArrayList<>();

        // skip first
        bufferedReader.readLine();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);

            return lines;
        }

        return lines;
    }

    private static List<Integer> getResult(List<String> lines) {
        int[] numbers = stream(lines.get(0).split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Map<Integer, Integer> frequency = new HashMap<>();

        stream(numbers).forEach(
                value -> {
                    Integer counter = frequency.get(value);
                    if (counter == null) {
                        counter = 1;
                    } else {
                        ++counter;
                    }

                    frequency.put(value, counter);
                }
        );

        return IntStream.range(0, 100)
                .map(index -> ofNullable(frequency.get(index)).orElse(0))
                .boxed()
                .collect(toList());
    }
}
