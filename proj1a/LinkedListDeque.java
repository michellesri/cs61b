public class LinkedListDeque<T> {
    // placeholderType allows us to not immediately define type as int or str.
    // the first item (if it exists) is at sentinel.next
    private TypeNode sentinel; // variable
    private TypeNode lastSentinel; // last sentinel
    private int size;

    private class TypeNode {
        public T item;
        public TypeNode next;
        public TypeNode prev;

        public TypeNode(T i, TypeNode n, TypeNode p) { //constructor for typenode
            item = i;
            next = n;
            prev = p;
        }
    }

    public LinkedListDeque() { // constructor for deque
        sentinel = new TypeNode(null, null, null);
        lastSentinel = new TypeNode(null, null, sentinel);
        sentinel.next = lastSentinel;

        size = 0;
    }

    public T getRecursive(int index) {
        TypeNode currentNode = sentinel.next;
        return getRecursiveHelper(currentNode, index);
    }

    private T getRecursiveHelper(TypeNode currentNode, int currentIndex) {
        if (currentIndex < 0 || currentIndex > size || currentNode == null) {
            return null;
        } else if (currentIndex == 0) {
            return currentNode.item;
        }
        return getRecursiveHelper(currentNode.next, currentIndex - 1);
    }

    public void addFirst(T x) {
        // adds x to the front of the list
        sentinel.next = new TypeNode(x, sentinel.next, sentinel);
        size += 1;
    }

    public void addLast(T x) {
        // add an item to the end of a list
        size += 1;

        lastSentinel.prev = new TypeNode(x, lastSentinel, lastSentinel.prev);
    }

    public T getFirst() {
        // returns the first item in the list
        return sentinel.next.item;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        TypeNode removedNode = sentinel.next;
        removedNode.next.prev = sentinel;
        sentinel.next = removedNode.next;
        return removedNode.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        TypeNode removedNode = lastSentinel.prev;
        lastSentinel.prev = removedNode.prev;
        removedNode.prev.next = lastSentinel;
        return removedNode.item;
    }

    public T get(int index) {
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
        if (currentNode != null) {
            return;
        }
        while (currentNode != lastSentinel) {
            System.out.print(currentNode.item + " ");
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
