package com.devteam.tutorial.algorithms.sort;

import java.util.Comparator;

public class InsertionSort<T> extends Sort<T>{

  @Override
  public T[] sort(T[] array, Comparator<T> comparator) {
    for(int i = 1; i < array.length; i++) {
      T node = array[i];
      int j = i;
      while( j > 0 && comparator.compare(array[j-1], node) > 0){
        array[j] = array[j-1];
        j--;
      }
      array[j] = node;
    }
    return array;
  }
}
