package com.leetcode.assorted_2023;

public class DefangingAnIPAddress {
    public String defangIPaddr(String address) {
        var result = new StringBuilder();

        for (var i = 0; i < address.length(); i++) {
            var c = address.charAt(i);
            if (c == '.') {
                result.append('[');
                result.append(c);
                result.append(']');
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
