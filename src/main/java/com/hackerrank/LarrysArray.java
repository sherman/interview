package com.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Denis Gabaydulin
 * @since 29/06/2016
 */
public class LarrysArray {
    private static final String YES = "YES";
    private static final String NO = "NO";

    public static void main(String[] args) throws IOException {
        InputStreamReader inoutStream = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(inoutStream);

        List<String> lines = readLines(buffer);

        buffer.close();

        getResult(lines, lines.size() / 2)
                .forEach(System.out::println);
    }

    public static List<String> readLines(BufferedReader bufferedReader) throws IOException {
        List<String> lines = new ArrayList<>();

        int numberOfTests = Integer.parseInt(bufferedReader.readLine());
        int linesCount = numberOfTests * 2;

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
            --linesCount;

            if (linesCount == 0) {
                return lines;
            }
        }

        return lines;
    }

    private static List<String> getResult(List<String> lines, int numberOfTests) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < numberOfTests * 2; i += 2) {
            int[] numbers = Arrays.stream(lines.get(i + 1).split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            boolean resultFound = false;

            for (int j = 0; j < numbers.length; j++) {
                for (int k = 0; k < numbers.length - 2; k++) {
                    if (numbers[k] > numbers[k + 2] || numbers[k] > numbers[k + 1]) {
                        int temp = numbers[k + 2];
                        numbers[k + 2] = numbers[k];
                        numbers[k] = numbers[k + 1];
                        numbers[k + 1] = temp;

                        if (numbers[k] > numbers[k + 2] || numbers[k] > numbers[k + 1]) {
                            temp = numbers[k + 2];
                            numbers[k + 2] = numbers[k];
                            numbers[k] = numbers[k + 1];
                            numbers[k + 1] = temp;
                        }
                    }

                    Arrays.stream(numbers).forEach(n -> System.out.print(n + " "));
                    System.out.print("\n");
                }
            }

            for (int j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    result.add(NO);
                    resultFound = true;
                    break;
                }
            }

            if (!resultFound) {
                result.add(YES);
            }
        }

        return result;
    }
}
