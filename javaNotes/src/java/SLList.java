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


  public SLList() { // constructor
    sentinel = new StuffNode( null, null);
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

  public int size() {
    return size;
  }

  public PlaceholderType get(int i) {
    int counter = 0;
    StuffNode current = sentinel.next;
    while (counter != i) {
      counter++;
      current = current.next;
    }
    return current.item;
  }


  public void addLast(PlaceholderType x) {
  // add an item to the end of a list
      if (size <= 0) {
          sentinel.next = new StuffNode(x, null);
          last = sentinel.next;
      } else {
          last.next = new StuffNode(x,null);
          last = last.next;
          size += 1;
      }
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
