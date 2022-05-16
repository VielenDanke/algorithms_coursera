package part1.queues_stacks.stack;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Performance:
 * 16 bytes - object overhead
 * 8 bytes - inner class extra overhead
 * 8 bytes - reference to String
 * 8 bytes - reference to Node
 * <p>
 * Doesn't include String, which the client owns
 *
 * @param <T> - values in stack
 */
public class LinkedStack<T> implements Stack<T>, Iterable<T> {

    private class LinkedStackIterator<Z> implements Iterator<Z> {

        private Node<Z> head;

        public LinkedStackIterator(Node<Z> head) {
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public Z next() {
            Z item = head.item;
            head = head.next;
            return item;
        }
    }

    private class Node<S> {
        S item;
        Node<S> next;
    }

    private Node<T> first;

    @Override
    public Iterator<T> iterator() {
        return new LinkedStackIterator<>(first);
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return first.item;
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T toReturn = first.item;
        first = first.next;
        return toReturn;
    }

    @Override
    public void push(T val) {
        Node<T> oldFirst = first;
        first = new Node<>();
        first.item = val;
        first.next = oldFirst;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }
}
