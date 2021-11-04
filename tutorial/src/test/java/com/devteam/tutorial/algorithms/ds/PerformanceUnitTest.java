package com.devteam.tutorial.algorithms.ds;

import java.util.concurrent.Callable;

import org.junit.jupiter.api.Test;

public class PerformanceUnitTest {
  // run test and record running times for SIZE = 100,1000,1000
  // ArrayList is faster at access as size increases.
  // LinkedList is faster at add/remove as size increases.

  @Test
  public void testPerfomance() throws Exception {
    int SMALL_SIZE = 100;
    int MEDIUM_SIZE = 1000;
    int LARGE_SIZE = 10000;
    int BIG_SIZE = 100000;
    testList(new ArrayList<Integer>(10), SMALL_SIZE);
    testList(new LinkedList<>(), SMALL_SIZE);
    System.out.println();
    testList(new ArrayList<Integer>(10), MEDIUM_SIZE);
    testList(new LinkedList<>(), MEDIUM_SIZE);
    System.out.println();
    testList(new ArrayList<Integer>(10), LARGE_SIZE);
    testList(new LinkedList<>(), LARGE_SIZE);
    System.out.println();
    testList(new ArrayList<Integer>(10), BIG_SIZE);
    testList(new LinkedList<>(), BIG_SIZE);
  }

  void testList(final List<Integer> list, int size) throws Exception {
    Callable<Void> add = () -> {
      list.clear();
      for (int i = 0; i < size; i++)
        list.add(i);
      return null;
    };
    run("Test Add" + list.getClass().getSimpleName() + " Size = " + size, add, 10000);
  }

  public void run(String title, Callable<Void> executor, int loop) throws Exception {
    for (int i = 0; i < 100; i++)
      executor.call();
    long start = System.currentTimeMillis();
    for (int i = 0; i < loop; i++) {
      executor.call();
    }
    long end = System.currentTimeMillis();
    System.out.println(title);
    System.out.println("Execution time " + (end - start) + "ms");
  }
}
