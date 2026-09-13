public class Main {

    public static void main(String[] args) {

        String input = """
            let a = 50 + 33 - 1;
            let b = 65 + 7;
            print a + b + 4;
            """;

        Parser parser = new Parser(input.getBytes());

        parser.parse();
    }
}