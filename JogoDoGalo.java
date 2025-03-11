import java.util.Scanner;

public class JogoDoGalo {

    // Definindo a matriz do tabuleiro 3x3
    private static char[][] tabuleiro = new char[3][3];
    private static char jogadorAtual = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializa o tabuleiro com espaços vazios
        inicializarTabuleiro();

        boolean jogoAtivo = true;

        // Loop principal do jogo
        while (jogoAtivo) {
            imprimirTabuleiro();

            // Solicita ao jogador a linha e a coluna para sua jogada
            System.out.println("Jogador " + jogadorAtual + ", escolha a linha e a coluna (0, 1 ou 2): ");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();

            // Verifica se a jogada é válida e executa
            if (movimentoValido(linha, coluna)) {
                tabuleiro[linha][coluna] = jogadorAtual;

                // Verifica se houve vencedor
                if (verificarVencedor()) {
                    imprimirTabuleiro();
                    System.out.println("Jogador " + jogadorAtual + " venceu!");
                    jogoAtivo = false;
                }
                // Verifica se o tabuleiro está cheio (empate)
                else if (tabuleiroCheio()) {
                    imprimirTabuleiro();
                    System.out.println("Empate!");
                    jogoAtivo = false;
                } else {
                    // Troca de jogador
                    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Jogada inválida, tente novamente.");
            }
        }
        scanner.close();
    }

    // Função que inicializa o tabuleiro preenchendo com espaços vazios
    private static void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';  // Preenchendo a matriz com espaços vazios
            }
        }
    }

    // Função que imprime o tabuleiro do jogo no formato da matriz
    private static void imprimirTabuleiro() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " | ");  // Exibe cada célula da matriz
            }
            System.out.println("\n-------------");
        }
    }

    // Função que verifica se a jogada é válida
    private static boolean movimentoValido(int linha, int coluna) {
        return linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ';
    }

    // Função que verifica se há um vencedor, usando a matriz para checar as linhas, colunas e diagonais
    private static boolean verificarVencedor() {
        // Verificando as linhas da matriz
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
                return true;  // Vencedor na linha
            }
        }

        // Verificando as colunas da matriz
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) {
                return true;  // Vencedor na coluna
            }
        }

        // Verificando a diagonal principal
        if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) {
            return true;  // Vencedor na diagonal principal
        }

        // Verificando a diagonal secundária
        if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual) {
            return true;  // Vencedor na diagonal secundária
        }

        return false;
    }

    // Função que verifica se o tabuleiro está cheio (sem espaços vazios)
    private static boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;  // Se encontrar uma célula vazia, o tabuleiro não está cheio
                }
            }
        }
        return true;  // Se não encontrar células vazias, o tabuleiro está cheio
    }
}