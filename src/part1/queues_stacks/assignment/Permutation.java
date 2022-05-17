package part1.queues_stacks.assignment;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String line = StdIn.readString();
            queue.enqueue(line);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
