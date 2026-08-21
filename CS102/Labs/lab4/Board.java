import java.util.*;

public class Board {

    private int[][] board;
    private int size;

    public Board(int playerNumber, int size) {
        this.size = size;
        board = new int[size][size];
        Random rand = new Random();

        board[0][0] = 1;
        board[0][size - 1] = 2;
        if (playerNumber > 2)
            board[size - 1][0] = 3;
        if (playerNumber > 3)
            board[size - 1][size - 1] = 4;

        board[0][size / 2] = 5;
        board[size / 2][0] = 5;
        board[size / 2][size - 1] = 5;
        board[size - 1][size / 2] = 5;

        for (int i = 0; i < 6;) {
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);

            if (checkBetween(x, y)) {
                board[x][y] = 5;
                i++;
            }
        }

        for (int i = 0; i < 11;) {
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);

            if (board[x][y] == 0) {
                board[x][y] = rand.nextBoolean() ? 6 : 7;
                i++;
            }
        }
        //8→ 9← 10↑ 11↓
        for(int i=0;i<3;){
            int x = rand.nextInt(size);
            int y = rand.nextInt(size); 
            int direction= rand.nextInt(8,12);

            if (checkBetween(x, y)) {
                board[x][y] = direction;
                i++;
            }
        }
    }

    public boolean checkBetween(int x, int y) {
        if (board[x][y] != 0){
            return false;}

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i;
                int ny = y + j;

                if (nx < 0 || ny < 0 || nx >= size || ny >= size) {
                    continue;
                }
                if (board[nx][ny] >= 1 && board[nx][ny] <= 5) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getCell(int x, int y) {
        return board[x][y];
    }

    public void setCell(int x, int y, int val) {
        board[x][y] = val;
    }

    public int getSize() {
        return size;
    }

    public int getNumberOfElement(String type) {
        int count = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (type.equals("player") && (cell >= 1 && cell <= 4))
                    count++;
                if (type.equals("pickableObject") && (cell == 6 || cell == 7))
                    count++;
            }
        }
        return count;
    }
}
