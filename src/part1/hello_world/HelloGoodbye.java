package part1.hello_world;

public class HelloGoodbye {

    public static void main(String[] args) {
        if (args.length <= 1) return;
        System.out.printf("Hello %s and %s.\n", args[0], args[1]);
        System.out.printf("Goodbye %s and %s.", args[1], args[0]);
    }
}
