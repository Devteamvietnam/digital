package com.devteam.tutorial.algorithms.ds;

public class LinkedList<T> implements List<T> {
  protected Node<T> head;
  protected Node<T> tail;
  protected int currSize;

  public LinkedList() {
    this.head = null;
    this.tail = null;
    this.currSize = 0;
  }

  public LinkedList(T[] obj) {
    addAll(obj);
  }

  public T head() {
    return head.getValue();
  }

  public T tail() {
    return tail.getValue();
  }

  @Override
  public int size() { return currSize; }

  @Override
  public void clear() {
    Node<T> currentNode = head;
    while (currentNode != null){
      Node<T> nextNode = currentNode.getNext();
      currentNode.setNext(null);
      currentNode.setValue(null);
      currentNode = nextNode;
    }
    head = tail = null;
    currSize = 0;
  }

  @Override
  public void add(T obj) {
    if(head == null) {
      head = new Node<T>(obj);
      tail = head;
    } else {
      Node<T> newTailNode = new Node<T>(obj);
      tail.setNext(newTailNode);
      tail = newTailNode;
    }
    currSize++;
  }

  public T remove() {
    if(head == null){
      throw new IllegalStateException("The Linked List is empty and there are no element to remove!");
    } 
    T val = head.getValue();
    head = head.getNext();
    currSize--;
    return val;
  }

  public void addAll(T[] obj) {
    for(T sel : obj) add(sel);
  }

  @Override
  public boolean remove(T obj) {
    int findPos = findPos(obj);
    if(findPos == -1) { throw new IllegalArgumentException("Could not find element in the Linked List"); }
    removeAt(findPos);
    return true;
  }

  @Override
  public T removeAt(int pos) {
    if(head == null) {
      throw new IllegalStateException("The Linked List is empty there are no element to remove!");
    }
    if(pos < 0 || pos >= currSize) {
      throw new IllegalStateException("The Linked List is smaller than the position you're trying to remove at" + pos);
    }

    Node<T> currNode = head;
    Node<T> preNode = head;

    for(int i = 0; i < pos && currNode != null; i++) {
      preNode = currNode;
      currNode = currNode.getNext();
    }

    T currElement = currNode.getValue();
    preNode.setNext(currNode.getNext());
    currSize--;
    return currElement;
  }

  @Override
  public T get(int pos) {
    if(head == null) {
      throw new IllegalStateException("The Linked List is empty there are no elements to get!");
    }

    Node<T> currNode = head;
    int idx = 0;
    while(idx != pos) {
      currNode = currNode.getNext();
      idx++;
    }
    return currNode.getValue();
  }

  @Override
  public void set(int pos, T obj) {
    if(pos >= currSize || pos < 0){
      throw new IllegalStateException("The Linked List is smaller than the position you're trying to insert at " + pos);
    } 
    Node<T> currNode = head;
    for(int i = 0; i < pos && currNode != null; i++) {
      currNode = currNode.getNext();
    }
    currNode.setValue(obj);
  }

  public void add(int pos, T obj) {
    if(pos >= currSize){
      throw new IllegalStateException("The Linked List is smaller than the position you're trying to insert at " + pos);
    } 
    Node<T> currNode = head;
    for(int i = 1; i < pos && currNode != null; i++) {
      currNode = currNode.getNext();
    }
    //severs the link chain and reconnect with the new node
    Node<T> newNode = new Node<T>(obj);
    Node<T> nextNode = currNode.getNext();
    currNode.setNext(newNode);
    newNode.setNext(nextNode);
    currSize++;
  }

  @Override
  public int findPos(T obj) {
    if(head == null) {
      throw new IllegalStateException("The Linked List is empty there are no elements to find!");
    }
    Node<T> curr = head;
    int pos = 0;
    while(curr != tail) {
      if(curr.getValue().equals(obj)) {
        return pos;
      }
      curr = curr.getNext();
      pos++;
    }
    return -1;
  }

  public String toString() {
    if(size() == 0) return "[]";
    else{
      StringBuilder sb = new StringBuilder();
      Node<T> currNode = head;
      while(currNode != null) {
        sb.append(currNode.getValue());
        currNode = currNode.getNext();

        if(currNode != null) {
          sb.append(", ");
        }
      }
      return sb.toString();
    }
  }

  static protected class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
      this.value = value;
      this.next = null;
    }

    public T getValue() { return value; }
    public void setValue(T value) { this.value = value;}

    public Node<T> getNext() { return next;}
    public void setNext(Node<T> next) { this.next=next; }
  }
}
