public class LinkedListDeque<PlaceholderType> {
    // placeholderType allows us to not immediately define type as int or str.
    // the first item (if it exists) is at sentinel.next
    private TypeNode sentinel; // variable
    private TypeNode lastSentinel; // last sentinel
    private int size;

    private class TypeNode {
        public PlaceholderType item;
        public TypeNode next;
        public TypeNode prev;

        public TypeNode(PlaceholderType i, TypeNode n, TypeNode p) { //constructor for typenode
            item = i;
            next = n;
            prev = p;
        }
    }

    public LinkedListDeque() { // constructor for deque
        sentinel = new TypeNode( null, null, null);
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

        lastSentinel.prev = new TypeNode(x,lastSentinel, lastSentinel.prev);
    }
    public PlaceholderType getFirst() {
        // returns the first item in the list
        return sentinel.next.item;
    }

    public PlaceholderType removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        TypeNode removedNode = sentinel.next;
        removedNode.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return removedNode.item;
    }

    public PlaceholderType removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        TypeNode removedNode = lastSentinel.prev;
        lastSentinel.prev = lastSentinel;
        return removedNode.item;
    }

    public PlaceholderType get(int index) {
        TypeNode currentNode = sentinel.next;
        if (index >= size) {
            return null;
        }
        while (index > 0) {
            currentNode = currentNode.next;
            index--;
        }
        return currentNode.item;
    }

//    for getRecursive, make helper method that uses the nested class


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        // returns true if deque is empty, false otherwise
        return size == 0;
    }

    public void printDeque() {
//      start from sentinel print the item by going through the list
        TypeNode currentNode = sentinel.next;
        while (currentNode != lastSentinel) {
            System.out.println(currentNode.item);
            currentNode = currentNode.next;

        }
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        lld1.addFirst(10);
        lld1.removeFirst();
        lld1.size();
    }

}
