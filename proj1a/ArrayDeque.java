
public class ArrayDeque<T> {
    // placeholderType allows us to not immediately define type as int or str.
    private int size;
    private int firstIndex;
    private int lastIndex;
    private T[] myArrayDeque;

    public ArrayDeque() { // constructor for ArrayDeque
        myArrayDeque = (T[]) new Object[8];
        firstIndex = 0;
        lastIndex = 0;
    }

    public void addFirst(T x) {
        if (size == 0) {
            firstIndex = 0;
            lastIndex = 0;
            myArrayDeque[0] = x;
            return;
        }
        if (size == myArrayDeque.length) {
            resizeUp(); // resize my array here
        }
        if (firstIndex == 0) {
            firstIndex = myArrayDeque.length - 1;
        } else {
            firstIndex--;
        }
        myArrayDeque[firstIndex] = x;
        size++;

    }

    public void addLast(T x) {
        if (size == 0) {
            firstIndex = 0;
            lastIndex = 0;
            myArrayDeque[0] = x;
            return;
        }
        if (size == myArrayDeque.length) {
            resizeUp(); // resize my array here
        }
        if (lastIndex == myArrayDeque.length - 1) {
            lastIndex = 0;
        } else {
            lastIndex++;
        }
        myArrayDeque[lastIndex] = x;
        size++;
    }

    public T get(int index) {
        return myArrayDeque[(firstIndex + index) % myArrayDeque.length];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < myArrayDeque.length; i++) {
            System.out.print(myArrayDeque[i] + " ");
        }
    }

    public T removeFirst() {
        T removedNode = myArrayDeque[firstIndex];
        myArrayDeque[firstIndex] = null;
        if (firstIndex == myArrayDeque.length - 1) {
            firstIndex = 0;
        } else {
            firstIndex++;
        }
        size--;
        if (size < myArrayDeque.length / 4) {
            resizeDown();
        }
        return removedNode;
    }

    public T removeLast() {
        T removedNode = myArrayDeque[lastIndex];
        myArrayDeque[lastIndex] = null;
        if (lastIndex == 0) {
            lastIndex = myArrayDeque.length - 1;
        } else {
            lastIndex--;
        }
        size--;
        if (size < myArrayDeque.length / 4) {
            resizeDown();
        }
        return removedNode;
    }

    private void resizeUp() {
        T[] resizedArray = (T[]) new Object[myArrayDeque.length * 2];
        // copy array into new array
        int sizeOfFirstCopy = myArrayDeque.length - firstIndex;
        System.arraycopy(myArrayDeque, firstIndex, resizedArray, 0, sizeOfFirstCopy);
        System.arraycopy(myArrayDeque, 0, resizedArray, sizeOfFirstCopy, size - sizeOfFirstCopy);
        myArrayDeque = resizedArray;
        firstIndex = 0;
        lastIndex = size - 1;
    }

    private void resizeDown() {
        T[] resizedArray = (T[]) new Object[myArrayDeque.length / 2];
//        int sizeOfFirstCopy = myArrayDeque.length - firstIndex;
        if (lastIndex < firstIndex) {
            int sizeOfFirstCopy = myArrayDeque.length - firstIndex;
            System.arraycopy(myArrayDeque, firstIndex, resizedArray, 0, sizeOfFirstCopy);
            System.arraycopy(myArrayDeque, 0, resizedArray, sizeOfFirstCopy, size - sizeOfFirstCopy);

        } else {
            System.arraycopy(myArrayDeque, firstIndex, resizedArray, 0, size);
        }
        myArrayDeque = resizedArray;
        firstIndex = 0;
        lastIndex = size - 1;
    }

}

// create a generic array with size 8 full of nulls
// array is circular
// when the front of the array is full, the addFirst gets added to the last

// when the whole array is full, resize

// keep track of the index of first and last