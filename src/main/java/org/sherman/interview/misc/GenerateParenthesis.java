package org.sherman.interview.misc;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    private static final Logger log = LoggerFactory.getLogger(GenerateParenthesis.class);

    public static List<String> generateParentheses(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis("", n, n, result);
        return result;
    }

    private static void generateParenthesis(String prefix, int open, int close, List<String> result) {
        if (open == 0 && close == 0) {
            log.info("{}", prefix);
            result.add(prefix);
        } else {
            if (open != 0 && open <= close) {
                generateParenthesis(prefix + "(", open - 1, close, result);
            }

            if (close != 0) {
                generateParenthesis(prefix + ")", open, close - 1, result);
            }
        }
    }
}
