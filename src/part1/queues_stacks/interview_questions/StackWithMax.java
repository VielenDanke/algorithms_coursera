package part1.queues_stacks.interview_questions;

import part1.queues_stacks.stack.LinkedStack;
import part1.queues_stacks.stack.Stack;

import java.util.*;

public class StackWithMax implements Stack<Integer> {
    private final LinkedStack<Integer> stack = new LinkedStack<>();
    private final Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a));

    public Integer max() {
        if (stack.isEmpty()) throw new EmptyStackException();
        return queue.peek();
    }

    @Override
    public Integer peek() {
        return stack.peek();
    }

    @Override
    public Integer pop() {
        queue.poll();
        return stack.pop();
    }

    @Override
    public void push(Integer val) {
        queue.add(val);
        stack.push(val);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
