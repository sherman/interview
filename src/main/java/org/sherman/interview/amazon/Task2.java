package org.sherman.interview.amazon;

/*
 * Copyright (C) 2019 by Denis M. Gabaydulin
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

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Task2 {
    private static final Logger log = LoggerFactory.getLogger(Task2.class);

    @Test
    public void test() {
        assertEquals(solution(1, 4), "bbabb");
        assertEquals(solution(5, 3), "aabababa");
        assertEquals(solution(3, 3), "bababa");
        assertEquals(solution(4, 1), "aabaa");
        assertEquals(solution(5, 1), "aabaaba");
    }

    public String solution(int a, int b) {
        int seqA = 0;
        int seqB = 0;

        Next next = a > b ? Next.A : Next.B;

        StringBuilder builder = new StringBuilder();

        while (a > 0 || b > 0) {
            if (next == Next.A) {
                Preconditions.checkArgument(seqA < 3);
                builder.append('a');
                a--;
                seqA++;
                seqB = 0;
            }

            if (next == Next.B) {
                Preconditions.checkArgument(seqB < 3);
                builder.append('b');
                b--;
                seqB++;
                seqA = 0;
            }


            if (a > 0 && Math.max(a, b) == a) {
                if (seqA < 2) {
                    next = Next.A;
                } else {
                    next = Next.B;
                }
            }

            if (b > 0 && Math.max(a, b) == b) {
                if (seqB < 2) {
                    next = Next.B;
                } else {
                    next = Next.A;
                }
            }
        }

        return builder.toString();
    }

    private enum Next {
        A,
        B
    }
}
