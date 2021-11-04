package com.devteam.tutorial.algorithms.ds;

import java.util.EmptyStackException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class StackUnitTest {

  @Test
  public void testStack() {
    Stack<String> stack = new LinkedListStack<String>();
    
    Assert.assertEquals("return expected = true", true, stack.empty());
    stack.push("string 1");
    stack.push("string 2");
    stack.push("string 3");
    Assert.assertEquals("pos expected = 1", 1, stack.search("string 2"));
    Assert.assertEquals("size expected = 3", 3, stack.size());
    Assert.assertEquals("string expected = string 3",new String ("string 3"), stack.peek());
    Assert.assertEquals("string expected = string 3",new String ("string 3"), stack.peek());
    Assert.assertEquals("string expected = string 3",new String ("string 3"), stack.pop());
    Assert.assertEquals("string expected = string 3",new String ("string 2"), stack.pop());
    Assert.assertEquals("size expected = 1", 1, stack.size());
    
    stack = new LinkedListStack<String>();
    try {
      stack.pop();
      Assert.fail("Stack is not empty");
    } catch(EmptyStackException ex) {
    }
  }
}