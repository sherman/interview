package org.sherman.benchmark.java;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class ListFilteringBenchmark {

    @Param({"10", "100", "1000"})
    private int size;

    @Param({"50", "90"})
    private int acqRate;

    private List<String> in;
    private Set<String> acq;

    @Setup
    public void setup() {
        in = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            in.add(UUID.randomUUID().toString());
        }

        int acqSize = size * (100 - acqRate) / 100;

        acq = new HashSet<>();
        Random random = new Random();
        System.out.println("acq size: " + acqSize);
        for (int i = 0; i < acqSize; i++) {
            acq.add(in.get(random.nextInt(size)));
        }
    }

    @Benchmark
    public void stream(Blackhole blackhole) {
        List<String> result = in.stream()
            .filter(topic -> !acq.contains(topic))
            .collect(Collectors.toList());

        blackhole.consume(result.size());
    }

    @Benchmark
    public void list(Blackhole blackhole) {
        List<String> result = new ArrayList<>();
        for (String topic : in) {
            if (!acq.contains(topic)) {
                result.add(topic);
            }
        }

        blackhole.consume(result.size());
    }
}
