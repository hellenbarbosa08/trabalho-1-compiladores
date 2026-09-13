import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Interpretador {

    private String[] comandos;

    private Stack<Integer> pilha = new Stack<>();

    private Map<String, Integer> variaveis = new HashMap<>();

    public Interpretador(String input) {
        comandos = input.split("\n");
    }

    public void run() {

        for (String linha : comandos) {

            linha = linha.trim();

            if (linha.isEmpty()) {
                continue;
            }

            String[] partes = linha.split(" ");

            String comando = partes[0];

            switch (comando) {

                case "push":

                    String valor = partes[1];

                    if (variaveis.containsKey(valor)) {
                        pilha.push(variaveis.get(valor));
                    } else {
                        pilha.push(Integer.parseInt(valor));
                    }

                    break;

                case "pop":

                    String nome = partes[1];

                    variaveis.put(nome, pilha.pop());

                    break;

                case "add":

                    int b = pilha.pop();
                    int a = pilha.pop();

                    pilha.push(a + b);

                    break;

                case "sub":

                    b = pilha.pop();
                    a = pilha.pop();

                    pilha.push(a - b);

                    break;

                case "mul":

                    b = pilha.pop();
                    a = pilha.pop();

                    pilha.push(a * b);

                    break;

                case "div":

                    b = pilha.pop();
                    a = pilha.pop();

                    pilha.push(a / b);

                    break;

                case "print":

                    System.out.println(pilha.pop());

                    break;
            }
        }
    }
}