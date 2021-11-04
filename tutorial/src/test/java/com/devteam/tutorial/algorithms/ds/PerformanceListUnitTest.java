package com.devteam.tutorial.algorithms.ds;

import java.util.concurrent.Callable;

import org.junit.jupiter.api.Test;

public class PerformanceListUnitTest {
  
  @Test
  public void testPerformance() throws Exception {
    int SMALL_SIZE = 100;
    testList(new ArrayList<Integer>(10), SMALL_SIZE);
    testList(new LinkedList<Integer>(), SMALL_SIZE);
    
    System.out.println();
    
    int MEDIUM_SIZE = 1000;
    testList(new ArrayList<Integer>(10), MEDIUM_SIZE);
    testList(new LinkedList<Integer>(),  MEDIUM_SIZE);

    System.out.println();
    
    int LARGE_SIZE = 10000;
    testList(new ArrayList<Integer>(10), LARGE_SIZE);
    testList(new LinkedList<Integer>(),  LARGE_SIZE);
  }
  
  void testList(final List<Integer> list, int size) throws Exception {
    Callable<Void> add = () -> {
      list.clear();
      for(int i = 0; i < size; i++) list.add(i);
      return null;
    };
    run("Test Add " + list.getClass().getSimpleName() + ", Size = " + size, add, 100000);
  }
  
  public void run(String title, Callable<Void> executor, int loop) throws Exception {
    for(int i = 0; i < 100; i++) executor.call();

    long start = System.currentTimeMillis();
    for(int i = 0; i < loop; i++) {
      executor.call();
    }
    long end = System.currentTimeMillis();
    System.out.println(title);
    System.out.println("  Execution Time: " + (end - start) + "ms");
  }
}