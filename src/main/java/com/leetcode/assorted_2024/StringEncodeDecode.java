package com.leetcode.assorted_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringEncodeDecode {
    private static final String DELIMITER = "\n";

    public String encode(List<String> strs) {
        if (strs.isEmpty()) {
            return null;
        }
        return String.join(DELIMITER, strs);
    }

    public List<String> decode(String str) {
        if (str == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(str.split(DELIMITER)).toList();
    }
}
