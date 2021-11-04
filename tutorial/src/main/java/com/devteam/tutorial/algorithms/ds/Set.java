package com.devteam.tutorial.algorithms.ds;

public interface Set<T> {

  public void clear();

  public boolean add(T obj);

  public boolean addAll(T[] obj);

  public boolean contains(T obj);

  public boolean containsAll(T[] obj);

  public boolean equals(Object o);

  public boolean isEmpty();

  int hashCode();

  public boolean remove(Object o);

  int size();
}
