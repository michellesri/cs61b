// SLList - a list of integers

public class SLList<PlaceholderType> implements ListInterface<PlaceholderType>{
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

  public void insert(PlaceholderType x, int position) {
      int counter = 0;
      StuffNode current = sentinel.next;

      if (current == null) {
          sentinel.next = new StuffNode(x, null);
      }
      while (counter < position) {
          current = current.next;
      }
      StuffNode saved = current.next;
      current.next = new StuffNode(x, saved);
  }



  public void addLast(PlaceholderType x) {
  // add an item to the end of a list
      StuffNode newNode = new StuffNode(x,null);

      if (size <= 0) {
          sentinel.next = newNode;
          last = sentinel.next;
      } else {
          last.next = newNode;
          last = last.next;
      }
      size++;
  }

  public PlaceholderType removeLast() {
      StuffNode current = sentinel.next;

      if (size <= 0) {
          return null;
      }

      while (current.next != last) {
          current = current.next;
      }
      StuffNode saved = current.next;
      current.next = null;
      return saved.item;
  }

  public PlaceholderType getLast() {
      return last.item;
  }

  @Override
    public void print() {
      System.out.println("blahblah");
      for (StuffNode p = sentinel.next; p != null; p = p.next) {
          System.out.print(p.item + " ");
      }
  }

    public void insertFive(PlaceholderType x1, PlaceholderType x2, PlaceholderType x3) {
        insert(x1, 0);
        insert(x2, 1);
        insert(x3, 2);
    }




}
