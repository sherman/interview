package com.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denis Gabaydulin
 * @since 02.07.16
 */
public class Euler1 {
    public static void main(String[] args) throws IOException {
        InputStreamReader inoutStream = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(inoutStream);

        List<Long> numbers = readLines(buffer);

        buffer.close();

        getResult(numbers)
                .forEach(System.out::println);
    }

    public static List<Long> readLines(BufferedReader bufferedReader) throws IOException {
        List<Long> lines = new ArrayList<>();

        int numberOfTests = Integer.parseInt(bufferedReader.readLine());

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(Long.parseLong(line));
            --numberOfTests;

            if (numberOfTests == 0) {
                return lines;
            }
        }

        return lines;
    }

    private static List<Long> getResult(List<Long> numbers) {
        return numbers.stream()
                .map(number -> sumOfDivisible(number, 3) + sumOfDivisible(number, 5) - sumOfDivisible(number, 15))
                .collect(Collectors.toList());
    }

    private static long sumOfDivisible(long number, int divisor) {
        return divisor * ((number - 1) / divisor) * (((number - 1) / divisor) + 1) / 2;
    }
}
