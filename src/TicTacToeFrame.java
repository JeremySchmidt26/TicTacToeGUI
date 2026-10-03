import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {

    private TicTacToeTile[][] tiles = new TicTacToeTile[3][3];
    private char currentPlayer = 'X';

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLayout(new BorderLayout());

        TicTacToeLogic.clearBoard();

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        BoardListener listener = new BoardListener();

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                tiles[r][c] = new TicTacToeTile(r, c);
                tiles[r][c].setFont(new Font("Arial", Font.BOLD, 60));
                tiles[r][c].addActionListener(listener);
                boardPanel.add(tiles[r][c]);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Goodbye!");
            System.exit(0);
        });

        add(boardPanel, BorderLayout.CENTER);
        add(quitButton, BorderLayout.SOUTH);
    }

    private class BoardListener implements java.awt.event.ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {

            TicTacToeTile tile = (TicTacToeTile) e.getSource();
            int row = tile.getRow();
            int col = tile.getCol();

            if (!TicTacToeLogic.isValidMove(row, col)) {
                JOptionPane.showMessageDialog(null, "Illegal move! Square already taken.");
                return;
            }

            TicTacToeLogic.board[row][col] = String.valueOf(currentPlayer);
            tile.setText(String.valueOf(currentPlayer));

            if (TicTacToeLogic.isWin(String.valueOf(currentPlayer))) {
                JOptionPane.showMessageDialog(null, currentPlayer + " wins!");
                askPlayAgain();
                return;
            }

            if (TicTacToeLogic.isTie()) {
                JOptionPane.showMessageDialog(null, "Tie game!");
                askPlayAgain();
                return;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private void askPlayAgain() {
        int choice = JOptionPane.showConfirmDialog(null, "Play again?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            resetBoard();
        } else {
            JOptionPane.showMessageDialog(null, "Goodbye!");
            System.exit(0);
        }
    }

    private void resetBoard() {
        currentPlayer = 'X';
        TicTacToeLogic.clearBoard();

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                tiles[r][c].setText("");
            }
        }
    }
}
