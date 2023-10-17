package org.sherman.benchmark.java;

import com.google.common.primitives.Longs;
import it.unimi.dsi.bits.TransformationStrategies;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import it.unimi.dsi.sux4j.mph.GOVMinimalPerfectHashFunction;
import it.unimi.dsi.sux4j.mph.LcpMonotoneMinimalPerfectHashFunction;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.InPlaceMergeSorter;
import org.apache.lucene.util.IntsRefBuilder;
import org.apache.lucene.util.fst.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.ArrayUtils.shuffle;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class MinimalPerfectHashFuncBenchmark {
    private static final int SIZE = 1 << 20;
    private final PositiveIntOutputs outputs = PositiveIntOutputs.getSingleton();
    private final IntsRefBuilder scratchIntsRef = new IntsRefBuilder();
    private final int[] ids = new int[SIZE];
    private final BytesRef[] termArray = new BytesRef[SIZE];
    private BytesRefFSTEnum<Long> termsFst;
    private final Object2LongOpenHashMap<BytesRef> baseLine = new Object2LongOpenHashMap<>(SIZE);
    private final Random random = new Random();

    private LcpMonotoneMinimalPerfectHashFunction<byte[]> lcpMonotoneMPHFunc;
    private GOVMinimalPerfectHashFunction<byte[]> gov4MPHFunc;
    private GOVMinimalPerfectHashFunction<byte[]> gov4MPHFuncWithSignatures;

    @Setup(Level.Trial)
    public void generate() throws IOException {
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

        var compiler = new FSTCompiler<>(FST.INPUT_TYPE.BYTE1, outputs);
        var keys = new ArrayList<byte[]>();
        for (var termId = 0; termId < SIZE; termId++) {
            var term = termArray[ids[termId]];
            var ref = Util.toIntsRef(term, scratchIntsRef);
            compiler.add(ref, (long) (termId + 1));
            baseLine.addTo(term, (termId + 1));
            keys.add(term.bytes);
        }
        var fst = compiler.compile();
        termsFst = new BytesRefFSTEnum<>(fst);

        lcpMonotoneMPHFunc = new LcpMonotoneMinimalPerfectHashFunction.Builder<byte[]>()
            .keys(keys)
            .numKeys(keys.size())
            .transform(TransformationStrategies.byteArray())
            .build();

        gov4MPHFunc = new GOVMinimalPerfectHashFunction.Builder<byte[]>()
            .keys(keys)
            .transform(TransformationStrategies.byteArray())
            .build();

        gov4MPHFuncWithSignatures = new GOVMinimalPerfectHashFunction.Builder<byte[]>()
            .keys(keys)
            .transform(TransformationStrategies.byteArray())
            .signed(64)
            .build();

        shuffle(termArray);
    }

    @Benchmark
    public void fstGet(Blackhole blackhole) throws IOException {
        var index = random.nextInt(termArray.length);
        var term = termArray[index];
        blackhole.consume(termsFst.seekExact(term));
    }

    @Benchmark
    public void openHashMapGet(Blackhole blackhole) {
        var index = random.nextInt(termArray.length);
        var term = termArray[index];
        blackhole.consume(baseLine.getLong(term));
    }

    @Benchmark
    public void lcpMonotoneMPHFunc(Blackhole blackhole) {
        var index = random.nextInt(termArray.length);
        var term = termArray[index];
        blackhole.consume(lcpMonotoneMPHFunc.getLong(term.bytes));
    }

    @Benchmark
    public void gov4MPHFunc(Blackhole blackhole) {
        var index = random.nextInt(termArray.length);
        var term = termArray[index];
        blackhole.consume(gov4MPHFunc.getLong(term.bytes));
    }

    @Benchmark
    public void gGov4MPHFuncWithSignatures(Blackhole blackhole) {
        var index = random.nextInt(termArray.length);
        var term = termArray[index];
        blackhole.consume(gov4MPHFuncWithSignatures.getLong(term.bytes));
    }
}
