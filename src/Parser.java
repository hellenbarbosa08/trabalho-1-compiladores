public class Parser {

    private byte[] input;
    private int current = 0;

    public Parser(byte[] input) {
        this.input = input;
    }

    public void parse() {
        expr();

        // Verifica se toda a entrada foi consumida
        if (peek() != '\0') {
            throw new Error("Erro de sintaxe");
        }
    }

    private char peek() {
        if (current < input.length) {
            return (char) input[current];
        }

        return '\0';
    }

    private void match(char expected) {
        if (peek() == expected) {
            current++;
        } else {
            throw new Error("Erro de sintaxe");
        }
    }

    private void expr() {
        digit();
        oper();
    }

    private void digit() {
        if (Character.isDigit(peek())) {

            System.out.println("push " + peek());

            match(peek());

        } else {
            throw new Error("Erro de sintaxe");
        }
    }

    private void oper() {

        if (peek() == '+') {

            match('+');
            digit();
            System.out.println("add");
            oper();

        } else if (peek() == '-') {

            match('-');
            digit();
            System.out.println("sub");
            oper();

        } else if (peek() == '*') {

            match('*');
            digit();
            System.out.println("mul");
            oper();

        } else if (peek() == '/') {

            match('/');
            digit();
            System.out.println("div");
            oper();
        }
    }
}
