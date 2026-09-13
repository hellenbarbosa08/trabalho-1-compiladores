public class Main {

    public static void main(String[] args) {

        String input = """
            let a = 67 + 0;
            let b = 21 + 2;
            print a + b;
            """;

        Parser parser = new Parser(input.getBytes());

        parser.parse();

        Interpretador interpretador =
            new Interpretador(parser.output());

        interpretador.run();
    }
}