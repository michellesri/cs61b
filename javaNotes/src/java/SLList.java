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

  // the first item (if it exists) is at sentinel.next
  private IntNode sentinel; // variable
  private int size;

  public SLList() { // constructor for empty list
    sentinel = new IntNode(63, null);
    size = 0;
  }

  public SLList(int x) { // constructor
    sentinel = new IntNode( 63, null);
    size = 0;
  }


  public void addFirst(int x) {
    // adds x to the front of the list
    sentinel.next = new IntNode(x, sentinel.next);
    size += 1;
  }

  public int getFirst() {
    // returns the first item in the list
    return sentinel.next.item;
  }

//  public int sizeIterative() {
//    int counter = 0;
//    IntNode current = first;
//
//    while (current != null) {
//      counter++;
//      current = current.next;
//    }
//    return counter;
//  }



  public int size() {
    return size;
  }


  public void addLast(int x) {
  // add an item to the end of a list
    size += 1;

    IntNode p = sentinel;
    // move p until it reaches the end of the list
    while (p.next != null) {
      p = p.next;
    }
    p.next = new IntNode(x, null);

  }

  public static void main(String[] args) {
    // creates a list of one integer, 10.
    SLList L = new SLList();
    L.addFirst(10);
    L.addFirst(5);
    L.addLast(20);
    System.out.println(L.sizeIterative());

    System.out.println(L.getFirst()); //should return 5
  }



}
