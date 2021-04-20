package com.hackerrank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result1 {

    /*
     * Complete the 'solve' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY heights
     *  2. 2D_INTEGER_ARRAY growthByYear
     */
    //private static final Logger logger = LoggerFactory.getLogger(Result1.class);


    public static List<Integer> solve(List<Integer> heights, List<List<Integer>> growthByYear) {
        for (List<Integer> pair : growthByYear) {
            //logger.info("[{}]", heights);

            int mountains = pair.get(0);
            int size = pair.get(1);

            for (int i = heights.size() - 1; i >= 0; i--) {
                if (mountains >= 0) {
                    heights.set(i, heights.get(i) + size);
                }
                mountains--;
            }
        }

        return heights;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> length = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = Result1.solve(length, queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining(" "))
                + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

