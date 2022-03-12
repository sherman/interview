package org.sherman.jcstress;

import com.google.common.collect.ImmutableMap;
import java.util.concurrent.ThreadLocalRandom;
import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Arbiter;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.LL_Result;
import org.sherman.interview.java.NonBlockingCache;

@JCStressTest
@State
@Outcome(id = "0, 0", expect = Expect.ACCEPTABLE)
@Outcome(id = "1, 1", expect = Expect.ACCEPTABLE)
@Outcome(id = "2, 2", expect = Expect.ACCEPTABLE)

@Outcome(id = "0, 1", expect = Expect.FORBIDDEN)
@Outcome(id = "1, 0", expect = Expect.FORBIDDEN)
@Outcome(id = "0, 2", expect = Expect.FORBIDDEN)
@Outcome(id = "2, 0", expect = Expect.FORBIDDEN)
@Outcome(id = "1, 2", expect = Expect.FORBIDDEN)
@Outcome(id = "2, 1", expect = Expect.FORBIDDEN)
public class NonBlockingCacheStressTest {
    private final NonBlockingCache cache = new NonBlockingCache(2);

    @Actor
    public void update1() {
        var x = ThreadLocalRandom.current().nextLong(3);
        cache.update(ImmutableMap.of(0, x, 1, x));
    }

    @Actor
    public void update2() {
        var x = ThreadLocalRandom.current().nextLong(3);
        cache.update(ImmutableMap.of(0, x, 1, x));
    }

    @Arbiter
    public void arbiter(LL_Result r) {
        var result = cache.getData(new int[] {0, 1});
        r.r1 = result[0];
        r.r2 = result[1];
    }
}
