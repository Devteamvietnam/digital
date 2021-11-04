package com.devteam.tutorial.algorithms.ds;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ArrayListUnitTest {

  @Test
  public void testArrayList() throws Exception {

    ArrayList<String> arrayList = new ArrayList<String>();
    arrayList.add("One");
    arrayList.add("Two");
    arrayList.add("Three");
    arrayList.add("Four");
    arrayList.add("Five");

    assertEquals("Expect list size = 5", 5, arrayList.size());

    //test retrieve by position
    assertEquals("Expect first element = One","One", arrayList.get(0));
    assertEquals("Expect element has pos 3","Four", arrayList.get(3));
    assertEquals("Expect last element = Five","Five", arrayList.get(arrayList.size() - 1));

    //test find by position
    assertEquals("expect pos -1", -1, arrayList.findPos("Six"));

    // test remove
    arrayList.remove("Two");
    assertEquals("Expect list -1 item", 4, arrayList.size());
    assertEquals("Expect pos -1", -1, arrayList.findPos("Two"));
    assertEquals("Expect item move up", "Three", arrayList.get(1));
    
    // test removeAt
    arrayList.removeAt(2);  //now element is Four
    assertEquals("Expect list = 3", 3, arrayList.size());
    assertEquals("Expect pos -1", -1, arrayList.findPos("Four"));
    assertEquals("Expect Five at pos 2", 2, arrayList.findPos("Five"));

    // test add
    arrayList.add("Elevent");
    assertEquals("Expect list size", 4, arrayList.size());
    assertEquals("Expect last element is Elevent", "Elevent", arrayList.get(arrayList.size() - 1));

    // test set
    arrayList.set(0, "Twelve");
    arrayList.set(2, "Thirteen");
    assertEquals("Expect size does not change", 4, arrayList.size());
    assertEquals("Expect pos 0", "Twelve", arrayList.get(0));
    assertEquals("Expect pos 2", "Thirteen", arrayList.get(2));

    // test clear
    arrayList.clear();
    assertEquals(0, arrayList.size());
    assertTrue(arrayList.get(1) == null);
  }
}
