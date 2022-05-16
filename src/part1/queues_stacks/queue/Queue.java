package part1.queues_stacks.queue;

import part1.queues_stacks.Storage;

public interface Queue<T> extends Storage {

    void enqueue(T t);

    T dequeue();
}
