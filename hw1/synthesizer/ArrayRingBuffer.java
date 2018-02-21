package synthesizer;

import java.util.Iterator;

// Make sure to make this class and all of its methods public
// Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */

    public ArrayRingBuffer(int capacity) {
        // Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        rb = (T[]) new Object[this.capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TEnqueue the item. increase fillCount and update last.
        if (fillCount == capacity) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount++;

        last = (last + 1) % capacity;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // Dequeue the first item. decrease fillCount and update first
        if (fillCount() == 0) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T temp = rb[first];
            rb[first] = null;
            fillCount--;
            first = (first + 1) % capacity;
            return temp;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (fillCount() == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        // Return the first item. None of your instance variables should change.
        return rb[first];
    }

    //code to support iteration.
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        int nextCounter = 0;

        @Override
        public boolean hasNext() {
            return nextCounter < fillCount;
        }

        @Override
        public T next() {
            T saved = rb[(nextCounter + first) % capacity];
            nextCounter++;
            return saved;
        }
    }



}
