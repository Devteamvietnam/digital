package com.devteam.tutorial.algorithms.ds;

import lombok.Getter;
import lombok.Setter;

public interface Tree<T> {
  int size() ;

  void clear();

  void add(T obj);

  void addAll(T[] obj);

  boolean remove(T obj);

  boolean contains(T obj);

  @Setter @Getter
  class Node<T> {

    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;
    private T    item;

    Node(T item) {
      this.item = item;
      this.left = null;
      this.right = null;
      this.parent = null;
    }
  }
}
