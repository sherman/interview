package com.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Denis Gabaydulin
 * @since 30/06/2016
 */
public class BiggerIsGreater {
    public static void main(String[] args) throws IOException {
        InputStreamReader inoutStream = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(inoutStream);

        List<String> lines = readLines(buffer);

        buffer.close();

        getResult(lines, lines.size())
                .forEach(System.out::println);
    }

    private static List<String> readLines(BufferedReader bufferedReader) throws IOException {
        List<String> lines = new ArrayList<>();

        int linesCount = Integer.parseInt(bufferedReader.readLine());

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

        for (int i = 0; i < numberOfTests; i ++) {
            String original = lines.get(i);

            if (isAllCharsAreEquals(original)) {
                result.add("no answer");
            } else {
                result.add(original);
            }
        }

        Collections.sort(result);

        return result;
    }

    private static boolean isAllCharsAreEquals(String line) {
        return line.length() > 1 && line.chars().filter(chr -> chr == line.charAt(0)).count() == line.length();
    }
}
