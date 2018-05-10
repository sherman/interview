package com.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis M. Gabaydulin
 * @since 10.05.18
 */
public class SearchMidRange {
    private static final Logger log = LoggerFactory.getLogger(SearchMidRange.class);

    public static int[] searchRange(int[] nums, int target) {
        return search(0, nums.length - 1, nums, target);
    }

    private static int[] search(int left, int right, int[] nums, int target) {
        log.info("{} {}", left, right);

        if (left <= right) {
            int mid = (right + left) / 2;

            int midValue = nums[mid];

            log.info("{}", midValue);

            if (midValue > target) {
                return search(left, mid - 1, nums, target);
            } else if (midValue < target) {
                return search(mid + 1, right, nums, target);
            } else {
                int l = mid;
                for (int i = mid; i >= 0 && nums[i] == target; i--) {
                    l = i;
                }

                int r = mid;
                for (int i = mid; i < nums.length && nums[i] == target; i++) {
                    r = i;
                }

                return new int[]{l, r};
            }
        } else {
            return new int[]{-1, -1};
        }
    }
}
