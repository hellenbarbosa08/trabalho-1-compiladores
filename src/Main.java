public class Main {

    public static void main(String[] args) {

        String input = "785*43*0/49";

        Parser p = new Parser(input.getBytes());

        p.parse();
    }
}