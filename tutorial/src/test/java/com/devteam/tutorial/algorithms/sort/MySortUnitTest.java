package com.devteam.tutorial.algorithms.sort;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MySortUnitTest {

  @Test
  public void testBubbleSort() {
    testSortIteger(new BubbleSort<Integer>());
    testSortString(new BubbleSort<String>());
  }

  @Test
  public void testInsertionSort() {
    testSortIteger(new InsertionSort<Integer>());
    testSortString(new InsertionSort<String>());
  }

  void testSortIteger(Sort<Integer> alg) {
    Integer[] array  = { 1, 4, 3, 2, 5 } ;
    Integer[] expect = { 1, 2, 3, 4, 5 } ;
    testSort(alg, array, expect, new IntegerComparator());
    
    //TODO: test empty array case
    Integer[] emptyCase  = {};
    Integer[] emptyExpect = {};
    testSort(alg, emptyCase, emptyExpect, new IntegerComparator());

    //TODO: test single item case
    Integer[] singleCase  = {  1 };
    Integer[] singleExpect = { 1 };
    testSort(alg, singleCase, singleExpect, new IntegerComparator());
  }

  void testSortString(Sort<String> alg) {
    String[] array  = { "string 2", "string 1", "string 3" } ;
    String[] expect = { "string 1", "string 2", "string 3" } ;
    testSort(alg, array, expect, new StringComparator());
  }

  void testSortPerson(Sort<Person> alg) {
    Person[] array = { new Person("Tuan", 42), new Person("Lam", 20), new Person("Hieu", 21)};
    Person[] expect= { new Person("Hieu", 21), new Person("Lam", 20), new Person("Tuan", 42)};
    testSort(alg, array, expect, Person.COMPARATOR);
  }

  <T extends Comparable<T>>void testSort(Sort<T> alg, T[] array, T[] expect, Comparator<T> comparator) {
    T[] result = alg.sort(array, comparator);
    Assert.assertArrayEquals("expect a sorted array", expect, result);
  }


  void printArray(Object[] array) {
    for(int i = 0; i< array.length; i++) {
      if(i > 0) System.out.print(" ");
      System.out.print(array[i]);
    }
    System.out.println();
  }
}