package com.devteam.tutorial.algorithms.sort;

import java.util.Comparator;

public class BubbleSort<T> extends Sort<T>{

  @Override
  public T[] sort(T[] array, Comparator<T> comparator) {
    for(int i = 0; i < array.length; i++) {
      for(int j = i; j < array.length; j++) {
        if(comparator.compare(array[i], array[j]) > 0) {
          swap(array, i, j);
        }
      }
    }
    return array;
  }
}
