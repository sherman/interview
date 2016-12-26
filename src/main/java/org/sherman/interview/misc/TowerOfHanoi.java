package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;
import org.sherman.interview.stack.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Denis Gabaydulin
 * @since 26/12/2016
 */
public class TowerOfHanoi {
    private static final Logger log = LoggerFactory.getLogger(TowerOfHanoi.class);

    private TowerOfHanoi() {
    }

    public static void towerOfHanoi(int n, @NotNull Stack<Disk> src, @NotNull Stack<Disk> aux, @NotNull Stack<Disk> dst) {
        if (n == 1) {
            transfer(n, src, dst);
        } else {
            towerOfHanoi(n - 1, src, dst, aux);

            transfer(n, src, dst);

            towerOfHanoi(n - 1, aux, src, dst);
        }
    }

    private static void transfer(int n, @NotNull Stack<Disk> src, Stack<Disk> dst) {
        checkArgument(dst.peek() == null || (dst.peek() != null && dst.peek().size > src.peek().size), "Invariant violation!");

        Disk disk = src.pop();
        dst.push(disk);

        log.info("Disk {} from {} to {}", n, src, dst);
    }

    static class Disk {
        final int size;

        Disk(int size) {
            this.size = size;
        }

        @Override
        public String toString() {
            return "" + size;
        }

    }

    static class NamedStack extends Stack<Disk> {
        private final String name;

        NamedStack(String name, int size) {
            super(size);
            this.name = name;
        }

        @Override
        public String toString() {
            return name + ":" + super.toString();
        }
    }
}
