package com.devteam.tutorial.algorithms.sort;

import java.util.Comparator;

abstract public class Sort<T> {
  public abstract T[] sort(T[] array, Comparator<T> comparator); 
    
  public void swap(T array[], int i, int j) {
    T obj    = array[i];
    array[i] = array[j];
    array[j] = obj;
  }
  
  public void printArray(T[] array){
    for(int i = 0; i < array.length; i++){
      System.out.print(array[i] + " ");
    }
    System.out.println("-------------");
  }
  
}

