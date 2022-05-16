package part1.queues_stacks.queue;

import java.util.EmptyStackException;

public class LinkedQueue<T> implements Queue<T> {

    private class Node<S> {
        S item;
        Node<S> next;

        public Node(S item) {
            this.item = item;
        }
    }

    private Node<T> first;
    private Node<T> last;

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public void enqueue(T t) {
        Node<T> oldLast = last;
        last = new Node<>(t);
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new EmptyStackException();
        T item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }
}
