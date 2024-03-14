package org.sherman.interview.java;

public class JaggedArraysApp {
    public static void main(String[] args) {
        var numberOfDocs = 100;
        var longValues = new long[numberOfDocs][];
        longValues[0] = new long[] {1, 2, 3};
        longValues[1] = new long[] {5, 6};
        for (var i = 0; i < 2; i++) {
            for (var k = 0; k < longValues[i].length; k++) {
                System.out.println(longValues[i][k]);
            }
        }
        longValues[1] = new long[] {42};
        for (var i = 0; i < 2; i++) {
            for (var k = 0; k < longValues[i].length; k++) {
                System.out.println(longValues[i][k]);
            }
        }
    }
}
