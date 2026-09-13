public class Main {

    public static void main(String[] args) {

        String input = "let a = 75 + 1 - 4;";

        Parser parser = new Parser(input.getBytes());

        parser.parse();
    }
}