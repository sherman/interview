package org.sherman.interview.java;

import static java.lang.foreign.ValueLayout.JAVA_INT;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CLinkerApp {
    private static final Logger logger = LoggerFactory.getLogger(CLinkerApp.class);

    public static void main(String[] args) throws Throwable {
        /*var linker = Linker.nativeLinker();
        var lookup = linker.defaultLookup();

        var getPid = linker.downcallHandle(
            lookup.lookup("getpid").get(),
            FunctionDescriptor.of(JAVA_INT));

        var pid = (int) getPid.invokeExact();

        var kill = linker.downcallHandle(
            lookup.lookup("kill").get(),
            FunctionDescriptor.of(JAVA_INT, JAVA_INT, JAVA_INT)
        );

        var returnCode = (int) kill.invoke((int) pid, 2);

        logger.info("{}", returnCode);*/
    }
}
