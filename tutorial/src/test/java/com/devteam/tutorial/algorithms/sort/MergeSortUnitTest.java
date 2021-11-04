package com.devteam.tutorial.algorithms.sort;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MergeSortUnitTest {

  Integer[] nums = new Integer[] { 9, 12, 11, 13, 5, 6, 8, 14, 18, 17, 16, 7, 20 };

  @Test
  public void testMergeSort() throws Exception {
    mergeSort(new MergeSort());
  }

  void mergeSort(MergeSort sort) {
    sort.mergeSort(nums, 0, nums.length - 1);
    System.out.println(Arrays.toString(nums));
  }
}
