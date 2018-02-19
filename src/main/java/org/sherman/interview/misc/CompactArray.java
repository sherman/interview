package org.sherman.interview.misc;

import com.google.common.primitives.Ints;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @NotNull
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

    /**
     * @param data contains integer list [a,a,a,b,c,c,c,c,d,d,d,...], every symbol occur at least twice
     */
    @NotNull
    public static int[] compactArrayV2(@NotNull int[] data) {
        // mark all duplicates as -1
        int currentElt = 0;
        for (int i = 0; i < data.length; i++) {
            if (currentElt == 0 || currentElt != data[i]) {
                currentElt = data[i];
            } else {
                data[i] = -1;
            }
        }

        log.info("{}", data);

        // compact array
        int offset = 0;
        int lastPosition = 0;
        int sequenceCounter = 0;
        currentElt = 0;
        int prevElt = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == -1) {
                sequenceCounter++;
            } else {
                prevElt = currentElt;
                currentElt = data[i];

                if (prevElt != 0) {
                    log.info("{} {}", prevElt, sequenceCounter);
                    data[2 * (offset - 1)] = prevElt;
                    data[2 * (offset - 1) + 1] = sequenceCounter + 1;
                }
                sequenceCounter = 0;
                offset += 1;
            }
        }

        data[2 * (offset - 1)] = currentElt;
        data[2 * (offset - 1) + 1] = sequenceCounter + 1;

        return Arrays.copyOf(data, 2 * (offset));
    }

    @NotNull
    public static int[] runLengthEncoding(@NotNull int[] data) {
        List<Integer> result = new ArrayList<>();

        int sequenceCounter = 1;
        for (int i = 1; i < data.length; i++) {
            if (data[i] != data[i - 1]) {
                result.add(data[i - 1]);
                result.add(sequenceCounter);
                sequenceCounter = 1;
            } else {
                sequenceCounter++;
            }
        }

        result.add(data[data.length - 1]);
        result.add(sequenceCounter);

        return Ints.toArray(result);
    }

    /**
     * @param data is sorted
     * @return
     */
    @NotNull
    public static int[] compactArrayV3(@NotNull int[] data) {
        if (data.length == 0) {
            return data;
        }

        int current = data[0];
        int count = 1;
        for (int i = 1; i < data.length; i++) {
            if (current != data[i]) {
                current = data[i];
                data[count++] = current;
            }
        }

        int[] newElts = new int[count];
        System.arraycopy(data, 0, newElts, 0, count);
        return newElts;
    }
}
