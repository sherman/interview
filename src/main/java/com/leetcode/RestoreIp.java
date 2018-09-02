package com.leetcode;

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

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestoreIp {
    private static final Logger log = LoggerFactory.getLogger(RestoreIp.class);

    public static String[] restoreIp(@NotNull String ip) {
        if (ip.length() > 12) {
            return new String[]{};
        }

        List<String> candidates = new ArrayList<>();
        List<String> res = new ArrayList<>();
        restoreIp2(ip, candidates, res);
        return res.toArray(new String[res.size()]);
    }

    public static void restoreIp2(String ip, List<String> candidates, List<String> result) {
        if (ip.isEmpty()) {
            if (isValidIp(candidates)) {
                result.add(
                    candidates.stream()
                        .map(v -> String.valueOf(v))
                        .collect(Collectors.joining("."))
                );
            }
        } else {
            if (ip.length() > 0) {
                candidates.add(ip.substring(0, 1));
                restoreIp2(ip.substring(1), candidates, result);
                candidates.remove(candidates.size() - 1);
            }
            if (ip.length() > 1) {
                candidates.add(ip.substring(0, 2));
                restoreIp2(ip.substring(2), candidates, result);
                candidates.remove(candidates.size() - 1);
            }
            if (ip.length() > 2) {
                candidates.add(ip.substring(0, 3));
                restoreIp2(ip.substring(3), candidates, result);
                candidates.remove(candidates.size() - 1);
            }
        }
    }

    private static boolean isValidIp(List<String> ip) {
        if (ip.size() != 4 || ip.stream().anyMatch(v -> v.startsWith("0") && v.length() > 1)) {
            return false;
        } else {
            return (Integer.valueOf(ip.get(0)) & 0xFF) == Integer.valueOf(ip.get(0))
                && (Integer.valueOf(ip.get(1)) & 0xFF) == Integer.valueOf(ip.get(1))
                && (Integer.valueOf(ip.get(2)) & 0xFF) == Integer.valueOf(ip.get(2))
                && (Integer.valueOf(ip.get(3)) & 0xFF) == Integer.valueOf(ip.get(3));
        }
    }
}
