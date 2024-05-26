import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConnectFour implements MouseListener, MouseMotionListener{

    Board board;
    int boardWidth = 700;
    int boardHeight = 750;
    char[] buttonNames = {'A','B','C','D','E','F','G'};
    char[][] boardPosition = new char[7][6];

    JFrame frame = new JFrame("Connect Four");
    JPanel buttonPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    char playerRed = 'R';
    char playerRedPreview = 'F';
    char playerYellow = 'Y';
    char playerYellowPreview = 'H';
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

    private String coordinatesToColumn(int x, int y) {
        if (y <= 580) {
            if (x >= 22 && x <= 98) {
                return "A";
            }
            else if (x >= 118 && x <= 193) {
                return "B";
            }
            else if (x >= 214 && x <= 288) {
                return "C";
            }
            else if (x >= 308 && x <= 384) {
                return "D";
            }
            else if (x >= 403 && x <= 477) {
                return "E";
            }
            else if (x >= 498 && x <= 574) {
                return "F";
            }
            else if (x >= 594 && x <= 670) {
                return "G";
            }
        }
        return null;
    }

    private void drawBoard() {
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                String column = coordinatesToColumn(x, y);

                System.out.println("Button clicked at " + x + ", " + y);
                System.out.println("Column " + column);
                // TO DO - add NULL protection here
                if (column != null && board.addPieceToRow(column, currentPlayer)) {
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
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
            }
        
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
            }
        });
        // 'preview' selection feature when mousing over a column
        frame.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                char previewPlayer;

                String column = coordinatesToColumn(x, y);

                if (column != null) {
                    System.out.println("Mouse pointing at column" + column +", first row " + String.valueOf(board.getfirstAvailableRow(column)));
                    if (currentPlayer == playerRed) {
                        previewPlayer = playerRedPreview;
                    }
                    else {
                        previewPlayer = playerYellowPreview;
                    }
                    board.addPieceToRow(column, previewPlayer);
                }
                else {
                    // clear all preview tiles
                    board.clearPreviewPieces();
                }
                
            }
            
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}
