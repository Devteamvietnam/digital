package com.devteam.tutorial.algorithms.ds;

import java.util.NoSuchElementException;

public class LinkedListQueue<T> extends LinkedList<T> implements Queue<T>{

  public void add(T obj) {
    if(obj == null) {
      throw new NullPointerException();
    }
    //TODO: review 
    //    if(tail == null) {
    //      head = new Node<T>(obj);
    //      tail = head;
    //      currPos++;
    //    } else {
    //      Node<T> node = new Node<T>(obj);
    //      tail.setNext(node);
    //      tail = node;
    //      currPos++;
    //    }
    //    return true;
  }

  public int size() { return currSize; }

  @Override
  public T element() throws NoSuchElementException {
    if(size() == 0){
      throw new NoSuchElementException();
    } else {
      return head();
    }
  }

  @Override
  public boolean offer(T obj) {
    if(obj == null) {
      return false;
    }
    //TODO: call super.add(..) ?
    if(tail == null) {
      head = new Node<T>(obj);
      tail= head;
      currSize ++;
    } else {
      Node<T> node = new Node<T>(obj);
      tail.setNext(node);
      tail = node;
      currSize++;
    }
    return true;
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
  public T poll() {
    if(size() == 0) { return null; } else {
      //TODO: call removeHead();
      T val = head.getValue();
      head = head.getNext();
      currSize --;
      return val;
    }
  }

  @Override
  public T remove() throws NoSuchElementException, NullPointerException {
    if(size() == 0) {
      throw new NoSuchElementException();
    }
    return remove();
  }
}
