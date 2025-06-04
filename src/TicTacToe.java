public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            if (i > 0) {
                System.out.println("---+---+---");
            }
            for (int j = 0; j < 3; j++) {
                if (j > 0) {
                    System.out.print("|");
                }
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean placeMark(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }
        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws java.io.IOException {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        TicTacToe game = new TicTacToe();
        while (true) {
            game.printBoard();
            System.out.println("Player " + game.currentPlayer + ", enter your move (row and column): ");
            String line = reader.readLine();
            if (line == null) break;
            String[] parts = line.trim().split(" ");
            if (parts.length != 2) {
                System.out.println("Please enter row and column separated by space.");
                continue;
            }
            int row, col;
            try {
                row = Integer.parseInt(parts[0]);
                col = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid numbers.");
                continue;
            }
            if (!game.placeMark(row, col)) {
                System.out.println("This move is not valid");
                continue;
            }
            if (game.checkWin()) {
                game.printBoard();
                System.out.println("Player " + game.currentPlayer + " wins!");
                break;
            }
            if (game.isBoardFull()) {
                game.printBoard();
                System.out.println("The game is a tie!");
                break;
            }
            game.changePlayer();
        }
    }
}
