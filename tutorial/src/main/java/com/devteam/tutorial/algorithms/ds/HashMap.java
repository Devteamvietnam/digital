package com.devteam.tutorial.algorithms.ds;

public class HashMap<Key, Value> implements Map<Key, Value>{
  private Entry<Key, Value>[] array;
  private int numOfEntry;
  private int capacity;

  public HashMap() {
    this((int) Math.pow(2, 4));
  }

  public HashMap(int initSize) {
    this.capacity = initSize;
    array = new Entry[this.capacity];
    this.numOfEntry = 0;
  }

  @Override
  public int size() { return numOfEntry; }

  @Override
  public void clear() {
    array = new Entry[this.capacity];
    numOfEntry = 0;
  }

  @Override
  public boolean containsKey(Key key) {
    int hash = calculateHash(key); 
    if(array[hash] != null && array[hash].getKey().equals(key)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean containsValue(Value value) {
    for(int i = 0; i < this.capacity; i++) {
      Entry entry = array[i];
      if(entry != null && entry.getValue().equals(value)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Value get(Key key) {
    int hash = calculateHash(key);

    if(array[hash] == null) {
      return null; 
    } else {
      return array[hash].getValue();
    }
  }

  @Override
  public void put(Key key, Value value) {
    if(containsKey(key)) {
      int hash = (key.hashCode() % this.capacity);
      array[hash] = new Entry<>(key, value);
    } else {
      int hash = calculateHash(key);
      array[hash] = new Entry<>(key, value);
      numOfEntry++;
    }
  }

  @Override
  public boolean isEmpty() {
    return numOfEntry == 0;
  }

  @Override
  public Value remove(Key key) {
    Value result = get(key);
    if(result != null) {
      int hash = calculateHash(key);
      array[hash] = null;
      numOfEntry--;

      hash = (hash + 1) % this.capacity;
      while(array[hash] != null) {
        Entry<Key, Value> entry = array[hash]; 
        array[hash] = null;
        put(entry.getKey(), entry.getValue());
        numOfEntry--;

        hash = (hash + 1) % this.capacity;
      }
    }

    return result;
  }

  private int calculateHash(Key key) {
    int hash = (key.hashCode() % this.capacity); 
    while(array[hash] != null && !array[hash].getKey().equals(key)) {
      hash = (hash + 1) % this.capacity; 
    }
    return hash;
  }
}