#!/usr/bin/env bash
# Compile and run the demo. Usage: scripts/run.sh [--interactive]
set -e
cd "$(dirname "$0")/.."
mkdir -p out
javac --release 17 -d out $(find src/main -name '*.java')
java -cp out kz.aitu.navigation.Main "$@"
