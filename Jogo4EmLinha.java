import java.util.Scanner;

public class Jogo4EmLinha {

    static char[][] tabuleiro = new char[6][7]; // Tabuleiro 6x7
    static char jogadorAtual = 'X'; // Jogador inicial

    public static void main(String[] args) {
        inicializarTabuleiro();
        exibirTabuleiro();

        while (true) {
            int coluna = obterJogada();
            if (realizarJogada(coluna)) {
                exibirTabuleiro();
                if (verificarVitoria()) {
                    System.out.println("Jogador " + jogadorAtual + " venceu!");
                    break;
                }
                mudarJogador();
            }
        }
    }

    // Inicializa o tabuleiro com espaços vazios
    public static void inicializarTabuleiro() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    // Exibe o tabuleiro na tela
    public static void exibirTabuleiro() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print("|" + tabuleiro[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    // Obtém a jogada do jogador
    public static int obterJogada() {
        Scanner scanner = new Scanner(System.in);
        int coluna;
        while (true) {
            System.out.print("Jogador " + jogadorAtual + ", escolha uma coluna (0-6): ");
            coluna = scanner.nextInt();
            if (coluna >= 0 && coluna < 7 && tabuleiro[0][coluna] == ' ') {
                break;
            } else {
                System.out.println("Coluna inválida ou cheia. Tente novamente.");
            }
        }
        return coluna;
    }

    // Realiza a jogada no tabuleiro
    public static boolean realizarJogada(int coluna) {
        for (int i = 5; i >= 0; i--) {
            if (tabuleiro[i][coluna] == ' ') {
                tabuleiro[i][coluna] = jogadorAtual;
                return true;
            }
        }
        return false;
    }

    // Verifica se o jogador atual venceu
    public static boolean verificarVitoria() {
        // Verificar horizontais, verticais e diagonais
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (tabuleiro[i][j] == jogadorAtual) {
                    // Verificar horizontal
                    if (j + 3 < 7 && tabuleiro[i][j + 1] == jogadorAtual && tabuleiro[i][j + 2] == jogadorAtual && tabuleiro[i][j + 3] == jogadorAtual)
                        return true;

                    // Verificar vertical
                    if (i + 3 < 6 && tabuleiro[i + 1][j] == jogadorAtual && tabuleiro[i + 2][j] == jogadorAtual && tabuleiro[i + 3][j] == jogadorAtual)
                        return true;

                    // Verificar diagonal (cima-esquerda para baixo-direita)
                    if (i + 3 < 6 && j + 3 < 7 && tabuleiro[i + 1][j + 1] == jogadorAtual && tabuleiro[i + 2][j + 2] == jogadorAtual && tabuleiro[i + 3][j + 3] == jogadorAtual)
                        return true;

                    // Verificar diagonal (cima-direita para baixo-esquerda)
                    if (i + 3 < 6 && j - 3 >= 0 && tabuleiro[i + 1][j - 1] == jogadorAtual && tabuleiro[i + 2][j - 2] == jogadorAtual && tabuleiro[i + 3][j - 3] == jogadorAtual)
                        return true;
                }
            }
        }
        return false;
    }

    // Alterna entre os jogadores
    public static void mudarJogador() {
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
    }
}