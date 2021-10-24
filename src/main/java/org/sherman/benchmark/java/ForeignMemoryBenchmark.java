package org.sherman.benchmark.java;

/*
 * Copyright (C) 2020 by Denis M. Gabaydulin
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import jdk.incubator.foreign.*;
import one.nio.util.JavaInternals;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Fork(2)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class ForeignMemoryBenchmark {
    private static final int SIZE = 1 << 20;
    private static final String ID = "ID";
    private final long[] data = new long[SIZE];

    private final Unsafe unsafe = JavaInternals.getUnsafe();
    private final long unsafeAddress = unsafe.allocateMemory(SIZE * 8);

    private final MemorySegment memorySegment = MemorySegment.allocateNative(SIZE * 8, ResourceScope.globalScope());
    private final MemoryAddress foreignMemoryAddress = memorySegment.address();
    private final MemoryLayout layout = MemoryLayout.sequenceLayout(SIZE, MemoryLayout.valueLayout(64, ByteOrder.nativeOrder()));
    private final VarHandle varHandle = layout.varHandle(long.class, MemoryLayout.PathElement.sequenceElement());
    private final MethodHandle methodHandle = varHandle.toMethodHandle(VarHandle.AccessMode.SET);

    @Setup
    public void generate() {
        for (int i = 0; i < SIZE; i++) {
            data[i] = 1L;
        }
    }

    @TearDown
    public void free() {
        unsafe.freeMemory(unsafeAddress);
        // TODO: how to close() segment?
    }

    @Benchmark
    public void putLongUnsafe(Blackhole blackhole) {
        long index = ThreadLocalRandom.current().nextInt(SIZE);
        unsafe.putLong(unsafeAddress + index * 8, 1L);
    }

    @Benchmark
    public void putLongForeignMethodHandle(Blackhole blackhole) throws Throwable {
        long index = ThreadLocalRandom.current().nextInt(SIZE);
        methodHandle.invokeExact(foreignMemoryAddress, index, 1L);
    }

    @Benchmark
    public void putLongForeignVarHandle(Blackhole blackhole) throws Throwable {
        long index = ThreadLocalRandom.current().nextInt(SIZE);
        varHandle.set(foreignMemoryAddress, index, 1L);
    }
}
