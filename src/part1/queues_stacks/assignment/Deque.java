package part1.queues_stacks.assignment;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static final class DequeIterator<Z> implements Iterator<Z> {

        private Node<Z> node;
        private int size;

        public DequeIterator(Node<Z> node, int size) {
            this.node = node;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Z next() {
            if (!hasNext()) throw new NoSuchElementException();
            size--;
            Z item = node.item;
            node = node.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static final class Node<T> {
        T item;
        Node<T> next;

        public Node(T item) {
            this.item = item;
        }
    }

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkIfArgumentIsNull(item);
        Node<Item> newNode = new Node<>(item);
        if (isEmpty()) {
            insertIntoEmptyQueue(newNode);
        } else {
            Node<Item> oldFirst = first;
            first = newNode;
            first.next = oldFirst;
        }
        size++;
    }

    private void insertIntoEmptyQueue(Node<Item> newNode) {
        first = newNode;
        last = newNode;
        first.next = last;
    }

    // add the item to the back
    public void addLast(Item item) {
        checkIfArgumentIsNull(item);
        Node<Item> newNode = new Node<>(item);
        if (isEmpty()) {
            insertIntoEmptyQueue(newNode);
        } else {
            last.next = newNode;
            last = last.next;
        }
        size++;
    }

    // remove and return the item from the front

    public Item removeFirst() {
        checkIfQueueIsEmpty();
        Item item = first.item;
        size--;
        if (size == 0) {
            first = null;
            last = null;
        } else {
            first = first.next;
        }
        return item;
    }
    // remove and return the item from the back

    public Item removeLast() {
        checkIfQueueIsEmpty();
        Item item = last.item;
        size--;
        if (size == 0) {
            first = null;
            last = null;
        } else {
            Node<Item> temp = first;
            while (temp.next != null && temp.next != last) {
                temp = temp.next;
            }
            temp.next = null;
            last = temp;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator<>(first, size());
    }

    private void checkIfArgumentIsNull(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void checkIfQueueIsEmpty() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    // return an iterator over items in order from front to back
    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("abc");
        deque.addFirst("Asd");
        deque.addFirst("bla");
        deque.addLast("last");
        deque.addLast("very last");
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.size());
        System.out.println(deque.isEmpty());

        for (int i = 0; i < 100; i++) {
            deque.addFirst(String.valueOf('a' + i));
            deque.addLast(String.valueOf('a' + i));
        }

        for (int i = 0; i < 30; i++) {
            deque.removeFirst();
            if (i % 2 == 0) {
                deque.removeLast();
            }
        }

        Iterator<String> iterator = deque.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
