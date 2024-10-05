package org.sherman.interview.java;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CLinkerApp {
    private static final Logger logger = LoggerFactory.getLogger(CLinkerApp.class);

    public static void main(String[] args) throws Throwable {
        try (var memorySession = Arena.ofConfined()) {
            var cLinker = Linker.nativeLinker();
            var getpidMH = cLinker.downcallHandle(
                cLinker.defaultLookup().find("getpid").get(),
                FunctionDescriptor.of(JAVA_INT)
            );

            int pid = (int) getpidMH.invokeExact();
            System.out.printf("MethodHandle calling getpid() (%d)\n", pid);

            var timeStruct = MemoryLayout.structLayout(
                    JAVA_LONG.withName("tv_sec"),
                    JAVA_LONG.withName("tv_usec")
                )
                .withName("timeval");

            var timeStructPointer = memorySession.allocate(timeStruct);

            var tzStruct = MemoryLayout.structLayout(
                    JAVA_INT.withName("tz_minuteswest"),
                    JAVA_INT.withName("tz_dsttime")
                )
                .withName("timezone");

            var tzStructPointer = memorySession.allocate(tzStruct);

            var getTimeMH = cLinker.downcallHandle(
                cLinker.defaultLookup().find("gettimeofday").get(),
                FunctionDescriptor.ofVoid(timeStruct, tzStruct)
            );

            var vh = tzStruct.varHandle(MemoryLayout.PathElement.groupElement("tv_usec"));

            getTimeMH.invokeExact(timeStructPointer, tzStructPointer);
            System.out.printf("MethodHandle calling gettimeofday() (%d)\n", vh.get(timeStructPointer.asSlice(0)));

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
