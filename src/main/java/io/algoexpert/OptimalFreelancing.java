package io.algoexpert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class OptimalFreelancing {
    private static final Logger logger = LoggerFactory.getLogger(OptimalFreelancing.class);

    public int optimalFreelancing(Map<String, Integer>[] jobs) {
        var candidates = new HashMap<Integer, Integer>();

        for (var i = 1; i <= 10; i++) {
            var paymentsOnDeadline = new ArrayList<Integer>();
            for (var job : jobs) {
                if (job.get("deadline") == i) {
                    var payment = job.get("payment");
                    paymentsOnDeadline.add(payment);
                }
            }
            Collections.sort(paymentsOnDeadline);
            Collections.reverse(paymentsOnDeadline);
            if (paymentsOnDeadline.size() > 0) {
                candidates.put(i, paymentsOnDeadline.get(0));
            } else {
                candidates.put(i, 0);
            }

            for (var k = 1; k < paymentsOnDeadline.size(); k++) {
                for (var j = 1; j < i; j++) {
                    var current = candidates.get(j);
                    if (current != null && current < paymentsOnDeadline.get(k)) {
                        candidates.put(j, paymentsOnDeadline.get(k));
                    }
                }
            }
        }

        var totalPayment = 0;
        var totalDays = 0;
        var max = 7;

        for (var i = 1; i <= 10; i++) {
            var next = candidates.get(i);
            if (next == 0) {
                max++;
            }
            if (totalDays < max) {
                totalPayment += next;
                totalDays++;
            } else {
                if (candidates.get(i - max) < next) {
                    totalPayment -= candidates.get(i - max);
                    totalPayment += next;
                }
            }
        }

        return totalPayment;
    }

    @Test
    public void test1() {
        var data = List.of(
            Map.of(
                "deadline", 2,
                "payment", 2
            ),
            Map.of(
                "deadline", 4,
                "payment", 3
            ),
            Map.of(
                "deadline", 5,
                "payment", 1
            ),
            Map.of(
                "deadline", 7,
                "payment", 2
            ),
            Map.of(
                "deadline", 3,
                "payment", 1
            ),
            Map.of(
                "deadline", 3,
                "payment", 2
            ),
            Map.of(
                "deadline", 1,
                "payment", 3
            )
        );

        Assert.assertEquals(
            optimalFreelancing(
                data.toArray(Map[]::new)
            ),
            13
        );
    }

    @Test
    public void test2() {
        var data = List.of(
            Map.of(
                "deadline", 2,
                "payment", 1
            ),
            Map.of(
                "deadline", 2,
                "payment", 2
            ),
            Map.of(
                "deadline", 2,
                "payment", 3
            ),
            Map.of(
                "deadline", 2,
                "payment", 4
            ),
            Map.of(
                "deadline", 2,
                "payment", 5
            ),
            Map.of(
                "deadline", 2,
                "payment", 6
            ),
            Map.of(
                "deadline", 2,
                "payment", 7
            )
        );

        Assert.assertEquals(
            optimalFreelancing(
                data.toArray(Map[]::new)
            ),
            13
        );
    }

    @Test
    public void test3() {
        var data = List.of(
            Map.of(
                "deadline", 2,
                "payment", 1
            ),
            Map.of(
                "deadline", 1,
                "payment", 4
            ),
            Map.of(
                "deadline", 3,
                "payment", 2
            ),
            Map.of(
                "deadline", 1,
                "payment", 3
            ),
            Map.of(
                "deadline", 4,
                "payment", 3
            ),
            Map.of(
                "deadline", 4,
                "payment", 2
            ),
            Map.of(
                "deadline", 4,
                "payment", 1
            ),
            Map.of(
                "deadline", 5,
                "payment", 4
            ),
            Map.of(
                "deadline", 8,
                "payment", 1
            )
        );

        Assert.assertEquals(
            optimalFreelancing(
                data.toArray(Map[]::new)
            ),
            16
        );
    }

    @Test
    public void test4() {
        var data = List.of(
            Map.of(
                "deadline", 10,
                "payment", 1
            )
        );

        Assert.assertEquals(
            optimalFreelancing(
                data.toArray(Map[]::new)
            ),
            1
        );
    }

    @Test
    public void test5() {
        var data = List.of(
            Map.of(
                "deadline", 1,
                "payment", 1
            ),
            Map.of(
                "deadline", 2,
                "payment", 1
            ),
            Map.of(
                "deadline", 3,
                "payment", 1
            ),
            Map.of(
                "deadline", 4,
                "payment", 1
            ),
            Map.of(
                "deadline", 5,
                "payment", 1
            ),
            Map.of(
                "deadline", 6,
                "payment", 1
            ),
            Map.of(
                "deadline", 7,
                "payment", 1
            ),
            Map.of(
                "deadline", 8,
                "payment", 1
            ),
            Map.of(
                "deadline", 9,
                "payment", 1
            ),
            Map.of(
                "deadline", 10,
                "payment", 1
            )
        );

        Assert.assertEquals(
            optimalFreelancing(
                data.toArray(Map[]::new)
            ),
            7
        );
    }
}
