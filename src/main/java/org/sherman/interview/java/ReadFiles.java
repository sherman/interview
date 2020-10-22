package org.sherman.interview.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadFiles {
    private static final Logger logger = LoggerFactory.getLogger(ReadFiles.class);

    public static void main(String[] args) throws IOException {
        File file = new File("/home/dgabaydulin//file");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        char[] buffer = new char[8192];
        int read = 0;
        int offset = 0;
        StringBuilder b = new StringBuilder();
        while ((read = reader.read(buffer)) != -1) {
            b.append(buffer, offset, read);
            offset += read;

        }
        String result = b.toString();
        logger.info("{}", result);
    }
}
