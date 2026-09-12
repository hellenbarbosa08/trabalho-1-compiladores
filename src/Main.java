public class Main {

    public static void main(String[] args) {

        String input = "3*1/3";

        Parser p = new Parser(input.getBytes());

        p.parse();
    }
}