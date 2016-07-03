package com.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denis Gabaydulin
 * @since 03.07.16
 */
public class FlippingBits {
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

        int numberOfTests = Integer.parseInt(bufferedReader.readLine().trim());

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(Long.parseLong(line.trim()));
            --numberOfTests;

            if (numberOfTests == 0) {
                return lines;
            }
        }

        return lines;
    }

    private static List<Long> getResult(List<Long> numbers) {
        return numbers.stream()
                // for unsigned int we use mask 0xFFFFFFFFL (max unsigned int value)
                .map(number -> ~number & 0xFFFFFFFFL)
                .collect(Collectors.toList());
    }
}
