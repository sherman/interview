package org.sherman.benchmark.one.memory;

import one.nio.mem.Allocator;
import one.nio.mem.LongObjectHashMap;
import one.nio.mem.Malloc;
import one.nio.mem.SharedMemoryLongMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.sherman.benchmark.one.hash.Generators;
import org.sherman.benchmark.one.hash.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class MemoryFragmentation {
    private static final Logger log = LoggerFactory.getLogger(MemoryFragmentation.class);

    private final Map<Long, Value> data = new HashMap<>();
    private final LongObjectHashMap<Value> data2 = new LongObjectHashMap<>(100000);
    private final SharedMemoryLongMap data3 = new SharedMemoryLongMap();

    @Setup(Level.Trial)
    public void init(Context context) {
    }

    @Benchmark
    public void hashMap(Blackhole blackhole, Context context) {
        Value v = context.iter.next();
        data.put(v.v1, v);
        Value v1 = data.get(context.iter.next().v1);
        if (v.v4) {
            data.remove(context.iter.next().v1);
        }
        blackhole.consume(v.v4 && v1.v4);
    }

    @Benchmark
    public void sharedMemoryMap(Blackhole blackhole, Context context) {
        Value v = context.iter.next();
        data2.put(v.v1, v);
        Value v1 = data2.get(context.iter.next().v1);
        if (v.v4) {
            data2.remove(context.iter.next().v1);
        }
        blackhole.consume(v.v4 && v1.v4);
    }

    @State(Scope.Thread)
    public static class Context {
        private List<Value> generated;
        Iterator<Value> iter;

        @Param({"1000"})
        private int size;

        @Setup
        public void init() {
            generated = Generators.generateValues(size);
            iter = new CyclicIterator(generated);
        }
    }

    public static class Value {
        public long v1;
        public String v2;
        public int v3;
        public boolean v4;
    }

    private static class CyclicIterator implements Iterator<Value> {
        private final Value[] elts;
        private int current = 0;
        private final int maxIndex;

        private CyclicIterator(List<Value> elts) {
            this.elts = elts.toArray(new Value[elts.size()]);
            this.maxIndex = elts.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Value next() {
            if (maxIndex >= current) {
                current = 0;
            } else {
                current++;
            }

            return elts[current];
        }
    }
}
