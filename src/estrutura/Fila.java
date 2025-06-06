package estrutura;

public class Fila {
    private No inicio;
    private No fim;

    public Fila() {
        this.inicio = null;
        this.fim = null;
    }

    public void enfileirar(String dado) {
        No novo = new No(dado);
        if (fim != null) {
            fim.proximo = novo;
        }
        fim = novo;
        if (inicio == null) {
            inicio = novo;
        }
    }

    public String desenfileirar() {
        if (inicio == null) return null;
        String dado = inicio.dado;
        inicio = inicio.proximo;
        if (inicio == null) fim = null;
        return dado;
    }

    public String exibirFila() {
        StringBuilder conteudoFila = new StringBuilder();
        No atual = inicio;
        int pos = 1;
        while (atual != null) {
            conteudoFila.append(pos).append(" - ").append(atual.dado).append("\n");
            atual = atual.proximo;
            pos++;
        }
        return conteudoFila.length() > 0 ? conteudoFila.toString() : "Fila vazia.";
    }
}
