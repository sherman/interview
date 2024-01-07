package com.leetcode.assorted_2024;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.*;

public class ThreeSum {
    private static final Logger logger = LoggerFactory.getLogger(ThreeSum.class);

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        var triplets = new HashSet<Elements>();

        for (var i = 0; i < nums.length; i++) {
            var r = nums.length - 1;
            var l = 0;
            var element = nums[i];
            while (l < r) {
                if (l == i) {
                    l++;
                    continue;
                }

                if (r == i) {
                    r--;
                    continue;
                }

                var sum = element + nums[l] + nums[r];
                //logger.info("[{}]", sum);
                if (sum == 0) {
                    logger.info("[{}] [{}] [{}]", element, nums[l], nums[r]);
                    var buffer = new int[]{element, nums[l], nums[r]};
                    Arrays.sort(buffer);
                    triplets.add(new Elements(buffer[0], buffer[1], buffer[2]));
                    l++;
                    r--;
                } else {
                    if (sum > 0) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
        }

        var result = new ArrayList<List<Integer>>();

        for (var element : triplets) {
            result.add(element.toList());
        }

        return result;
    }

    private static final class Elements {
        final int a;
        final int b;
        final int c;

        private Elements(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public List<Integer> toList() {
            return List.of(a, b, c);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Elements elements = (Elements) o;
            return a == elements.a && b == elements.b && c == elements.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
    }

    @Test
    public void test() {
        threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        /*threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        threeSum(new int[]{3, 0, -2, -1, 1, 2}); // -2 -1 0 1 2 3
        threeSum(new int[]{-2, -7, -11, -8, 9, -7, -8, -15, 10, 4, 3, 9, 8, 11, 1, 12, -6, -14, -2, -1, -7, -13, -11, -15, 11, -2, 7, -4, 12, 7, -3, -5, 7, -7, 3, 2, 1, 10, 2, -12, -1, 12, 12, -8, 9, -9, 11, 10, 14, -6, -6, -8, -3, -2, 14, -15, 3, -2, -4, 1, -9, 8, 11, 5, -14, -1, 14, -6, -14, 2, -2, -8, -9, -13, 0, 7, -7, -4, 2, -8, -2, 11, -9, 2, -13, -10, 2, 5, 4, 13, 13, 2, -1, 10, 14, -8, -14, 14, 2, 10});*/
    }
}
