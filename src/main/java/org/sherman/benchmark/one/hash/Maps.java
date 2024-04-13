package org.sherman.benchmark.one.hash;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import one.nio.mem.LongHashSet;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class Maps {
    private static final Logger log = LoggerFactory.getLogger(Maps.class);

    private LongHashSet oneHashSet;
    private ConcurrentHashMap<Long, Boolean> concurrentHashMap;
    private ArraySet arraySet;

    @Setup(Level.Trial)
    public void generateData(Context context) {
        oneHashSet = new LongHashSet(context.size);
        concurrentHashMap = new ConcurrentHashMap<>(context.size);
        arraySet = new ArraySet(context.size);

        for (Long elt : context.generated) {
            oneHashSet.putKey(elt);
            concurrentHashMap.put(elt, Boolean.TRUE);
            arraySet.putKey(elt);
        }

        if (context.generated.size() <= 1000) {
            log.info("Created: {}", context.generated);
        }
    }

    @Benchmark
    public void iterate(Blackhole blackhole, Context context) {
        blackhole.consume(context.iter2.next());
    }

    @Benchmark
    public void getOneHashSet(Blackhole blackhole, Context context) {
        int index = oneHashSet.getKey(context.iter2.next());
        blackhole.consume(index != -1);
    }

    @Benchmark
    public void getConcurrentHashMap(Blackhole blackhole, Context context) {
        boolean res = concurrentHashMap.containsKey(context.iter2.next());
        blackhole.consume(res);
    }

    @Benchmark
    public void getArraySet(Blackhole blackhole, Context context) {
        int index = arraySet.getKey(context.iter2.next());
        blackhole.consume(index != -1);
    }

    @State(Scope.Thread)
    public static class Context {
        List<Long> generated;
        Iterator<Long> iter;
        CyclicIteratorV2 iter2;

        @Param({"100", "10000", "100000"})
        private int size;

        @Setup(Level.Trial)
        public void init() {
            var loadFactor = 0.75;
            generated = Generators.generate((int) (size * loadFactor));
            iter = new CyclicIterator(generated);
            iter2 = new CyclicIteratorV2(generated);
        }
    }

    // no boxing
    private static class CyclicIteratorV2 {
        private final long[] elts;
        private int current = 0;
        private final int maxIndex;

        private CyclicIteratorV2(List<Long> elts) {
            this.elts = elts.stream().mapToLong(v -> v).toArray();
            this.maxIndex = elts.size() - 1;
        }

        public long next() {
            if (maxIndex >= current) {
                current = 0;
            } else {
                current++;
            }

            return elts[current];
        }
    }

    private static class CyclicIterator implements Iterator<Long> {
        private final Long[] elts;
        private int current = 0;
        private final int maxIndex;

        private CyclicIterator(List<Long> elts) {
            this.elts = elts.toArray(new Long[elts.size()]);
            this.maxIndex = elts.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Long next() {
            if (maxIndex >= current) {
                current = 0;
            } else {
                current++;
            }

            return elts[current];
        }
    }
}
