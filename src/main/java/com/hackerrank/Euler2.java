package com.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denis Gabaydulin
 * @since 28.11.16
 */
public class Euler2 {
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
                .map(Euler2::fibonacci)
                .collect(Collectors.toList());
    }

    private static long fibonacci(long n) {
        if (n == 0) {
            return 0;
        }

        long a = 0;
        long b = 1;
        long result = 0;

        long sum = 0;

        for (int i = 1; result <= n; i++) {
            result = a + b;
            a = b;
            b = result;

            if (result <= n && result % 2 == 0) {
                sum += result;
            }
        }

        return sum;
    }
}
