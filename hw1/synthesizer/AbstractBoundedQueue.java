package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue {

    protected int fillCount;
    protected int capacity;
    public int capacity() {
        return fillCount;
    }
    public int fillCount() {
        return capacity;
    }
//    public boolean isEmpty()
//    public boolean isFull()
//    public abstract T peek();
//    public abstract T dequeue();
//    public abstract void enqueue(T x);

}