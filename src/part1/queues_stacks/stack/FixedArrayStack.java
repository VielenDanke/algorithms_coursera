package part1.queues_stacks.stack;

import java.util.EmptyStackException;

public class FixedArrayStack<T> implements Stack<T> {

    private final T[] stack;
    private int N;

    @SuppressWarnings("unchecked")
    public FixedArrayStack(int size) {
        stack = (T[]) new Object[size];
        N = 0;
    }

    @Override
    public T peek() {
        return stack[N-1];
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = stack[--N];
        stack[N] = null;
        return item;
    }

    @Override
    public void push(T s) {
        stack[N++] = s;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }
}
