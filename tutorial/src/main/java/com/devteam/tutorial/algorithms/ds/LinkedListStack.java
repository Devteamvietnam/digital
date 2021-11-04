package com.devteam.tutorial.algorithms.ds;

import java.util.EmptyStackException;

//TODO: reuse the methods removeHead, addHead or removeTail , addTail
public class LinkedListStack<T> extends LinkedList<T> implements Stack<T> {

  @Override
  public boolean empty() {
    if(tail == null) return true;
    return false;
  }

  @Override
  public T peek() {
    if(size() == 0){
      return null;
    } else {
      return head();
    }
  }

  @Override
  public T pop() {
    if(size() == 0){
      throw new EmptyStackException(); 
    } else {
      T val = head.getValue();
      head = head.getNext();
      currSize --;
      return val;
    }
  }

  @Override
  public T push(T obj) {
    if(size() == 0){
      head = new Node<T>(obj);
      tail = head;
      currSize ++;
    } else {
      Node<T> node = new Node<T>(obj);
      node.setNext(head);
      head = node;
      currSize ++;
    }
    return null;
  }

  @Override
  public int search(T obj) {
    Node<T> curr = new Node<T>(obj);
    curr = head;
    int pos = 0;
    while(curr != tail) {
      if(curr.getValue().equals(obj)){
        return pos;
      }
      curr = curr.getNext();
      pos ++;
    }
    return -1;
  }
}
