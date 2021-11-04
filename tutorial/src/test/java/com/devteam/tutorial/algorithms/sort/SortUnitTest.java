package com.devteam.tutorial.algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class SortUnitTest {

  Comparator<Integer> INTEGER_COMPARATOR = new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) { return o1.compareTo(o2); }
  };

  Comparator<String> STRING_COMPARATOR = new Comparator<String>() {
    @Override
    public int compare(String o1, String o2) { return o1.compareTo(o2); }
  };

  Comparator<Person> PERSON_COMPARATOR = new Comparator<Person>() {
    @Override
    public int compare(Person o1, Person o2) {
      return o1.fullName.compareTo(o2.fullName);
    }
  };

  final Integer[] ARRAY_INTEGER_0 = {  };
  final Integer[] ARRAY_INTEGER_0_EXPECT = {};

  final Integer[] ARRAY_INTEGER_1 = { 2 };
  final Integer[] ARRAY_INTEGER_1_EXPECT = { 2 };

  final Integer[] ARRAY_INTEGER_2 = { 2, 1  };
  final Integer[] ARRAY_INTEGER_2_EXPECT = { 1, 2 };

  final Integer[] ARRAY_INTEGER_5 = { 2, 1, 3, 4, 5  };
  final Integer[] ARRAY_INTEGER_5_EXPECT = { 1, 2, 3, 4, 5 };

  final Integer[] ARRAY_INTEGER_6 = { 2, 1, 3, 6 , 4, 5  };
  final Integer[] ARRAY_INTEGER_6_EXPECT = { 1, 2, 3, 4, 5, 6 };

  final String[]  ARRAY_STRING_1 = {"a"};
  final String[]  ARRAY_STRING_1_EXPECT = { "a" };

  final String[]  ARRAY_STRING_2 = { "b", "a"};
  final String[]  ARRAY_STRING_2_EXPECT = { "a", "b" };

  final String[]  ARRAY_STRING_3 = { "b", "a", "c"};
  final String[]  ARRAY_STRING_3_EXPECT = { "a", "b", "c" };

  final Person[]  ARRAY_PERSON_0 = {  };
  final Person[]  ARRAY_PERSON_0_EXPECT = {  };

  final Person[] ARRAY_PERSON_3 = { new Person("Tuan", 42), new Person("Lam", 20), new Person("Hieu", 21)};
  final Person[] ARRAY_PERSON_3_EXPECT= { new Person("Hieu", 21), new Person("Lam", 20), new Person("Tuan", 42)};
  
  @Test
  public void testBubbleSorts() {

    runSort(new BubbleSort<Integer>(), INTEGER_COMPARATOR, ARRAY_INTEGER_0, ARRAY_INTEGER_0_EXPECT);
    runSort(new BubbleSort<Integer>(), INTEGER_COMPARATOR, ARRAY_INTEGER_1, ARRAY_INTEGER_1_EXPECT);
    runSort(new BubbleSort<Integer>(), INTEGER_COMPARATOR, ARRAY_INTEGER_2, ARRAY_INTEGER_2_EXPECT);
    runSort(new BubbleSort<Integer>(), INTEGER_COMPARATOR, ARRAY_INTEGER_5, ARRAY_INTEGER_5_EXPECT);
    runSort(new BubbleSort<Integer>(), INTEGER_COMPARATOR, ARRAY_INTEGER_6, ARRAY_INTEGER_6_EXPECT);

    runSort(new BubbleSort<String>(), STRING_COMPARATOR, ARRAY_STRING_1, ARRAY_STRING_1_EXPECT);
    runSort(new BubbleSort<String>(), STRING_COMPARATOR, ARRAY_STRING_2, ARRAY_STRING_2_EXPECT);

    runSort(new BubbleSort<Person>(), PERSON_COMPARATOR, ARRAY_PERSON_0, ARRAY_PERSON_0_EXPECT);
    runSort(new BubbleSort<Person>(), PERSON_COMPARATOR, ARRAY_PERSON_3, ARRAY_PERSON_3_EXPECT);

  }

  @Test
  public void testMergeSorts() {
    //Implement MergeSort with Sort api
  }

  @Test
  public void testQuickSorts() {
    runSort(new QuickSort<Integer>(), INTEGER_COMPARATOR, ARRAY_INTEGER_1, ARRAY_INTEGER_1_EXPECT);
    runSort(new QuickSort<Integer>(), INTEGER_COMPARATOR, ARRAY_INTEGER_2, ARRAY_INTEGER_2_EXPECT);
    runSort(new QuickSort<Integer>(), INTEGER_COMPARATOR, ARRAY_INTEGER_2, ARRAY_INTEGER_2_EXPECT);
    runSort(new QuickSort<Integer>(), INTEGER_COMPARATOR, ARRAY_INTEGER_5, ARRAY_INTEGER_5_EXPECT);
    runSort(new QuickSort<Integer>(), INTEGER_COMPARATOR, ARRAY_INTEGER_6, ARRAY_INTEGER_6_EXPECT);

    runSort(new QuickSort<String>(), STRING_COMPARATOR, ARRAY_STRING_1, ARRAY_STRING_1_EXPECT);
    runSort(new QuickSort<String>(), STRING_COMPARATOR, ARRAY_STRING_2, ARRAY_STRING_2_EXPECT);
    runSort(new QuickSort<String>(), STRING_COMPARATOR, ARRAY_STRING_3, ARRAY_STRING_3_EXPECT);

    runSort(new QuickSort<Person>(), PERSON_COMPARATOR, ARRAY_PERSON_0, ARRAY_PERSON_0_EXPECT);
    runSort(new QuickSort<Person>(), PERSON_COMPARATOR, ARRAY_PERSON_3, ARRAY_PERSON_3_EXPECT);
  }
  
  <T> void runSort(Sort<T> sortAlg, Comparator<T> comparator, T[] array, T[] expectResult) {
    //TODO: implement this
    T[] result = sortAlg.sort(array, comparator);
    sortAlg.printArray(result);
    assertArrayEquals(result, expectResult);
  }
}