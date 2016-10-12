package org.sherman.interview.lists;

/*
 * Copyright (C) 2015 by Denis M. Gabaydulin
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

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ListsTest {
    @Test
    public void reverse() {
        Item elt3 = new Item();
        elt3.id = 3;

        Item elt2 = new Item();
        elt2.id = 2;
        elt2.next = elt3;

        Item head = new Item();
        head.id = 1;
        head.next = elt2;

        Item newHead = Lists.reverse(head);

        Item tmp = newHead;
        int prev = 4;
        while (tmp != null) {
            assertTrue(tmp.id < prev);
            prev = tmp.id;
            tmp = tmp.next;
        }
    }

    @Test
    public void getIntersectionOfSortedLists() {
        assertEquals(Lists.getIntersectionOfSortedLists(new int[]{1, 2, 3}, new int[]{2, 3}), new int[]{2, 3});
    }

    @Test
    public void getIntersectionOfSortedListsConstMemory() {
        assertEquals(Lists.getIntersectionOfSortedListsConstMemory(new int[]{1, 2, 3}, new int[]{2, 3}), new Integer[]{2, 3});
    }

}
