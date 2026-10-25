#!/usr/bin/env bash
# Compile and run the tests (no external libraries needed).
set -e
cd "$(dirname "$0")/.."
mkdir -p out
javac --release 17 -d out $(find src -name '*.java')
java -cp out kz.aitu.navigation.NavigatorTest
