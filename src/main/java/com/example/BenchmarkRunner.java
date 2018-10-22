package com.example;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

  @State(Scope.Benchmark)
  public static class ExecutionPlan {

    @Param({"one", "two", "three", "four"})
    private String param;

    @Setup
    public void setup() {
      // do something
    }
  }

  @Benchmark
  public int firstBench(ExecutionPlan plan) {
    return plan.param.length();
  }

  @Benchmark
  public int secondBench(ExecutionPlan plan) {
    return plan.param.length();
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(BenchmarkRunner.class.getSimpleName())
      .warmupIterations(3)
      .measurementIterations(3)
      .mode(Mode.All)
      .forks(1)
      .build();

    new Runner(opt).run();
  }
}
