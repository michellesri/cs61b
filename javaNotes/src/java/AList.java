/** Array based list.
 *  @author Josh Hug
 */

public class AList<Item> implements ListInterface<Item>{
    private Item[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = (Item[]) new Object[100]; //this casts our object to an item.
        size = 0;
    }


    /** resizes the underlying array to the target capacity */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    @Override
    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size + 1);
        }
        items[size] = x;
        size++;
    }

    @Override
    /** Returns the item from the back of the list. */
    public Item getLast() {

        return items[size - 1];
    }

    @Override
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {

        return items[i];
    }

    @Override
    /** Returns the number of items in the list. */
    public int size() {

        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Item removeLast() {
        Item x = getLast();
        items[size - 1] = null;
        size--;
        return x;
    }

    @Override
    // inserts item into given position
    public void insert(Item x, int position) {
        Item[] newItems = (Item[]) new Object[items.length + 1];

        System.arraycopy(items, 0, newItems, 0, position);
        items = newItems;
    }

    public void insertFive(Item x1, Item x2, Item x3) {
        insert(x1, 0);
        insert(x2, 1);
        insert(x3, 2);
    }

    @Override
    // inserts item at the front
    public void addFirst(Item x) {
        insert(x, 0);
    }

    @Override
    // gets an item from the front
    public Item getFirst() {
        return get(0);
    }
} 
