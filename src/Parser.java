public class Parser {

    private Scanner scanner;
    private Token tokenAtual;

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

    private void number() {
        System.out.println("push " + tokenAtual.lexeme);
        match(TokenType.NUMBER);
    }

    private void term() {
        if (tokenAtual.type == TokenType.NUMBER) {
            number();

        } else if (tokenAtual.type == TokenType.IDENT) {
            System.out.println("push " + tokenAtual.lexeme);
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
            System.out.println("add");
            oper();

        } else if (tokenAtual.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();
            System.out.println("sub");
            oper();

        } else if (tokenAtual.type == TokenType.MULT) {
            match(TokenType.MULT);
            term();
            System.out.println("mul");
            oper();

        } else if (tokenAtual.type == TokenType.DIV) {
            match(TokenType.DIV);
            term();
            System.out.println("div");
            oper();
        }
    }

    private void letStatement() {
        match(TokenType.LET);

        String nome = tokenAtual.lexeme;

        match(TokenType.IDENT);
        match(TokenType.EQ);

        expr();

        System.out.println("pop " + nome);

        match(TokenType.SEMICOLON);
    }

    public void parse() {
        letStatement();

        if (tokenAtual.type != TokenType.EOF) {
            throw new Error("Erro de sintaxe");
        }
    }
}