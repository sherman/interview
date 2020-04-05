package org.sherman.benchmark.java;

import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordsApp {
    private static final Logger logger = LoggerFactory.getLogger(RecordsApp.class);

    public static void main(String[] args) {
        Key key = new Key(10L);

        logger.info("[{}]", key);
    }

    private static record Key(long id) {
    }
}
