package com.devteam.tutorial.algorithms.ds;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class QueueUnitTest {
  
  
  @Test
  public void testQueue() {
    LinkedListQueue<Long> queue = new LinkedListQueue<Long>();
    try {
      queue.element();
      Assert.fail("Stack is not empty");
    } catch(NoSuchElementException ex) {
      System.out.println("error");
      ex.printStackTrace();
    }
    Assert.assertEquals("head expected = null", null, queue.poll());
    try {
      queue.remove();
      Assert.fail("Stack is not empty");
    } catch(NoSuchElementException ex) {
       System.out.println("error");
       ex.printStackTrace();
    }
    Assert.assertEquals("return null", null, queue.poll());
    queue.add(1l);
    Assert.assertEquals("size expected = 1", 1, queue.size());
    System.out.println(queue.head());
    queue.add(2l);
    queue.offer(null);
    Assert.assertEquals("size expected = 1", 2, queue.size());
    System.out.println(queue.get(0));
    System.out.println(queue.element());
    Assert.assertEquals("head = 1", Long.valueOf(1), queue.element());
    Assert.assertEquals("head = 1", Long.valueOf(1), queue.poll());
    try {
      queue.add((Long) null);
      Assert.fail("obj = null");
    } catch(NullPointerException ex) {
      System.out.println("error");
      ex.printStackTrace();
    }
    }
  }