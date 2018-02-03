
public class ArrayDeque<PlaceholderType> {
    // placeholderType allows us to not immediately define type as int or str.
    private int size;
    private int firstIndex;
    private int lastIndex;
    PlaceholderType[] myArrayDeque;

    public ArrayDeque() { // constructor for ArrayDeque
        myArrayDeque = (PlaceholderType[]) new Object[8];
        firstIndex = 0;
        lastIndex = myArrayDeque.length - 1;
    }

    public void addFirst(PlaceholderType x) {
        if (size == myArrayDeque.length) {
            resize(); // resize my array here
        }
        size++;
    }

    public void addLast(PlaceholderType x) {
        if (size == myArrayDeque.length) {
            resize(); // resize my array here
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void resize() {

    }

}

// create a generic array with size 8 full of nulls
// array is circular
// when the front of the array is full, the addFirst gets added to the last

// when the whole array is full, resize

// keep track of the index of first and last