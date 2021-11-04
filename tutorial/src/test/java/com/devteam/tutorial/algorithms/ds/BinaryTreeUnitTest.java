package com.devteam.tutorial.algorithms.ds;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BinaryTreeUnitTest {

  @Test
  public void BinaryTreeUnitTest() {
    BinaryTree<Integer> binaryTree = new BinaryTree<>();
    assertEquals(0, binaryTree.size());

    binaryTree.add(10);
    binaryTree.add(16);
    binaryTree.add(7);
    binaryTree.add(13);
    binaryTree.add(8);
    binaryTree.add(3);
    binaryTree.add(14);
    binaryTree.add(17);

    System.out.println("Binary Tree: ");
    BTreePrinter.printNode(binaryTree.getRoot());

    assertEquals(8, binaryTree.size());
    assertTrue(binaryTree.contains(7));
    assertFalse(binaryTree.contains(1));

    // remove leaf Node
    assertTrue(binaryTree.remove(8));
    assertEquals(7, binaryTree.size());
    assertEquals(false, binaryTree.contains(8));
    System.out.println("Binary Tree after remove 8:");
    BTreePrinter.printNode(binaryTree.getRoot()); 

    // remove Node has a Left child 
    assertTrue(binaryTree.remove(7));
    assertEquals(6, binaryTree.size());
    assertEquals(false, binaryTree.contains(7));
    System.out.println("Binary Tree after remove 7:");
    BTreePrinter.printNode(binaryTree.getRoot()); 

    // remove Node has a Right child
    assertTrue(binaryTree.remove(13));
    assertEquals(5, binaryTree.size());
    assertEquals(false, binaryTree.contains(13));
    System.out.println("Binary Tree after remove 13:");
    BTreePrinter.printNode(binaryTree.getRoot()); 

    //remote Node has both children
    assertTrue(binaryTree.remove(16));
    assertEquals(4, binaryTree.size());
    assertEquals(false, binaryTree.contains(16));

    System.out.println("Binary Tree after remove 16:");
    //        BTreePrinter.printNode(binaryTree.getRoot());

  }
}
