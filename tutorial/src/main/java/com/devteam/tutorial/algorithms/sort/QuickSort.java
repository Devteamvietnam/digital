package com.devteam.tutorial.algorithms.sort;

import java.util.Comparator;

public class QuickSort<T> extends Sort<T> {
  
  @Override
  public T[] sort(T[] array, Comparator<T> comparator) {
    quickSort(array, 0, array.length - 1, comparator);
    return array;
  }
  
  public void quickSort(T[] array, int first, int last, Comparator<T> comparator) {
    T key = array[(first + last)/2];
    int i = first;
    int j = last;
    while(i <= j) {
      while(comparator.compare(array[i], key ) < 0) {
        i++;
      }
      while(comparator.compare(array[j], key) > 0) {
        j--;
      }
      swap(array, i, j);
      i++;
      j--;
    }
    if(first < j) {
      quickSort(array, first, j, comparator);
    }
    if(last > i) {
      quickSort(array, i, last, comparator);
    }
  }
}
