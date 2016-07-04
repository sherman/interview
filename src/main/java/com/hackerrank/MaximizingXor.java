package com.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/**
 * @author Denis Gabaydulin
 * @since 04/07/2016
 */
public class MaximizingXor {
    public static void main(String[] args) throws IOException {
        InputStreamReader inoutStream = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(inoutStream);

        int left = Integer.parseInt(buffer.readLine().trim());
        int right = Integer.parseInt(buffer.readLine().trim());

        buffer.close();

        System.out.print(getResult(left, right));
    }

    private static int getResult(int left, int right) {
        return IntStream.range(left, right + 1)
                .map(v -> IntStream.range(v, right + 1).map(i -> v ^ i).max().getAsInt())
                .max()
                .getAsInt();
    }
}
