package edu.hw1;

public final class Task8 {
    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (var i = 0; i < 8; i++) {
            for (var j = 0; j < 8; j++) {
                if (board[i][j] == 1) {
                    if ((i >= 2 && j >= 1 && board[i-2][j-1] == 1) ||
                        (i >= 2 && j <= 6 && board[i-2][j+1] == 1) ||
                        (i <= 5 && j >= 1 && board[i+2][j-1] == 1) ||
                        (i <= 5 && j <= 6 && board[i+2][j+1] == 1) ||
                        (i >= 1 && j >= 2 && board[i-1][j-2] == 1) ||
                        (i <= 6 && j >= 2 && board[i+1][j-2] == 1) ||
                        (i >= 1 && j <= 5 && board[i-1][j+2] == 1) ||
                        (i <= 6 && j <= 5 && board[i+1][j+2] == 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
