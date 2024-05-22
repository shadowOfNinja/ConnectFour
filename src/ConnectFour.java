import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConnectFour {

    Board board;
    int boardWidth = 700;
    int boardHeight = 750;
    char[] buttonNames = {'A','B','C','D','E','F','G'};
    char[][] boardPosition = new char[7][6];

    JFrame frame = new JFrame("Connect Four");
    JPanel buttonPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    char playerRed = 'R';
    char playerYellow = 'Y';
    char currentPlayer = playerRed;

    public ConnectFour() {

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // code for adding input buttons on top of the display
        buttonPanel.setLayout(new GridLayout(1, 7));
        buttonPanel.setBackground(Color.BLUE);
        frame.add(buttonPanel, BorderLayout.NORTH);

        // code for setting up the board
        boardPanel.setLayout(new BorderLayout());
        boardPanel.setBackground(Color.BLUE);
        boardPanel.setForeground(Color.BLACK);
        frame.add(boardPanel, BorderLayout.CENTER);

        this.board = new Board(boardPosition);
        boardPanel.add(board);

        drawBoard();

        board.repaint();

    }

    private void drawBoard() {
        for (int row = 0; row < 7; row++) {
            JButton button = new JButton();
            
            buttonPanel.add(button);
            button.setBackground(Color.darkGray);
            button.setForeground(Color.white);
            button.setFont(new Font("Arial", Font.BOLD, 50));
            button.setText(String.valueOf(buttonNames[row]));
            button.setSize(50, 50);
            button.setFocusable(false);

            // Add button listener
            // On click: get the top-most Y digit and set it to the current colour
            // Then, check for win state
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //JButton button = (JButton) e.getSource();
                    System.out.println("Clicked a button " + button.getText());
                    if (board.addPieceToRow(button.getText(), currentPlayer)) {
                        board.repaint();

                        // Check for win / tie
                        if (board.winCondition(currentPlayer)) {
                            System.out.println(currentPlayer + " wins!!");
                            int selection = JOptionPane.showConfirmDialog (null, "Play another game?","Play again?",JOptionPane.YES_NO_OPTION);
                            if (selection == JOptionPane.YES_OPTION) {
                                // Reset board with the next player
                                board.resetBoard();
                            }
                            else {
                                // terminate application
                                System.exit(0);
                            }
                        }
                        else {
                            System.out.println("No winner yet");
                            // check for tie ie: only 42 possible turns
                            if (board.tieCondition()) {
                                System.out.println("Tie!!");
                                int selection = JOptionPane.showConfirmDialog (null, "Play another game?","Play again?",JOptionPane.YES_NO_OPTION);
                                if (selection == JOptionPane.YES_OPTION) {
                                    // Reset board with the next player
                                    board.resetBoard();
                                }
                                else {
                                    // terminate application
                                    System.exit(0);
                                }
                            }
                        }
                        // swap current player
                        currentPlayer = currentPlayer == playerRed ? playerYellow : playerRed;
                    }

                }
            });
        }
    }
}
