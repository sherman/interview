package org.sherman.benchmark.java;

import com.google.common.hash.Hashing;
import java.util.HashSet;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class Collisions {
    private static final Random r = new Random();
    private static final Logger logger = LoggerFactory.getLogger(Collisions.class);
    @Test
    public void generate() {
        var hf = Hashing.murmur3_32_fixed(42);
        var items = new HashSet<Integer>();
        var l1 = new HashSet<Integer>();
        var l2 = new HashSet<Integer>();
        var l3 = new HashSet<Integer>();

        int collisions1 = 0;
        int collisions2 = 0;
        int collisions3 = 0;
        for (var i = 0; i < 50_000_000; i++) {
            var value = r.nextInt();
            if (items.add(value)) {
                /*for (var b : String.valueOf(value).getBytes()) {
                    System.out.println(Integer.toBinaryString((b & 0xFF) + 0x100).substring(1));
                }*/
                var h1 = hf.newHasher().putBytes(String.valueOf(value).getBytes()).hash().asInt();
                var h2 = hf.newHasher().putInt(String.valueOf(value).hashCode()).hash().asInt();
                var h3 = hf.newHasher().putInt(value).hash().asInt();
                if (!l1.add(h1)) {
                    collisions1++;
                }

                if (!l2.add(h2)) {
                    collisions2++;
                }

                if (!l3.add(h3)) {
                    collisions3++;
                }
            }
        }

        logger.info("c1: [{}], c2: [{}], c3: [{}]", collisions1, collisions2, collisions3);
    }
}
