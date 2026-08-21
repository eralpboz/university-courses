import java.util.*;

public class Board {
    private int[][] board;
    private int size;
    Game game;

    // Players=1,2,3,4 / Blocks=5 / Picakble Objects 1pt=6 / Picakble Objects 2pt=7 / Teleport=8

    public Board(Game game, int size) {
        this.size = size;
        Random rand = new Random();
        board = new int[size][size];
        this.game = game;
        int playerNumber = game.getPlayerNumber();

        board[0][0] = 1;
        board[0][size-1] = 2;
        if (playerNumber > 2) {
            board[size-1][0] = 3;
        }
        if (playerNumber > 3) {
            board[size-1][size-1] = 4;
        }
        board[0][size/2] = 5;
        board[size/2][0] = 5;
        board[size/2][size-1] = 5;
        board[size-1][size/2] = 5;

        int randomX;
        int randomY;
        for (int i = 0; i < 6;) {
            randomX = rand.nextInt(size);
            randomY = rand.nextInt(size);
            if (checkBetween(randomX, randomY)) {
                board[randomX][randomY] = 5;
                i++;
            }
        }
        int oneOrTwo;
        for (int i = 0; i < 11;) {
            randomX = rand.nextInt(size);
            randomY = rand.nextInt(size);
            oneOrTwo = rand.nextInt(2);
            if (board[randomX][randomY] == 0) {
                if (oneOrTwo == 0) {
                    board[randomX][randomY] = 6;
                }
                if (oneOrTwo == 1) {
                    board[randomX][randomY] = 7;
                }
                i++;
            }
        }
            for (int i = 0; i < 5;) {
                randomX = rand.nextInt(size);
                randomY = rand.nextInt(size);
                if (checkBetween(randomX, randomY)) {
                    board[randomX][randomY] = 8;
                    i++;
                }   
        }

    }

    public boolean checkBetween(int x, int y) {
        if (board[x][y] != 0) {
            return false;
        }

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

    public void printBoard(Game game) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int cell = getCell(i, j);

                if (cell == 0) {
                    System.out.print(".");
                }
                if (cell == 1) {
                    System.out.print("1");
                }
                if (cell == 2) {
                    System.out.print("2");
                }
                if (cell == 3) {
                    System.out.print("3");
                }
                if (cell == 4) {
                    System.out.print("4");
                }
                if (cell == 5) {
                    System.out.print("X");
                }
                if (cell == 6 || cell == 7) {
                    System.out.print("*");
                }
                if( cell == 8){
                    System.out.print("T");
                }

            }
            System.out.println();
        }
    }

    public int getCell(int x, int y) {
        return board[x][y];
        
    }

    public void setCell(int x, int y, int changeNumber) {
        board[x][y] = changeNumber;
    }

    public int getNumberOfElement(String element) {
        int counter = 0;
        if (element.equals("player")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == 1 || board[i][j] == 2 || board[i][j] == 3 || board[i][j] == 4) {
                        counter++;
                    }
                }
            }
        }
        if (element.equals("pickableObject")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == 7 || board[i][j] == 6) {
                        counter++;
                    }
                }
            }
        }

        return counter;

    }
    public  int getSize(){
        return size;
    }
}