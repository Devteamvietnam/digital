package com.devteam.tutorial.algorithms.sort;

import java.util.Comparator;

public class Person implements Comparable<Person> {
  static public Comparator<Person> COMPARATOR = new  Comparator<Person>() {
    public int compare(Person p1, Person p2) {
      return p1.compareTo(p2);
    }
  };

  String fullName;
  int    age;

  public Person(String name, int age) {
    this.fullName = name;
    this.age      = age;
  }

  public String toString() { return fullName; }

  @Override
  public int compareTo(Person p) {
    return fullName.compareTo(p.fullName);
  }
}