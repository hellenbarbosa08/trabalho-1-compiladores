public class Scanner {

    private byte[] input;
    private int current;

    public Scanner(byte[] input) {
        this.input = input;
    }

    private char peek() {
        if (current < input.length) {
            return (char) input[current];
        }

        return '\0';
    }

    private void advance() {
        if (peek() != '\0') {
            current++;
        }
    }

    private void ignorarEspacos() {
        while (Character.isWhitespace(peek())) {
            advance();
        }
    }

    private Token number() {

        int inicio = current;

        while (Character.isDigit(peek())) {
            advance();
        }

        String numero = new String(
            input,
            inicio,
            current - inicio
        );

        return new Token(TokenType.NUMBER, numero);
    }

    private Token identifier() {

        int inicio = current;

        while (
            Character.isLetterOrDigit(peek()) ||
            peek() == '_'
        ) {
            advance();
        }

        String nome = new String(
            input,
            inicio,
            current - inicio
        );

        
        if (nome.equals("let")) {
            return new Token(TokenType.LET, nome);
        }

        return new Token(TokenType.IDENT, nome);
    }

    public Token nextToken() {

        ignorarEspacos();

        char ch = peek();

     
        if (Character.isLetter(ch) || ch == '_') {
            return identifier();
        }

      
        if (Character.isDigit(ch)) {
            return number();
        }

        switch (ch) {

            case '+':
                advance();
                return new Token(TokenType.PLUS, "+");

            case '-':
                advance();
                return new Token(TokenType.MINUS, "-");

            case '*':
                advance();
                return new Token(TokenType.MULT, "*");

            case '/':
                advance();
                return new Token(TokenType.DIV, "/");

            case '=':
                advance();
                return new Token(TokenType.EQ, "=");

            case ';':
                advance();
                return new Token(TokenType.SEMICOLON, ";");

            case '\0':
                return new Token(TokenType.EOF, "EOF");

            default:
                throw new Error("Erro lexico: " + ch);
        }
    }
}