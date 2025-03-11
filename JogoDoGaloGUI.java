import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDoGaloGUI extends JFrame {
    private JButton[][] botoes = new JButton[3][3];
    private char jogadorAtual = 'X';
    private char[][] tabuleiro = new char[3][3];

    public JogoDoGaloGUI() {
        setTitle("Jogo do Galo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        
        inicializarTabuleiro();
        criarBotoes();
        
        setVisible(true);
    }
    
    private void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }
    
    private void criarBotoes() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton(" ");
                botoes[i][j].setFont(new Font("Arial", Font.BOLD, 50));
                botoes[i][j].setFocusPainted(false);
                final int linha = i;
                final int coluna = j;
                botoes[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (tabuleiro[linha][coluna] == ' ') {
                            tabuleiro[linha][coluna] = jogadorAtual;
                            botoes[linha][coluna].setText(String.valueOf(jogadorAtual));
                            if (verificarVencedor()) {
                                JOptionPane.showMessageDialog(null, "Jogador " + jogadorAtual + " venceu!");
                                reiniciarJogo();
                            } else if (tabuleiroCheio()) {
                                JOptionPane.showMessageDialog(null, "Empate!");
                                reiniciarJogo();
                            } else {
                                jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                            }
                        }
                    }
                });
                add(botoes[i][j]);
            }
        }
    }
    
    private boolean verificarVencedor() {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) return true;
            if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) return true;
        }
        if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) return true;
        if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual) return true;
        return false;
    }
    
    private boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') return false;
            }
        }
        return true;
    }
    
    private void reiniciarJogo() {
        jogadorAtual = 'X';
        inicializarTabuleiro();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText(" ");
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JogoDoGaloGUI());
    }
}