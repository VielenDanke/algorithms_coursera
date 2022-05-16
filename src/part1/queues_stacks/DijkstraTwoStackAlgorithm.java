package part1.queues_stacks;

import java.util.Stack;

public class DijkstraTwoStackAlgorithm {

    private final Stack<Integer> values = new Stack<>();
    private final Stack<Character> operation = new Stack<>();

    public int calculateExpression(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (isExpression(current)) {
                operation.add(current);
            } else if (Character.isDigit(current)) {
                values.add(current - '0');
            } else if (current == ')') {
                Character op = operation.pop();
                int p = values.pop(), q = values.pop(), sum = performOperation(op, p, q);
                values.add(sum);
            }
        }
        return values.pop();
    }


    private boolean isExpression(char expression) {
        return expression == '*' || expression == '/' || expression == '+' || expression == '-';
    }

    private int performOperation(Character operation, int p, int q) {
        return switch (operation) {
            case '+' -> p + q;
            case '-' -> p - q;
            case '*' -> p * q;
            case '/' -> p / q;
            default -> throw new UnsupportedOperationException();
        };
    }

    public static void main(String[] args) {
        DijkstraTwoStackAlgorithm algo = new DijkstraTwoStackAlgorithm();

        System.out.println(algo.calculateExpression("(1+((2+3)*(4*5)))"));
    }
}
