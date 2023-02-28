#!/bin/bash

set -e -x

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/home/sherman/work/interview/src/main/resource/

java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI \
--add-exports jdk.internal.vm.ci/jdk.vm.ci.code=ALL-UNNAMED \
--add-exports jdk.internal.vm.ci/jdk.vm.ci.code.site=ALL-UNNAMED \
--add-exports jdk.internal.vm.ci/jdk.vm.ci.hotspot=ALL-UNNAMED  \
--add-exports jdk.internal.vm.ci/jdk.vm.ci.meta=ALL-UNNAMED \
--add-exports jdk.internal.vm.ci/jdk.vm.ci.runtime=ALL-UNNAMED \
-jar target/FunMicro-benchmarks.jar \
MathBenchmark
