public class Solution {
    public boolean isValidSudoku(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        HashSet<Character> sets = new HashSet<Character>();
        
        // check row
        for (int row = 0; row < rows; row++) {
            sets.clear();
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == '.') {
                    continue;
                }
                if (sets.contains(board[row][col])) {
                    return false;
                }
                if (board[row][col] >= '1' 
                    && board[row][col] <= '9') {
                    sets.add(board[row][col]);
                } else {
                    return false;
                }
            }
        }
        
        // check col
        for (int col = 0; col < cols; col++) {
            sets.clear();
            for (int row = 0; row < rows; row++) {
                if (board[row][col] == '.') {
                    continue;
                }
                if (sets.contains(board[row][col])) {
                    return false;
                }
                if (board[row][col] >= '1' 
                    && board[row][col] <= '9') {
                    sets.add(board[row][col]);
                } else {
                    return false;
                }
            }
        } 
        
        // check block
        for (int bigRow = 0; bigRow < rows/3; bigRow++) {
            for (int bigCol = 0; bigCol < cols/3; bigCol++) {
                sets.clear();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int row = bigRow * 3 + i;
                        int col = bigCol * 3 + j;
                        if (board[row][col] == '.') {
                            continue;
                        }
                        if (sets.contains(board[row][col])) {
                            return false;
                        }
                        if (board[row][col] >= '1' 
                            && board[row][col] <= '9') {
                            sets.add(board[row][col]);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}