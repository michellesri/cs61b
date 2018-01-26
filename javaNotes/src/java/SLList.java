// SLList - a list of integers

public class SLList {

  private static class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
      item = i;
      next = n;
    }
  }

  private IntNode first; // variable
  private int size;

  public SLList(int x) { // constructor
    first = new IntNode(x, null);
  }


  public void addFirst(int x) {
    // adds x to the front of the list
    first = new IntNode(x, first);
  }

  public int getFirst() {
    // returns the first item in the list
    return first.item;
  }

  public int sizeIterative() {
    int counter = 0;
    IntNode current = first;

    while (current != null) {
      counter++;
      current = current.next;
    }
    return counter;
  }

  // public static int sizeIterative(SLList s) {
  //   int counter = 0;
  //   SLList current = s.first;
  //
  //   while (current != null) {
  //     counter++;
  //     current = current.next;
  //   }
  //   return counter;
  // }

  private static int sizeRecursive(IntNode x) {
    if (x == null) {
      return 1;
    }
    return 1 + sizeRecursive(x.next);
  }

  public int size() {
    return sizeRecursive(first);
  }

  // public int sizeRecursive(IntNode x) {
  //   if (x == null) {
  //     return 1;
  //   }
  //   return 1 + sizeRecursive(x.next);
  // }

  public void addLast(int x) {
    // add an item to the end of a list
    IntNode p = first;
    // move p until it reaches the end of the list
    while (p.next != null) {
      p = p.next;
    }
    p.next = new IntNode(x, null);

  }

  public static void main(String[] args) {
    // creates a list of one integer, 10.
    SLList L = new SLList(15);
    L.addFirst(10);
    L.addFirst(5);
    L.addLast(20);
    System.out.println(L.sizeIterative());

    System.out.println(L.getFirst()); //should return 5
  }



}
