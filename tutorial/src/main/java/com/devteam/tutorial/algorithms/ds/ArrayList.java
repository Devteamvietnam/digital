package com.devteam.tutorial.algorithms.ds;

public class ArrayList<T> extends Object implements List<T> {

  private T[] array;
  private int currPos;

  public ArrayList() {
    this(10);
  }

  public ArrayList(int capacity) {
    if(capacity < 0 ) throw new IllegalArgumentException("Capacity cannot be negative: " + capacity);

    array = (T[]) new Object[capacity];
    currPos = 0;
  }

  @Override
  public int size() { return currPos; }

  @Override
  public void clear() {
    for(int i = 0; i < currPos; i++){
      array[i] = null;
    }
    currPos = 0;
  }

  @Override
  public void add(T obj) {
    checkFull();
    array[currPos++] = obj;
  }

  public void addAll(T[] array) {
    for(T sel : array) add(sel);
  }

  @Override
  public boolean remove(T obj) {
    int pos = findPos(obj);
    removeAt(pos);
    return true;
  }

  @Override
  public T removeAt(int pos) {
    if(pos >= currPos || pos < 0) throw new IndexOutOfBoundsException();
    T Val = array[pos];
    while(pos < currPos) {
      array[pos] = array[pos + 1];
      pos++;
    }
    currPos--;
    return Val;
  }

  @Override
  public T get(int pos) {
    return array[pos];
  }

  @Override
  public void set(int pos, T obj) {
    array[pos] = obj;
  }

  @Override
  public int findPos(T obj) {
    for(int pos = 0; pos < currPos; pos++ ) {
      if(array[pos].equals(obj)) {
        return pos;
      }
    }
    return -1;
  }

  private void checkFull() {
    if(currPos == array.length) {
      T[] newArray = (T[]) new Object [(array.length * 3/2) + 1];

      for(int i = 0; i < array.length; i++) {
        newArray[i] = array[i];
      }
      array = newArray;
    }
  }

  public String toString() {
    if(size() == 0) return "[]";
    else{
      StringBuilder sb = new StringBuilder(currPos);
      sb.append("[");
      for(int i = 0; i < currPos - 1; i++){
        sb.append(array[i]).append(",");
      }
      sb.append(array[currPos - 1]).append("]");
      return sb.toString();
    }
  }
}
