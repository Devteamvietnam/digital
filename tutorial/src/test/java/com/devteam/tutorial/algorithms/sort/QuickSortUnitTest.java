package com.devteam.tutorial.algorithms.sort;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuickSortUnitTest {

  Integer[]                input = { 20, 10, 15, 9, 7, 13, 18, 3 };
  private final static int SIZE  = 10;
  private final static int MAX   = 20;

  @BeforeEach
  public void setUp() throws Exception {
    input = new Integer[SIZE];
    Random generator = new Random();
    for (int i = 0; i < input.length; i++) {
      input[i] = generator.nextInt(MAX);
    }
  }

  @Test
  public void testQuickSort() throws Exception {
    quickSort(new QuickSortT());
  }

  void quickSort(QuickSortT quickSort) {
    // test null
    quickSort.sort(null);
    // test empty
    quickSort.sort(new Integer[0]);
    // test sort
    for (Integer i : input) {
      System.out.println(i);
    }
    long startTime = System.currentTimeMillis();
    quickSort.sort(input);
    long stopTime = System.currentTimeMillis();
    long elapsedTime = stopTime - startTime;
    System.out.println("QuickSort: " + elapsedTime);
    if (!validate(input)) {
      fail("Not happen");
    }
    assertTrue(true);
  }

  private boolean validate(Integer[] input2) {
    for (int i = 0; i < input2.length - 1; i++) {
      if (input2[i] > input2[i + 1]) {
        return false;
      }
    }
    return true;
  }
}
