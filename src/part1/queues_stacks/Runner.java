package part1.queues_stacks;

import part1.queues_stacks.queue.ArrayQueue;

public class Runner {

    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<>();

        for (int i = 0; i < 100; i++) {
            queue.enqueue("abc");
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
