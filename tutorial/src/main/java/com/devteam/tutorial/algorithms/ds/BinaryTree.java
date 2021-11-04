package com.devteam.tutorial.algorithms.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.devteam.tutorial.algorithms.ds.Tree.Node;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {
  private Node<T> root;
  private int  size;

  public BinaryTree() {
    this.root = null;
  }

  public Node<T> getRoot() {
    return this.root;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void add(T item) {
    Node<T> node = new Node<T>(item);

    if (this.root == null) {
      this.root = node;
      System.out.println("Set root: " + node.getItem());
      size++;
    } else {
      insert(this.root, node);
    }
  }

  @Override
  public void addAll(T[] items) {
    for(T item: items) {
      add(item); 
    }
  }

  @Override
  public boolean contains(T item) {
    Node<T> currentNode = get(item);

    if (currentNode == null) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public boolean remove(T item) {
    boolean deleted = false;

    if(this.root == null) {
      return false; 
    }

    Node<T> currentNode = get(item);

    if(currentNode != null) {
      if(currentNode.getLeft() == null && currentNode.getRight() == null) {
        // the node to remove doesnt have any children
        unlink(currentNode, null); 
        deleted = true;
      } else if(currentNode.getLeft() == null && currentNode.getRight() != null) {
        // the node to remove has a right child
        unlink(currentNode, currentNode.getRight()); 
        deleted = true;
      } else if(currentNode.getLeft() != null && currentNode.getRight() == null) {
        // the node to remove has a left child
        unlink(currentNode, currentNode.getLeft()); 
        deleted = true;
      } else {
        // the node to remove has both children
        Node<T> child = currentNode.getLeft();
        while(child.getRight() != null && child.getLeft() != null) {
          child = child.getRight(); 
        }

        child.getParent().setRight(null);
        child.setLeft(currentNode.getLeft());
        child.setRight(currentNode.getRight());
        unlink(currentNode, child);
        deleted = true;
      }
    }

    if(deleted) {
      this.size--; 
    }

    return deleted;
  }

  public void clear() { }

  private void unlink(Node<T> currentNode, Node<T> newNode) {
    if(currentNode == this.root) {
      newNode.setLeft(currentNode.getLeft());
      newNode.setRight(currentNode.getRight());
      this.root = newNode;
    } else if(currentNode.getParent().getRight() == currentNode) {
      currentNode.getParent().setRight(newNode);
    } else {
      currentNode.getParent().setLeft(newNode);
    }
  }

  private Node<T> get(T item) {
    Node<T> currentNode = this.root;
    while (currentNode != null) {
      int val = item.compareTo(currentNode.getItem());

      if (val == 0) {
        return currentNode;
      } else if (val < 0) {
        currentNode = currentNode.getLeft();
      } else {
        currentNode = currentNode.getRight();
      }
    }
    return null;
  }

  private void insert(Node<T> parent, Node<T> child) {

    if ((child.getItem()).compareTo(parent.getItem()) < 0) {
      if (parent.getLeft() == null) {
        parent.setLeft(child);
        child.setParent(parent);
        size++;
      } else {
        insert(parent.getLeft(), child);
      }
    } else if ((child.getItem()).compareTo(parent.getItem()) > 0) {
      if (parent.getRight() == null) {
        parent.setRight(child);
        child.setParent(parent);
        size++;
      } else {
        insert(parent.getRight(), child);
      }
    }

    // dont anything when parent and child happen to be equal
    // data in binary tree is assumed to be unique and the value already exists in
    // the tree
  }
}

class BTreePrinter {

  public static <T extends Comparable<?>> void printNode(Node<T> root) {
    int maxLevel = BTreePrinter.maxLevel(root);

    printNodeInternal(Collections.singletonList(root), 1, maxLevel);
  }

  private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
    if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
      return;

    int floor = maxLevel - level;
    int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
    int firstSpaces = (int) Math.pow(2, (floor)) - 1;
    int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

    BTreePrinter.printWhitespaces(firstSpaces);

    List<Node<T>> newNodes = new ArrayList<Node<T>>();
    for (Node<T> node : nodes) {
      if (node != null) {
        System.out.print(node.getItem());
        newNodes.add(node.getLeft());
        newNodes.add(node.getRight());
      } else {
        newNodes.add(null);
        newNodes.add(null);
        System.out.print(" ");
      }

      BTreePrinter.printWhitespaces(betweenSpaces);
    }
    System.out.println("");

    for (int i = 1; i <= endgeLines; i++) {
      for (int j = 0; j < nodes.size(); j++) {
        BTreePrinter.printWhitespaces(firstSpaces - i);
        if (nodes.get(j) == null) {
          BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
          continue;
        }

        if (nodes.get(j).getLeft() != null)
          System.out.print("/");
        else
          BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(i + i - 1);

        if (nodes.get(j).getRight() != null)
          System.out.print("\\");
        else
          BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
      }

      System.out.println("");
    }

    printNodeInternal(newNodes, level + 1, maxLevel);
  }

  private static void printWhitespaces(int count) {
    for (int i = 0; i < count; i++)
      System.out.print(" ");
  }

  private static <T extends Comparable<?>> int maxLevel(Node<T> root) {
    if (root == null)
      return 0;

    return Math.max(BTreePrinter.maxLevel(root.getLeft()), BTreePrinter.maxLevel(root.getRight())) + 1;
  }

  private static <T> boolean isAllElementsNull(List<T> list) {
    for (Object object : list) {
      if (object != null)
        return false;
    }

    return true;
  }
}
