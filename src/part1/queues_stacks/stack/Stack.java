package part1.queues_stacks.stack;

import part1.queues_stacks.Storage;

public interface Stack<T> extends Storage {

    /**
     *
     * Method return the top of stack
     *
     * O(1) time complexity
     *
     * @return T element of Stack
     */
    T pop();

    /**
     *
     * O(1) time complexity average
     *
     * @param t - value to push to the stack
     */
    void push(T t);

    T peek();

    /**
     *
     * O(1) time complexity
     *
     * @return check if stack is empty
     */
    boolean isEmpty();
}
