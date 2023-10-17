package org.sherman.interview.java;

import com.google.common.primitives.Longs;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.InPlaceMergeSorter;
import org.apache.lucene.util.IntsRefBuilder;
import org.apache.lucene.util.fst.*;
import org.openjdk.jol.info.GraphLayout;
import org.sherman.benchmark.java.MinimalPerfectHashFuncBenchmark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Comparator;
import java.util.Random;

public class FstApp {
    private static final Logger logger = LoggerFactory.getLogger(MinimalPerfectHashFuncBenchmark.class);
    private static final int SIZE = 1 << 20;

    public static void main(String[] args) throws IOException {
        var outputs = PositiveIntOutputs.getSingleton();
        var scratchIntsRef = new IntsRefBuilder();
        var ids = new int[SIZE];
        var termArray = new BytesRef[SIZE];
        var random = new Random();

        for (int i = 0; i < SIZE; i++) {
            ids[i] = i;
            termArray[i] = new BytesRef(Longs.toByteArray(random.nextLong()));
        }

        var comparator = new Comparator<BytesRef>() {
            @Override
            public int compare(BytesRef o1, BytesRef o2) {
                return o1.compareTo(o2);
            }
        };

        new InPlaceMergeSorter() {
            private BytesRef scratch1 = new BytesRef();
            private BytesRef scratch2 = new BytesRef();

            @Override
            protected void swap(int i, int j) {
                final int o = ids[i];
                ids[i] = ids[j];
                ids[j] = o;
            }

            @Override
            protected int compare(int i, int j) {
                var ord1 = ids[i];
                var ord2 = ids[j];
                scratch1 = termArray[ord1];
                scratch2 = termArray[ord2];

                return comparator.compare(scratch1, scratch2);
            }

        }.sort(0, ids.length);

        var baseLine = new Object2LongOpenHashMap<BytesRef>(SIZE);
        var compiler = new FSTCompiler<>(FST.INPUT_TYPE.BYTE1, outputs);
        for (var termId = 0; termId < SIZE; termId++) {
            var term = termArray[ids[termId]];
            var ref = Util.toIntsRef(term, scratchIntsRef);
            compiler.add(ref, (long) (termId + 1));
            baseLine.addTo(term, (termId + 1));
        }
        var fst = compiler.compile();
        var termsFst = new BytesRefFSTEnum<>(fst);

        logger.info("Terms size: [{}]", GraphLayout.parseInstance(termsFst).totalSize());
        logger.info("Terms size (app): [{}]", fst.ramBytesUsed());

        logger.info("Hashmap size: [{}]", GraphLayout.parseInstance(baseLine).totalSize());
        logger.info("Terms array size: [{}]", GraphLayout.parseInstance(termArray).totalSize());
    }
}
