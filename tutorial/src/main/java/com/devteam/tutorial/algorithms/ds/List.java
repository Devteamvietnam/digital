package com.devteam.tutorial.algorithms.ds;

public interface List<T> {
  public int size() ;
  
  public void clear();
  
  public void add(T obj);
  
  public void addAll(T[] obj);
  
  public boolean remove(T obj);
  
  public T removeAt(int pos);
  
  public T get(int pos);
  
  public void set(int pos, T obj);
  
  public int findPos(T obj);
}
