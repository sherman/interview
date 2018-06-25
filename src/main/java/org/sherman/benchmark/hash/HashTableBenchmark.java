package org.sherman.benchmark.hash;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class HashTableBenchmark {

    private CacheHashMap cacheHashMap;
    private CacheTreeMap cacheTreeMap;
    private CacheLinkedHashMap cacheLinkedHashMap;

    private List<Elt> elts;
    private List<Elt> shuffled;

    @Param({"100", "10000", "100000"})
    public int size;

    @Setup
    public void generateData() {
        cacheHashMap = new CacheHashMap(size);
        cacheTreeMap = new CacheTreeMap(size);
        cacheLinkedHashMap = new CacheLinkedHashMap(size);

        elts = Generators.generate(size);

        shuffled = new ArrayList<>(elts);
        Collections.shuffle(shuffled);

        elts.forEach(
            elt -> cacheHashMap.set(elt)
        );

        elts.forEach(
            elt -> cacheTreeMap.set(elt)
        );

        elts.forEach(
            elt -> cacheLinkedHashMap.set(elt)
        );
    }

    @Benchmark
    public void getHashMap(Blackhole blackhole) {
        for (Elt elt : shuffled) {
            Elt found = cacheHashMap.get(elt.id);
            blackhole.consume(found);
        }
    }

    @Benchmark
    public void getTreeMap(Blackhole blackhole) {
        for (Elt elt : shuffled) {
            Elt found = cacheTreeMap.get(elt.id);
            blackhole.consume(found);
        }
    }

    @Benchmark
    public void getLinkedHashMap(Blackhole blackhole) {
        for (Elt elt : shuffled) {
            Elt found = cacheLinkedHashMap.get(elt.id);
            blackhole.consume(found);
        }
    }
}
