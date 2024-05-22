import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Board extends JPanel {
    private int turns;
    private char[][] pieces;

    public Board(char[][] pieces) {
        this.pieces = pieces;
        this.setBackground(Color.BLUE);
        this.turns = 0;
        repaint();
    }

    public int getfirstAvailableRow(int column) {
        // loop through the column in the 2D array, and return the first available spot
        for (int i = 5; i >= 0; i--) {
            if (this.pieces[column][i] != 'R' && this.pieces[column][i] != 'Y') {
                return i;
            }
        }

        return -1;
    }

    public boolean addPieceToRow(String column, char currentPlayer) {
        int searchColumn = -1;
        int rowToPlace = -1;

        switch (column) {
            case "A":
                searchColumn = 0;
                break;
            case "B":
                searchColumn = 1;
                break;
            case "C":
                searchColumn = 2;
                break;
            case "D":
                searchColumn = 3;
                break;
            case "E":
                searchColumn = 4;
                break;
            case "F":
                searchColumn = 5;
                break;
            case "G":
                searchColumn = 6;
                break;
        }

        rowToPlace = getfirstAvailableRow(searchColumn);
        if (rowToPlace != -1) {
            System.out.println("Adding a piece to " + searchColumn + " " + rowToPlace);
            this.pieces[searchColumn][rowToPlace] = currentPlayer;
            return true;
        }

        return false;
    }

    public boolean tieCondition () {
        if (this.turns == 42) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean winCondition(char currentPlayer) {
        Boolean winner = false;
        this.turns++;

        // Search all verical blocks of 4
        System.out.println("Searching verticals");
        for (int rows = 0; rows < 5; rows++) {
            System.out.println("Row " + rows);
            for (int iteration = 0; iteration < 3; iteration++) {
                if (this.pieces[rows][0+iteration] == currentPlayer && this.pieces[rows][1+iteration] == currentPlayer && this.pieces[rows][2+iteration] == currentPlayer && this.pieces[rows][3+iteration] == currentPlayer) {
                    setWinner(rows, 0+iteration, rows, 1+iteration, rows, 2+iteration, rows, 3+iteration);
                    winner = true;
                    break;
                }
            }
        }

        // Search all horizontal blocks of 4
        System.out.println("Searching horizontals");
        for (int iteration = 0; iteration < 4; iteration++) {
            for (int columns = 0; columns < 6; columns++) {
                if (this.pieces[0+iteration][columns] == currentPlayer && this.pieces[1+iteration][columns] == currentPlayer && this.pieces[2+iteration][columns] == currentPlayer && this.pieces[3+iteration][columns] == currentPlayer) {
                    winner = true;
                    this.pieces[0+iteration][columns] = 'G';
                    this.pieces[1+iteration][columns] = 'G';
                    this.pieces[2+iteration][columns] = 'G';
                    this.pieces[3+iteration][columns] = 'G';
                    setWinner(0+iteration, columns, 1+iteration, columns, 2+iteration, columns, 3+iteration, columns);
                    break;
                }
            }
        }

        // TO DO: Think of a loop to search for this, rather than manually search all spots
        // from chat GPT
        /*
            int ROWS = 6;
            int COLS = 7;
        // Check diagonals from bottom-left to top-right
        for (int r = ROWS - 1; r >= 3; r--) {
            for (int c = 0; c <= COLS - 4; c++) {
                if (board[r][c] != ' ' && board[r][c] == board[r - 1][c + 1] && board[r][c] == board[r - 2][c + 2] && board[r][c] == board[r - 3][c + 3]) {
                    return board[r][c];
                }
            }
        }

        // Check diagonals from top-left to bottom-right
        for (int r = 0; r <= ROWS - 4; r++) {
            for (int c = 0; c <= COLS - 4; c++) {
                if (board[r][c] != ' ' && board[r][c] == board[r + 1][c + 1] && board[r][c] == board[r + 2][c + 2] && board[r][c] == board[r + 3][c + 3]) {
                    return board[r][c];
                }
            }
        }
         */

        // Search diagonals
        System.out.println("Searching diagonals");
        if (this.pieces[0][0] == currentPlayer && this.pieces[1][1] == currentPlayer && this.pieces[2][2] == currentPlayer && this.pieces[3][3] == currentPlayer) {
            setWinner(0,0,1,1,2,2,3,3);
            winner = true;
        }
        else if (this.pieces[1][1] == currentPlayer && this.pieces[2][2] == currentPlayer && this.pieces[3][3] == currentPlayer && this.pieces[4][4] == currentPlayer) {
            setWinner(1,1,2,2,3,3,4,4);
            winner = true;
        }
        else if (this.pieces[2][2] == currentPlayer && this.pieces[3][3] == currentPlayer && this.pieces[4][4] == currentPlayer && this.pieces[5][5] == currentPlayer) {
            setWinner(2, 2, 3, 3, 4, 4, 5, 5);
            winner = true;
        } 
        else if (this.pieces[0][1] == currentPlayer && this.pieces[1][2] == currentPlayer && this.pieces[2][3] == currentPlayer && this.pieces[3][4] == currentPlayer) {
            setWinner(0,1,1,2,2,3,3,4);
            winner = true;
        }
        else if (this.pieces[1][2] == currentPlayer && this.pieces[2][3] == currentPlayer && this.pieces[3][4] == currentPlayer && this.pieces[4][5] == currentPlayer) {
            setWinner(1,2,2,3,3,4,4,5);
            winner = true;
        }
        else if (this.pieces[0][2] == currentPlayer && this.pieces[1][3] == currentPlayer && this.pieces[2][4] == currentPlayer && this.pieces[3][5] == currentPlayer) {
            setWinner(0,2,1,3,2,4,3,5);
            winner = true;
        }
        else if (this.pieces[1][0] == currentPlayer && this.pieces[2][1] == currentPlayer && this.pieces[3][2] == currentPlayer && this.pieces[4][3] == currentPlayer) {
            setWinner(1,0,2,1,3,2,4,3);
            winner = true;
        }
        else if (this.pieces[2][1] == currentPlayer && this.pieces[3][2] == currentPlayer && this.pieces[4][3] == currentPlayer && this.pieces[5][4] == currentPlayer) {
            setWinner(2,1,3,2,4,3,5,4);
            winner = true;
        }
        else if (this.pieces[3][2] == currentPlayer && this.pieces[4][3] == currentPlayer && this.pieces[5][4] == currentPlayer && this.pieces[6][5] == currentPlayer) {
            setWinner(3,2,4,3,5,4,6,5);
            winner = true;
        }
        else if (this.pieces[2][0] == currentPlayer && this.pieces[3][1] == currentPlayer && this.pieces[4][2] == currentPlayer && this.pieces[5][3] == currentPlayer) {
            setWinner(2,0,3,1,4,2,5,3);
            winner = true;
        }
        else if (this.pieces[3][1] == currentPlayer && this.pieces[4][2] == currentPlayer && this.pieces[5][3] == currentPlayer && this.pieces[6][4] == currentPlayer) {
            setWinner(3,1,4,2,5,3,6,4);
            winner = true;
        }
        else if (this.pieces[3][0] == currentPlayer && this.pieces[4][1] == currentPlayer && this.pieces[5][2] == currentPlayer && this.pieces[6][3] == currentPlayer) {
            setWinner(3,0,4,1,5,2,6,3);
            winner = true;
        }
        else if (this.pieces[6][0] == currentPlayer && this.pieces[5][1] == currentPlayer && this.pieces[4][2] == currentPlayer && this.pieces[5][3] == currentPlayer) {
            setWinner(6,0,5,1,4,2,5,3);
            winner = true;
        }
        else if (this.pieces[5][1] == currentPlayer && this.pieces[4][2] == currentPlayer && this.pieces[3][3] == currentPlayer && this.pieces[2][4] == currentPlayer) {
            setWinner(5,1,4,2,3,3,2,4);
            winner = true;
        }
        else if (this.pieces[4][2] == currentPlayer && this.pieces[3][3] == currentPlayer && this.pieces[2][4] == currentPlayer && this.pieces[1][5] == currentPlayer) {
            setWinner(4,2,3,3,2,4,1,5);
            winner = true;
        } 
        else if (this.pieces[6][1] == currentPlayer && this.pieces[5][2] == currentPlayer && this.pieces[4][3] == currentPlayer && this.pieces[3][4] == currentPlayer) {
            setWinner(6,1,5,2,4,3,3,4);
            winner = true;
        }
        else if (this.pieces[5][2] == currentPlayer && this.pieces[4][3] == currentPlayer && this.pieces[3][4] == currentPlayer && this.pieces[2][5] == currentPlayer) {
            setWinner(5,2,4,3,3,4,2,5);
            winner = true;
        }
        else if (this.pieces[6][2] == currentPlayer && this.pieces[5][3] == currentPlayer && this.pieces[4][4] == currentPlayer && this.pieces[3][5] == currentPlayer) {
            setWinner(6,2,5,3,4,4,3,5);
            winner = true;
        }
        else if (this.pieces[5][0] == currentPlayer && this.pieces[4][1] == currentPlayer && this.pieces[3][2] == currentPlayer && this.pieces[2][3] == currentPlayer) {
            setWinner(5,0,4,1,3,2,2,3);
            winner = true;
        }
        else if (this.pieces[4][1] == currentPlayer && this.pieces[3][2] == currentPlayer && this.pieces[2][3] == currentPlayer && this.pieces[1][4] == currentPlayer) {
            setWinner(4,1,3,2,2,3,1,4);
            winner = true;
        }
        else if (this.pieces[3][2] == currentPlayer && this.pieces[2][3] == currentPlayer && this.pieces[1][4] == currentPlayer && this.pieces[0][5] == currentPlayer) {
            setWinner(3,2,2,3,1,4,0,5);
            winner = true;
        }
        else if (this.pieces[4][0] == currentPlayer && this.pieces[3][1] == currentPlayer && this.pieces[2][2] == currentPlayer && this.pieces[1][3] == currentPlayer) {
            setWinner(4,0,3,1,2,2,1,3);
            winner = true;
        }
        else if (this.pieces[3][1] == currentPlayer && this.pieces[2][2] == currentPlayer && this.pieces[1][3] == currentPlayer && this.pieces[0][4] == currentPlayer) {
            setWinner(3,1,2,2,1,3,0,4);
            winner = true;
        }
        else if (this.pieces[3][0] == currentPlayer && this.pieces[2][1] == currentPlayer && this.pieces[1][2] == currentPlayer && this.pieces[0][3] == currentPlayer) {
            setWinner(3,0,2,1,1,2,0,3);
            winner = true;
        }
        
        return winner;
    }

    public void setWinner(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this.pieces[x1][y1] = 'G';
        this.pieces[x2][y2] = 'G';
        this.pieces[x3][y3] = 'G';
        this.pieces[x4][y4] = 'G';
        this.repaint();
    }

    public void resetBoard() {
        for (int rows = 0; rows < 7; rows++) {
            for (int columns = 0; columns < 6; columns++) {
                this.pieces[rows][columns] = 'W';
            }
        }
        this.turns = 0;
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x, y = 0;

        for (int rows = 0; rows < 7; rows++) {
            for (int cols = 0; cols < 6; cols++) {

                // Determine colour of the piece based on value
                switch (String.valueOf(this.pieces[rows][cols])) {
                    case "R":
                        // red
                        g.setColor(Color.RED);
                        break;
                    case "Y":
                        // yellow
                        g.setColor(Color.YELLOW);
                        break;
                    case "G":
                        // green - for winning piececs
                        g.setColor(Color.GREEN);
                        break;
                    default:
                        // white
                        g.setColor(Color.WHITE);
                        break;
                }
                x = ((rows + 1) * 20) + (rows * 75);
                y = (cols + 1) * 20 + (cols * 70);
                g.fillOval(x, y, 75, 75);
                //System.out.println("Paint a circle for X: " + x + " Y: " + y);
            }
        }
    }

}