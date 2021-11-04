package com.devteam.tutorial.algorithms.ds;

public interface Stack<T> {
  
  public boolean empty();
  
  public T peek();
  
  public T pop();

  public int size();
  
  public T push(T obj);
  
  public int search(T obj);
}
