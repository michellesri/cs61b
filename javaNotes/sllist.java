// SLList - a list of integers

public class SLList {
  public IntNode first; // variable

  public SLList(int x) { // constructor
    first = new IntNode(x, null);
  }

  public static void main(String[] args) {
    // creates a list of one integer, 10.
    SLList L = new SLList(10);
  }
}
