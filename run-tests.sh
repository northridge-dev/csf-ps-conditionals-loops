#!/bin/bash
set -e

echo "Compiling..."
javac -cp tests/junit-platform-console-standalone.jar:tests/:. **/*.java

echo "Running tests..."
java -XX:MaxJavaStackTraceDepth=1 -jar tests/junit-platform-console-standalone.jar execute \
  -cp tests/:. \
  --scan-class-path \
  --exclude-engine=junit-vintage \
  --exclude-engine=junit-platform-suite \
  --reports-dir test-results
