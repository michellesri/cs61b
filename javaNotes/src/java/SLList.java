// SLList - a list of integers

public class SLList<PlaceholderType> {
  // placeholderType allows us to not immediately define type as int or str.
  // the first item (if it exists) is at sentinel.next
  private StuffNode sentinel; // variable
  private StuffNode last;
  private int size;

  private class StuffNode {
    public PlaceholderType item;
    public StuffNode next;

    public StuffNode(PlaceholderType i, StuffNode n) {
      item = i;
      next = n;
    }
  }


//  public SLList() { // constructor for empty list
//    sentinel = new StuffNode(null, null);
//    size = 0;
//  }

  public SLList(PlaceholderType x) { // constructor
    sentinel = new StuffNode( x, null);
    size = 0;
  }


  public void addFirst(PlaceholderType x) {
    // adds x to the front of the list
    sentinel.next = new StuffNode(x, sentinel.next);
    size += 1;
  }

  public PlaceholderType getFirst() {
    // returns the first item in the list
    return sentinel.next.item;
  }

//  public int sizeIterative() {
//    int counter = 0;
//    StuffNode current = first;
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


  public void addLast(PlaceholderType x) {
  // add an item to the end of a list
    size += 1;

    last.next = new StuffNode(x,null);
    last = last.next;

  }

//  public static void main(String[] args) {
//    // creates a list of one integer, 10.
//    SLList L = new SLList();
//    L.addFirst(10);
//    L.addFirst(5);
//    L.addLast(20);
//    System.out.println(L.sizeIterative());
//
//    System.out.println(L.getFirst()); //should return 5
//  }



}
