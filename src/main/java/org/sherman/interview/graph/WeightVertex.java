package org.sherman.interview.graph;

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

import java.util.Objects;

public class WeightVertex {
    private final Vertex vertex;
    private final int totalWeight;

    public WeightVertex(Vertex vertex, int totalWeight) {
        this.vertex = vertex;
        this.totalWeight = totalWeight;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightVertex that = (WeightVertex) o;
        return totalWeight == that.totalWeight &&
            Objects.equals(vertex, that.vertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex, totalWeight);
    }

    @Override
    public String toString() {
        return "WeightVertex{" +
            "vertex=" + vertex +
            ", totalWeight=" + totalWeight +
            '}';
    }
}
