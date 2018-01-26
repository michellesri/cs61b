// SLList - a list of integers

public class SLList {

  public class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
      item = i;
      next = n;
    }
  }

  private IntNode first; // variable

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

  public static void main(String[] args) {
    // creates a list of one integer, 10.
    SLList L = new SLList(15);
    L.addFirst(10);
    L.addFirst(5);

    System.out.println(L.getFirst()); //should return 5
  }

}
