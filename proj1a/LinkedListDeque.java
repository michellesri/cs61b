public class LinkedListDeque<PlaceholderType> {
    // placeholderType allows us to not immediately define type as int or str.
    // the first item (if it exists) is at sentinel.next
    private TypeNode sentinel; // variable
    private TypeNode last;
    private int size;

    private class TypeNode {
        public PlaceholderType item;
        public TypeNode next;
        public TypeNode prev;

        public TypeNode(PlaceholderType i, TypeNode n, TypeNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public LinkedListDeque(PlaceholderType x) { // constructor
        sentinel = new TypeNode( x, null, null);
        size = 0;
    }


    public void addFirst(PlaceholderType x) {
        // adds x to the front of the list
        sentinel.next = new TypeNode(x, sentinel.next, sentinel);
        size += 1;
    }

    public void addLast(PlaceholderType x) {
        // add an item to the end of a list
        size += 1;

        last.next = new TypeNode(x,null, last); //should change n to sentinel? for it to loop?
        last = last.next;

    }

    public PlaceholderType getFirst() {
        // returns the first item in the list
        return sentinel.next.item;
    }

    public PlaceholderType removeFirst() {
        PlaceholderType first = getFirst();
        PlaceholderType removedNode = first;
        if (first == null) {
            return null;
        }
        size--;
        first = first.next;
    }


    public int size() { //change this to private?
        return size;
    }

    public boolean isEmpty() {
        // returns true if deque is empty, false otherwise
        return sentinel.next == null;
    }

    public void printDeque() {

    }





}
