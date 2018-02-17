package org.sherman.interview.java;

/*
 * Copyright (C) 2018 by Denis M. Gabaydulin
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

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<T> {
    private final AtomicReference<Node<T>> reference = new AtomicReference<>();

    public void push(T value) {
        Node<T> newNode = new Node<>();
        newNode.value = value;

        Node<T> oldHead;
        do {
            oldHead = reference.get();
            newNode.prev = oldHead;
        } while (!reference.compareAndSet(oldHead, newNode));
    }

    public T pop() {
        Node<T> oldHead;
        Node<T> newHead;

        do {
            oldHead = reference.get();

            if (oldHead == null) {
                return null;
            }

            newHead = oldHead.prev;
        } while (!reference.compareAndSet(oldHead, newHead));

        return oldHead.value;
    }

    public Node<T> getHead() {
        return reference.get();
    }

    public static class Node<T> {
        T value;
        Node<T> prev;

        public T getValue() {
            return value;
        }

        public Node<T> getPrev() {
            return prev;
        }
    }
}
