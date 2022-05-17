package part1.queues_stacks.assignment;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final class RandomizedQueueIterator<Z> implements Iterator<Z> {

        private final Z[] queue;
        private int pointer;

        public RandomizedQueueIterator(Z[] array, int size) {
            queue = (Z[]) new Object[size];
            System.arraycopy(array, 0, queue, 0, size);
            pointer = 0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return pointer < queue.length;
        }

        @Override
        public Z next() {
            if (!hasNext()) throw new NoSuchElementException();
            return queue[pointer++];
        }
    }

    private static final int DEFAULT_SIZE = 8;
    private Item[] queue;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[DEFAULT_SIZE];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        queue[size++] = item;
        if (size == queue.length) {
            resize(queue.length * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        checkIfQueueIsEmpty();
        int indexToRemove = StdRandom.uniform(size);
        Item element = queue[indexToRemove];
        final int newSize = size - 1;
        if (newSize > indexToRemove)
            System.arraycopy(queue, indexToRemove + 1, queue, indexToRemove, newSize - indexToRemove);
        size = newSize;
        queue[size] = null;
        return element;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        checkIfQueueIsEmpty();
        return queue[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<>(queue, size);
    }

    private void resize(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize];
        System.arraycopy(queue, 0, newArray, 0, Math.min(newSize, queue.length));
        queue = newArray;
    }

    private void checkIfQueueIsEmpty() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> integers = new RandomizedQueue<>();

        integers.enqueue(1);
        integers.enqueue(2);
        integers.enqueue(3);
        System.out.println(integers.sample());
        System.out.println(integers.dequeue());
        System.out.println(integers.size());
        System.out.println(integers.isEmpty());
    }

}
