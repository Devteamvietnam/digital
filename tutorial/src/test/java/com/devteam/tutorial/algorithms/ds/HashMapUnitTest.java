package com.devteam.tutorial.algorithms.ds;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class HashMapUnitTest {

  @Test
  public void testHashMap() throws Exception {
    HashMap<String, Integer> map = new HashMap<>();
    assertTrue(map.isEmpty());
    map.put("key1", Integer.valueOf(1));
    assertEquals("value expected = 1", Integer.valueOf(1), map.get("key1"));
    map.put("key1", Integer.valueOf(2));
    assertEquals("value expected = 2", Integer.valueOf(2), map.get("key1"));
    map.put("key2", Integer.valueOf(12));
    map.put("key0", Integer.valueOf(0));
    map.put("key3", Integer.valueOf(3));
    map.put("key6", Integer.valueOf(6));

    assertEquals("size expected = 5", 5, map.size());
    assertEquals("value expected = 3", Integer.valueOf(3), map.get("key3"));
    assertEquals("value expected = 6", Integer.valueOf(6), map.get("key6"));
    assertEquals("value expected = null", null, map.get("key4"));
    assertEquals("return false", false, map.containsKey("key14"));
    assertEquals("return true", true, map.containsKey("key3"));

    map.remove("key1");
    assertEquals("size expected = 4", 4, map.size());
    assertEquals("value expected = null", null, map.get("key1"));
    map.remove("key3");
    assertEquals("return false", false, map.containsKey("key3"));
    assertEquals("value expected = null", null, map.get("key3"));
    assertEquals("value expected = 6", Integer.valueOf(6), map.get("key6"));
    assertEquals("value expected = 0", Integer.valueOf(0), map.get("key0"));
    assertEquals("return true", true, map.containsValue(0));
    assertEquals("return true", true, map.containsValue(6));
    assertEquals("return true", true, map.containsValue(12));
    assertEquals("return true", false, map.containsValue(2));

    map.clear();
    assertEquals("size expected = 0", 0, map.size());
    assertEquals("return true", false, map.containsValue(0));
  }
}