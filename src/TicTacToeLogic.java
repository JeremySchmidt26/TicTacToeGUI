public class TicTacToeLogic {

    public static final int ROW = 3;
    public static final int COL = 3;

    // This stores X/O values separate from the GUI buttons
    public static String[][] board = new String[ROW][COL];

    public static void clearBoard() {
        for (int r = 0; r < ROW; r++)
            for (int c = 0; c < COL; c++)
                board[r][c] = " ";
    }

    public static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    public static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int r = 0; r < ROW; r++)
            if (board[r][0].equals(player) &&
                    board[r][1].equals(player) &&
                    board[r][2].equals(player))
                return true;
        return false;
    }

    private static boolean isColWin(String player) {
        for (int c = 0; c < COL; c++)
            if (board[0][c].equals(player) &&
                    board[1][c].equals(player) &&
                    board[2][c].equals(player))
                return true;
        return false;
    }

    private static boolean isDiagWin(String player) {
        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player))
            return true;

        if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player))
            return true;

        return false;
    }

    public static boolean isTie() {
        for (int r = 0; r < ROW; r++)
            for (int c = 0; c < COL; c++)
                if (board[r][c].equals(" "))
                    return false;
        return true;
    }
}
