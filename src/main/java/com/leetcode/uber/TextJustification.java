package com.leetcode.uber;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This is an
 * example,text
 * justification.
 */

public class TextJustification {
    private static final Logger logger = LoggerFactory.getLogger(TextJustification.class);

    public List<String> fullJustify(String[] words, int maxWidth) {
        var result = new ArrayList<String>();
        var currentLine = new ArrayList<String>();
        var chars = 0;
        var spaces = 0;
        for (var word : words) {
            if (currentLine.isEmpty()) {
                currentLine.add(word);
                chars += word.length();
            } else {
                var newLen = chars + spaces + 1 + word.length();
                if (newLen > maxWidth) {
                    logger.info("{}: {}", currentLine, chars);
                    result.add(justify(currentLine, maxWidth, false));
                    currentLine.clear();
                    chars = 0;
                    spaces = 0;

                    currentLine.add(word);
                    chars += word.length();
                } else {
                    currentLine.add(word);
                    chars += word.length();
                    spaces += 1;
                }
            }
        }

        if (!currentLine.isEmpty()) {
            result.add(justify(currentLine, maxWidth, true));
        }

        logger.info("{}", result);
        return result;
    }

    public static String justify(List<String> words, int maxWidth, boolean lastLine) {
        var result = new StringBuilder();
        if (lastLine || words.size() == 1) {
            for (var i = 0; i < words.size(); i++) {
                result.append(words.get(i));

                if ((result.length() + 1) < maxWidth) {
                    result.append(" ");
                } else {
                    break;
                }
            }
            // add extra spaces
            while (result.length() < maxWidth) {
                result.append(" ");
            }
        } else {
            var sum = words.stream().map(String::length).reduce(0, Integer::sum);
            var spaces = new int[words.size() - 1];
            var spaceLength = maxWidth - sum;
            while (spaceLength > 0) {
                for (var i = 0; i < spaces.length; i++) {
                    if (spaceLength > 0) {
                        spaces[i]++;
                        spaceLength--;
                    } else {
                        break;
                    }
                }
            }

            for (var i = 0; i < words.size(); i++) {
                result.append(words.get(i));

                if (i < words.size() - 1) {
                    for (var j = 0; j < spaces[i]; j++) {
                        result.append(" ");
                    }
                }
            }
        }
        return result.toString();
    }

    @Test
    public void justifyTest() {
        Assert.assertEquals(justify(List.of("ab", "cd", "ef"), 9, false), "ab  cd ef");
        Assert.assertEquals(justify(List.of("ab", "cd", "ef"), 9, true), "ab cd ef ");
        Assert.assertEquals(justify(List.of("ab", "cd"), 7, false), "ab   cd");
        Assert.assertEquals(justify(List.of("ab", "cd", "ef"), 8, false), "ab cd ef");
        Assert.assertEquals(justify(List.of("ab"), 3, false), "ab ");
        Assert.assertEquals(justify(List.of("ab"), 3, true), "ab ");
        Assert.assertEquals(justify(List.of("ab"), 4, false), "ab  ");
        Assert.assertEquals(justify(List.of("ab"), 4, true), "ab  ");
        Assert.assertEquals(justify(List.of("ab", "cd"), 5, false), "ab cd");
        Assert.assertEquals(justify(List.of("ab", "cd"), 5, true), "ab cd");
    }

    @Test
    public void test() {
        var res = fullJustify(
            new String[] {
                "Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art",
                "is", "everything", "else", "we", "do"
            },
            20
        );

        Assert.assertEquals(
            res,
            List.of(
                "Science  is  what we",
                "understand      well",
                "enough to explain to",
                "a  computer.  Art is",
                "everything  else  we",
                "do                  "
            )
        );
    }
}
