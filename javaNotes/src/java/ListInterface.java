public interface ListInterface<Item>{
    public void addLast(Item x);

    /** Returns the item from the back of the list. */
    public Item getLast();

    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i);

    /** Returns the number of items in the list. */
    public int size();

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Item removeLast();

    /** inserts item into given position */
    public void insert(Item x, int position);

    /** inserts item at the front */
    public void addFirst(Item x);

    /** gets an item from the front */
    public Item getFirst();

    default public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}