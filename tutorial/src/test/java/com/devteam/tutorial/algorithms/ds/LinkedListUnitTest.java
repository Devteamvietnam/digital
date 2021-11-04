package com.devteam.tutorial.algorithms.ds;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class LinkedListUnitTest {

  @Test
  public void testLinkedList() throws Exception {
    testList(new LinkedList<Integer>());
  }
  
  void testList(LinkedList<Integer> linkedList) {
    Integer[] list = new Integer[] { 1, 2, 3, 4 };
    // test Size empty
    assertEquals(0, linkedList.size());
    // test addAll
    linkedList.addAll(list);
    assertEquals("Expect list size = 4", 4, linkedList.size());
    assertEquals("expect pos 5", -1, linkedList.findPos(5));

    // test remove element 
    linkedList.remove(3); // linked list expected [1, 2, 4]
    assertEquals("Expect list -1 item", list.length - 1, linkedList.size());
    assertEquals("Expect pos 0", Integer.valueOf(1), linkedList.get(0));
    assertEquals("Expect pos 1", Integer.valueOf(2), linkedList.get(1));
    assertEquals("Expect item move up", Integer.valueOf(4), linkedList.get(2));
    
    //test remove element not have in the linked list
    try{
      linkedList.remove(5);
      fail("Exception not thrown");
    }catch(IllegalArgumentException e){
      assertTrue(e.getMessage() == "Could not find element in the Linked List");
    }

    // test removeAt
    linkedList.removeAt(1); // linked list expected [1, 4]
    assertEquals("Expect list = 2", 2, linkedList.size());
    assertEquals("Expect pos 1", Integer.valueOf(1), linkedList.get(0));
    assertEquals("Expect 4 at pos 2", Integer.valueOf(4), linkedList.get(1));

    // test add ( add a element in last of List )
    linkedList.add(2);
    assertEquals("Expect list size", 3, linkedList.size());
    assertEquals("Expect last element of the list = 2", Integer.valueOf(2), linkedList.get(linkedList.size() - 1));

    // test set
    linkedList.set(1, 24);
    linkedList.set(2, 25);
    assertEquals("Expect size does not change", 3, linkedList.size());
    assertEquals("Expect pos 1", Integer.valueOf(24), linkedList.get(1));
    assertEquals("Expect pos 2", Integer.valueOf(25), linkedList.get(2));

    // test clear
    linkedList.clear();
    assertEquals(0, linkedList.size());
  }

}
