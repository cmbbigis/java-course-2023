package edu.hw1;

public final class Task8 {
    private Task8() {

    }

    final private static int N = 8;
    final private static int BOTTOM_BORDER = 0;
    final private static int TOP_BORDER = 7;

    private static boolean isNotOutOfBounds(int i, int j) {
        return i >= BOTTOM_BORDER && i <= TOP_BORDER && j >= BOTTOM_BORDER && j <= TOP_BORDER;
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (var i = 0; i < N; i++) {
            for (var j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    if ((isNotOutOfBounds(i + 2, j - 1) && board[i + 2][j - 1] == 1)
                        || (isNotOutOfBounds(i + 2, j + 1) && board[i + 2][j + 1] == 1)
                        || (isNotOutOfBounds(i - 1, j + 2) && board[i - 1][j + 2] == 1)
                        || (isNotOutOfBounds(i + 1, j + 2) && board[i + 1][j + 2] == 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
