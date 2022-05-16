package part1.queues_stacks.stack;

import java.util.EmptyStackException;

/**
 * Performance:
 * 16 bytes - object overhead
 * 8 bytes - reference to array
 * 24 bytes - array overhead
 * 8 bytes * array size - array objects
 * 4 bytes - int N
 * <p>
 * Doesn't include String, which the client owns
 *
 * @param <T> - values in stack
 */
public class DynamicArrayStack<T> implements Stack<T> {

    private static final int DEFAULT_SIZE = 8;
    private T[] stack;
    private int N;

    @SuppressWarnings("unchecked")
    public DynamicArrayStack() {
        stack = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return stack[N-1];
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T item = stack[--N];
        stack[N] = null;
        if (N > 0 && N == stack.length / 4) resize(stack.length / 2);
        return item;
    }

    @Override
    public void push(T t) {
        if (N == stack.length) resize(2 * stack.length);
        stack[N++] = t;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int size) {
        T[] newStack = (T[]) new Object[size];
        System.arraycopy(stack, 0, newStack, 0, Math.min(size, stack.length));
        stack = newStack;
    }
}
