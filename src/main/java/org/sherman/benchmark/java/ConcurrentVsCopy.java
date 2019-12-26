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
public class ConcurrentVsCopy {
    private static final Logger logger = LoggerFactory.getLogger(ConcurrentVsCopy.class);

    private final ConcurrentLinkedQueue<Message> messages1 = new ConcurrentLinkedQueue<>();

    @Param({"10000", "100000", "1000000"})
    private int size;

    @Setup(Level.Iteration)
    public void generateData() {
        messages1.clear();
        //actualMessageRef.set(messages2);

        for (int i = 0; i < size; i++) {
            Message message = new Message();
            message.value = "Message" + i;
            messages1.add(message);
        }
    }

    @Benchmark
    public void iterateAndCopy(Blackhole blackhole) {
        Message m = new Message();
        m.value = "test";
        messages1.add(m);

        List<Message> batch = new ArrayList<>();
        Iterator<Message> iter = messages1.iterator();
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
