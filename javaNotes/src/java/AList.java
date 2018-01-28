/** Array based list.
 *  @author Josh Hug
 */

// index:  0  1
// items: [5, 9, 0...]
    // size: 2

    //invariants: things that are always true about our data structure
    // addlast: the next item we want to add will go into position size
    // the num of items in the list should be size

    // getlast: the item we want to return is in position size - 1
public class AList<Item> {
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

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size + 1);
        }
        items[size] = x;
        size++;
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {

        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {

        return items[i];
    }

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
} 
