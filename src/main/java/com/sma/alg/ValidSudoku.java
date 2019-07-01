package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;

@Tag(dss = Array, references = LeetCode)
public class ValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    boolean[][] row = new boolean[9][9];//if num n is on row i then row[i][num]=true
    boolean[][] col = new boolean[9][9];
    boolean[][] block = new boolean[9][9];

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == '.')
          continue;
        int n = board[i][j] - '1';
        int block_idx = i / 3 * 3 + j / 3;
        if (row[i][n] || col[j][n] || block[block_idx][n])
          return false;
        row[i][n] = col[j][n] = block[block_idx][n] = true;
      }
    }
    return true;
  }
}
