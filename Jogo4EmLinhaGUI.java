import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Jogo4EmLinhaGUI {
    static final int LINHAS = 6;
    static final int COLUNAS = 7;
    static final int TAMANHO_CELULA = 100;

    char[][] tabuleiro = new char[LINHAS][COLUNAS];
    char jogadorAtual = 'X';

    JFrame frame;
    JPanel painelTabuleiro;
    JButton[] botoesColuna;
    JPanel[][] celulas;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Jogo4EmLinhaGUI().iniciarJogo();
        });
    }

    public void iniciarJogo() {
        inicializarTabuleiro();

        frame = new JFrame("4 em Linha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(COLUNAS * TAMANHO_CELULA, (LINHAS + 1) * TAMANHO_CELULA);
        frame.setLayout(new BorderLayout());

        painelTabuleiro = new JPanel();
        painelTabuleiro.setLayout(new GridLayout(LINHAS, COLUNAS));
        celulas = new JPanel[LINHAS][COLUNAS];

        // Adicionar células ao painel
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                celulas[i][j] = new JPanel();
                celulas[i][j].setBackground(Color.WHITE);
                celulas[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                painelTabuleiro.add(celulas[i][j]);
            }
        }

        // Botões de coluna
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, COLUNAS));
        botoesColuna = new JButton[COLUNAS];
        for (int i = 0; i < COLUNAS; i++) {
            botoesColuna[i] = new JButton("↓");
            botoesColuna[i].setFont(new Font("Arial", Font.PLAIN, 24));
            botoesColuna[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int coluna = Integer.parseInt(e.getActionCommand());
                    realizarJogada(coluna);
                }
            });
            botoesColuna[i].setActionCommand(String.valueOf(i));
            painelBotoes.add(botoesColuna[i]);
        }

        frame.add(painelTabuleiro, BorderLayout.CENTER);
        frame.add(painelBotoes, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void inicializarTabuleiro() {
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    public void realizarJogada(int coluna) {
        for (int i = LINHAS - 1; i >= 0; i--) {
            if (tabuleiro[i][coluna] == ' ') {
                tabuleiro[i][coluna] = jogadorAtual;
                atualizarTabuleiro();
                if (verificarVitoria()) {
                    String jogadorVencedor = (jogadorAtual == 'X') ? "Jogador 1" : "Jogador 2";
                    JOptionPane.showMessageDialog(frame, jogadorVencedor + " venceu!");
                    inicializarTabuleiro();
                    atualizarTabuleiro();
                }
                mudarJogador();
                break;
            }
        }
    }

    public void atualizarTabuleiro() {
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                if (tabuleiro[i][j] == 'X') {
                    celulas[i][j].setBackground(Color.MAGENTA);
                } else if (tabuleiro[i][j] == 'O') {
                    celulas[i][j].setBackground(Color.YELLOW);
                } else {
                    celulas[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    public boolean verificarVitoria() {
        // Verificar horizontais
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS - 3; j++) {
                if (tabuleiro[i][j] != ' ' && tabuleiro[i][j] == tabuleiro[i][j + 1] && tabuleiro[i][j] == tabuleiro[i][j + 2] && tabuleiro[i][j] == tabuleiro[i][j + 3]) {
                    return true;
                }
            }
        }

        // Verificar verticais
        for (int i = 0; i < LINHAS - 3; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                if (tabuleiro[i][j] != ' ' && tabuleiro[i][j] == tabuleiro[i + 1][j] && tabuleiro[i][j] == tabuleiro[i + 2][j] && tabuleiro[i][j] == tabuleiro[i + 3][j]) {
                    return true;
                }
            }
        }

        // Verificar diagonais
        for (int i = 0; i < LINHAS - 3; i++) {
            for (int j = 0; j < COLUNAS - 3; j++) {
                if (tabuleiro[i][j] != ' ' && tabuleiro[i][j] == tabuleiro[i + 1][j + 1] && tabuleiro[i][j] == tabuleiro[i + 2][j + 2] && tabuleiro[i][j] == tabuleiro[i + 3][j + 3]) {
                    return true;
                }
            }
        }

        for (int i = 3; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS - 3; j++) {
                if (tabuleiro[i][j] != ' ' && tabuleiro[i][j] == tabuleiro[i - 1][j + 1] && tabuleiro[i][j] == tabuleiro[i - 2][j + 2] && tabuleiro[i][j] == tabuleiro[i - 3][j + 3]) {
                    return true;
                }
            }
        }

        return false;
    }

    public void mudarJogador() {
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
    }
}
