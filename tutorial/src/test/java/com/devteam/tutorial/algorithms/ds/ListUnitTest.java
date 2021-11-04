package com.devteam.tutorial.algorithms.ds;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ListUnitTest {
  
  @Test
  public void testArrayList() throws Exception {
    testList(new ArrayList<Integer>(10));
  }
  
  @Test
  public void testLinkedList() {
    Integer[] ARRAY = new Integer[] { 1, 2, 3, 4 };
    LinkedList<Integer> list = new LinkedList<>();

    //test add
    list.addAll(ARRAY);
    Assert.assertEquals("size expect = 4" , 4, list.size());
    
    //test remove, removeAt
    list.remove();
    Assert.assertEquals("size expect = 3" , 3, list.size());
    Assert.assertEquals("expect pos 0 = 2", Integer.valueOf(2), list.get(0));
    
//    list.removeTail();
    Assert.assertEquals("expect pos 1 = 3", Integer.valueOf(3), list.get(1));
    Assert.assertEquals("size expect = 2", 2, list.size());
    list.add(4);
    
  //remove
    Assert.assertEquals("size expect ", false ,list.remove(5));
    Assert.assertEquals("size expect = 3", 3, list.size());
    list.remove(4);
    Assert.assertEquals("size expect ", 2, list.size());
    list.add(4);
    list.remove(3);
    Assert.assertEquals("size expect ", 2, list.size());
    Assert.assertEquals("expect pos 1 = 4", Integer.valueOf(4), list.get(1));
    Assert.assertEquals("expect pos 0 = 2", Integer.valueOf(2), list.get(0));
    
    list.add((Integer)null);
    Assert.assertEquals("size expect ", 3, list.size());
  }

  void testList(List<Integer> list) {
    Integer[] ARRAY =  new Integer[] { 1, 2, 3, 4 };
    list.addAll(ARRAY);
    Assert.assertEquals("expect list size" , 4, list.size());
    Assert.assertEquals("expect pos 5", -1, list.findPos(5));
    list.remove(2);
    Assert.assertEquals("expect list has less 1 item ", ARRAY.length - 1, list.size());
    Assert.assertEquals("expect 2 is no longer in the list ", -1, list.findPos(2));
    Assert.assertEquals("expect pos 0 ", Integer.valueOf(1), list.get(0));
    Assert.assertEquals("expect pos 1 ", Integer.valueOf(3), list.get(1));
    Assert.assertEquals("expect pos 2 ", Integer.valueOf(4), list.get(2));
    Assert.assertEquals("expect item move up one position", Integer.valueOf(3), list.get(1));
    
    list.removeAt(2);
    Assert.assertEquals("size expect = 2", 2, list.size());
    Assert.assertEquals("expect pos 0 ", Integer.valueOf(1), list.get(0));
    Assert.assertEquals("expect pos 1 ", Integer.valueOf(3), list.get(1));
    Assert.assertEquals("expect 3 at pos 1", 1, list.findPos(3));
    
    list.add(2);
    Assert.assertEquals("expect list size" , 3, list.size());
    Assert.assertEquals("expect item at pos 2", Integer.valueOf(2), list.get(2));
    
    //test set
    list.set(0, 10);
    list.set(2, 10);
    Assert.assertEquals("Expect size does not change", 3, list.size());
    Assert.assertEquals("Expect size does not change", Integer.valueOf(10), list.get(0));
    Assert.assertEquals("Expect size does not change", Integer.valueOf(10), list.get(2));

    //test clear
    list.clear();
    Assert.assertEquals(0, list.size());
    
    for(int i = 0; i < 100; i++) {
      list.add(i);
    }
    Assert.assertEquals("Test check full", 100, list.size());
    
  }
}