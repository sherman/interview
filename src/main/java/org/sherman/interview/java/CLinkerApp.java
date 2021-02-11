package org.sherman.interview.java;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.LibraryLookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodType;

public class CLinkerApp {
    private static final Logger logger = LoggerFactory.getLogger(CLinkerApp.class);

    public static void main(String[] args) throws Throwable {
        var linker = CLinker.getInstance();
        var lookup = LibraryLookup.ofDefault();

        var getPid = linker.downcallHandle(
            lookup.lookup("getpid").get(),
            MethodType.methodType(int.class),
            FunctionDescriptor.of(CLinker.C_INT));

        var pid = (int) getPid.invokeExact();

        var kill = linker.downcallHandle(
            lookup.lookup("kill").get(),
            MethodType.methodType(int.class, int.class, int.class),
            FunctionDescriptor.of(CLinker.C_INT, CLinker.C_INT, CLinker.C_INT)
        );

        var returnCode = (int) kill.invoke((int) pid, 2);

        logger.info("{}", returnCode);
    }
}
