package com.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

/**
 * @author Denis Gabaydulin
 * @since 03.07.16
 */
public class LonelyInteger {
    public static void main(String[] args) throws IOException {
        InputStreamReader inoutStream = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(inoutStream);

        List<String> numbers = readLines(buffer);

        buffer.close();

        System.out.print(getResult(numbers));
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

    private static int getResult(List<String> lines) {
        int[] numbers = stream(lines.get(0).split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = result ^ numbers[i];
        }

        return result;
    }
}
