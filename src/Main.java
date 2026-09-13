public class Main {

    public static void main(String[] args) {

        String input = """
            let a = 16 + 18;
            let b = 225/ 5;
            let c = a * 4;
            print a + b;
            print c - 10;
            """;

        Parser parser = new Parser(input.getBytes());

        parser.parse();

        System.out.println("TRADUCAO:");
        System.out.println(parser.output());

        System.out.println("RESULTADO:");

        Interpretador interpretador =
            new Interpretador(parser.output());

        interpretador.run();
    }
}