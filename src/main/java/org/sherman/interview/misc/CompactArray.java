package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 23/11/2016
 */
public class CompactArray {
    private static final Logger log = LoggerFactory.getLogger(CompactArray.class);

    private CompactArray() {
    }

    /**
     * @param data contains integer list [a,a,a,b,c,c,c,c,d,d,d,...], every symbol occur at least twice
     */
    public static int[] compactArray(@NotNull int[] data) {
        int currentElt = 0;
        int sequenceCounter = 0;
        int globalOffset = 0;

        log.info("{}", data);

        for (int i = 0; i < data.length; i++) {
            if (currentElt == 0) {
                currentElt = data[i];
            }

            if (data[i] != currentElt) {
                //log.info("{} {}", startElt, offset);
                if (sequenceCounter > 1) {
                    data[i - globalOffset - sequenceCounter] = data[i - 1];
                    data[i - globalOffset - sequenceCounter + 1] = sequenceCounter;
                }
                globalOffset += sequenceCounter - 2;
                sequenceCounter = 1;
                currentElt = data[i];
            } else {
                //log.info("{} {}", currentElt, sequenceCounter);
                currentElt = data[i];
                sequenceCounter++;
            }
        }

        log.info("{}", data);

        if (sequenceCounter > 1) {
            data[data.length - globalOffset - sequenceCounter] = currentElt;
            data[data.length - globalOffset - sequenceCounter + 1] = sequenceCounter;
        }

        for (int i = data.length - globalOffset - sequenceCounter + 2; i < data.length; i++) {
            data[i] = -1;
        }

        log.info("{}", data);

        return data;
    }

    @Test
    public void test() {
        assertEquals(CompactArray.compactArray(new int[]{1, 1, 1, 2, 2, 2, 3, 3}), new int[]{1, 3, 2, 3, 3, 2, -1, -1});
    }
}
