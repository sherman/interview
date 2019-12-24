package org.sherman.benchmark.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

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
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class CopyVsIterate {
    private static final Logger log = LoggerFactory.getLogger(CopyVsIterate.class);

    private final ConcurrentLinkedQueue<Message> messages1 = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Message> messages2 = new ConcurrentLinkedQueue<>();

    @Param({"10000", "100000", "1000000"})
    private int size;

    @Setup(Level.Iteration)
    public void generateData() {
        messages1.clear();
        messages2.clear();

        for (int i = 0; i < size; i++) {
            Message message = new Message();
            message.value = "Message" + i;
            messages1.add(message);
            messages2.add(message);
        }

        //log.info("Size: {} {}", messages1.size(), messages2.size());
    }

    @Benchmark
    public void iterate(Blackhole blackhole) {
        //log.info("Size: {} {}", messages1.size(), messages2.size());

        List<Message> batch = new ArrayList<>();
        for (Message message : messages1) {
            batch.add(message);

            if (batch.size() == 1000) {
                blackhole.consume(batch);
                batch.forEach(m -> m.sent = true );
                batch.clear();
            }
        }

        if (!batch.isEmpty()) {
            blackhole.consume(batch);
            batch.forEach(m -> m.sent = true );
            batch.clear();
        }
    }

    @Benchmark
    public void copy(Blackhole blackhole) {
        List<Message> batch = new ArrayList<>();
        Iterator<Message> iter = messages2.iterator();
        while (iter.hasNext()) {
            Message message = iter.next();
            batch.add(message);
            iter.remove();

            if (batch.size() == 1000) {
                blackhole.consume(batch);
                batch.clear();
            }
        }

        if (!batch.isEmpty()) {
            blackhole.consume(batch);
            batch.clear();
        }
    }

    private static class Message {
        boolean acked;
        boolean sent;
        String value;
    }
}
