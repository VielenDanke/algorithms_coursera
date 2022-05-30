package part1.sorting.interview_questions;

import java.util.Arrays;
import java.util.Random;

public class ShufflingLinkedList {

    private static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    private static class Pair implements Comparable<Pair> {
        int num;
        int val;

        Pair(int num, int val) {
            this.num = num;
            this.val = val;
        }

        @Override
        public int compareTo(Pair other) {
            return num - other.num;
        }
    }

    public static void shuffle(Node head) {
        int N = 0;
        Node temp = head;
        while (temp != null) {
            N++;
            temp = temp.next;
        }
        temp = head;
        int M = 0;
        Random random = new Random();
        Pair[] pairs = new Pair[N];
        while (temp != null) {
            Pair pair = new Pair(random.nextInt(), temp.val);
            pairs[M++] = pair;
            temp = temp.next;
        }
        Arrays.sort(pairs);

        temp = head;

        while (temp != null) {
            temp.val = pairs[--M].val;
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(5, new Node(3, new Node(4, new Node(7))));
        ShufflingLinkedList.shuffle(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
