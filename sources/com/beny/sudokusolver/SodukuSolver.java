package com.beny.sudokusolver;

public class SodukuSolver {
    public static boolean solve(int[][] puzzle) {
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (puzzle[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (isSafe(i, j, k, puzzle)) {
                            puzzle[i][j] = k;
                            if (solve(puzzle)) {
                                return true;
                            }
                            puzzle[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSafe(int row, int col, int num, int[][] puzzle) {
        for (int x = 0; x <= 8; x++) {
            if (puzzle[row][x] == num) {
                return false;
            }
        }
        for (int x2 = 0; x2 <= 8; x2++) {
            if (puzzle[x2][col] == num) {
                return false;
            }
        }
        int startRow = row - (row % 3);
        int startCol = col - (col % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
