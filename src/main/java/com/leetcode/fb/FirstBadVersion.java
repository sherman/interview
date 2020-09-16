package com.leetcode.fb;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstBadVersion {
    private final int badVersion;

    public FirstBadVersion(int badVersion) {
        this.badVersion = badVersion;
    }

    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (isBadVersion(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private boolean isBadVersion(int version) {
        return badVersion == version;
    }

    @Test
    public static void test() {
        FirstBadVersion t1 = new FirstBadVersion(3);
        Assert.assertEquals(t1.firstBadVersion(4), 3);

        FirstBadVersion t3 = new FirstBadVersion(4);
        Assert.assertEquals(t3.firstBadVersion(5), 4);
    }
}
