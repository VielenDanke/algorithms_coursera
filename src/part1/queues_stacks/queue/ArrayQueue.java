package part1.queues_stacks.queue;

public class ArrayQueue<T> implements Queue<T> {

    private static final int DEFAULT_SIZE = 8;
    private T[] queue;
    private int N;
    private int R;

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        queue = (T[]) new Object[DEFAULT_SIZE];
        N = 0;
        R = 0;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public void enqueue(T t) {
        if (N == queue.length) resize(queue.length * 2);
        queue[N++] = t;
    }

    @Override
    public T dequeue() {
        T item = queue[R++];
        if (N == R) {
            N = 0;
            R = 0;
            resize(DEFAULT_SIZE);
        } else if (N - R <= queue.length / 4) {
            resize(queue.length / 2);
            N %= queue.length;
            R %= queue.length;
        }
        return item;
    }

    @SuppressWarnings("unchecked")
    private void resize(int size) {
        T[] newQueue = (T[]) new Object[size];
        System.arraycopy(queue, 0, newQueue, 0, Math.min(size, queue.length));
        queue = newQueue;
    }
}
