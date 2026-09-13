public class Parser {

    private Scanner scanner;
    private Token tokenAtual;

    private String output = "";

    public Parser(byte[] input) {
        scanner = new Scanner(input);
        tokenAtual = scanner.nextToken();
    }

    private void proximoToken() {
        tokenAtual = scanner.nextToken();
    }

    private void match(TokenType tipo) {
        if (tokenAtual.type == tipo) {
            proximoToken();
        } else {
            throw new Error("Erro de sintaxe");
        }
    }

    private void gerar(String comando) {
        output += comando + "\n";
    }

    public String output() {
        return output;
    }

    private void number() {
        gerar("push " + tokenAtual.lexeme);
        match(TokenType.NUMBER);
    }

    private void term() {

        if (tokenAtual.type == TokenType.NUMBER) {

            number();

        } else if (tokenAtual.type == TokenType.IDENT) {

            gerar("push " + tokenAtual.lexeme);
            match(TokenType.IDENT);

        } else {

            throw new Error("Erro de sintaxe");
        }
    }

    private void expr() {
        term();
        oper();
    }

    private void oper() {

        if (tokenAtual.type == TokenType.PLUS) {

            match(TokenType.PLUS);
            term();
            gerar("add");
            oper();

        } else if (tokenAtual.type == TokenType.MINUS) {

            match(TokenType.MINUS);
            term();
            gerar("sub");
            oper();

        } else if (tokenAtual.type == TokenType.MULT) {

            match(TokenType.MULT);
            term();
            gerar("mul");
            oper();

        } else if (tokenAtual.type == TokenType.DIV) {

            match(TokenType.DIV);
            term();
            gerar("div");
            oper();
        }
    }

    private void letStatement() {

        match(TokenType.LET);

        String nome = tokenAtual.lexeme;

        match(TokenType.IDENT);
        match(TokenType.EQ);

        expr();

        gerar("pop " + nome);

        match(TokenType.SEMICOLON);
    }

    private void printStatement() {

        match(TokenType.PRINT);

        expr();

        gerar("print");

        match(TokenType.SEMICOLON);
    }

    private void statement() {

        if (tokenAtual.type == TokenType.LET) {

            letStatement();

        } else if (tokenAtual.type == TokenType.PRINT) {

            printStatement();

        } else {

            throw new Error("Erro de sintaxe");
        }
    }

    private void statements() {

        while (tokenAtual.type != TokenType.EOF) {
            statement();
        }
    }

    public void parse() {
        statements();
    }
}