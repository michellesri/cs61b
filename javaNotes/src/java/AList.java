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
public class AList {
    private int[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = new int[100]; //arbitrary size
        size = 0;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == items.length) {
            int[] a = new int[size + 1];
            System.arraycopy(items, 0, a, 0, size);
            items = a;
        }
        items[size] = x;
        size++;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {

        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {

        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {

        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {

        int x = getLast();
        size--;
        return x;
    }
} 
