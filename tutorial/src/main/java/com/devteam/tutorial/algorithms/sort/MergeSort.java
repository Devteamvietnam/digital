package com.devteam.tutorial.algorithms.sort;

public class MergeSort {
  // calculating next gap
  private static int nextGap(int gap) {
    if (gap <= 1)
      return 0;
    return (int) Math.ceil(gap / 2.0);
  }

  // funtion for swapping
  private static void swap(Integer[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  // Độ phức tạp về thời gian: O (nlog n)
  // Độ phức tạp của không gian: O (1)
  public static void merge(Integer[] nums, int start, int end) {
    int gap = end - start + 1;
    for (gap = nextGap(gap); gap > 0; gap = nextGap(gap)) {
      for (int i = start; i + gap <= end; i++) {
        int j = i + gap;
        if (nums[i] > nums[j])
          swap(nums, i, j);
      }
    }
  }
  // Sắp xếp đệ quy log n
  // mỗi lần gọi merge ()
  // thực hiện nlog n bước
  // Độ phức tạp về thời gian: O (n * log n + 2 ((n / 2) * log (n / 2)) +
  // 4 ((n / 4) * log (n / 4)) + ..... + 1)
  // Độ phức tạp về thời gian: O (logn * (n * log n))
  // O (n * (logn) ^ 2)
  // Độ phức tạp của không gian: O (1)
  public void mergeSort(Integer[] nums, int s, int e) {
    if (s == e)
      return;
    // mid to slice
    // array in two halves
    int mid = (s + e) / 2;
    // Recursive to sort left
    // and right subarrays
    mergeSort(nums, s, mid);
    mergeSort(nums, mid + 1, e);
    merge(nums, s, e);
  }
}
