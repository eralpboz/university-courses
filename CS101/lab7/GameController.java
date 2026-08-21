package lab7;

public class GameController {
    private Board brd;
    private int gmeScre;
    private int flgPlcd;
    private boolean gmOvr;

    public GameController() {
        brd = new Board();
        gmeScre = 0;
        flgPlcd = 0;
        gmOvr = false;
    }

    /*
     * handleReveal(int row, int col): Attempts to reveal the cell at (row, col). If
     * the cell is already revealed or flagged, does nothing. If a mine is hit, sets
     * gameOver to
     * true and calls board.revealAllMines() . Otherwise increments score by the
     * number of newly revealed cells.
     */
    public void handleReveal(int rw, int cl) {
        Cell c = brd.getCell(rw, cl);
        if (c == null || c.isRevealed() || c.isFlagged()) {
            return;
        }

        int prvScre = currntSfeCnt();

        if (c.isMine()) {
            gmOvr = true;
            brd.revealAllMines();
        } else {
            brd.revealCell(rw, cl);

            int nwScre = currntSfeCnt() - prvScre;
            gmeScre += nwScre;
        }
    }
/*score */
    private int currntSfeCnt() {
        int cnt = 0;
        Cell[][] tGrid = brd.getGrid();
        for (int a = 0; a < MinesweeperGame.getRws(); a++) {
            for (int b = 0; b < MinesweeperGame.getCls(); b++) {
                if (tGrid[a][b].isRevealed() && !tGrid[a][b].isMine()) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public boolean isGameOver() {
        return gmOvr || brd.allSafeCellsRevealed();
    }

    public boolean isVictory() {
        return !gmOvr && brd.allSafeCellsRevealed();
    }

    public Board getBoard() {
        return brd;
    }

    public int getFlagsPlaced() {
        return flgPlcd;
    }

    public int getMinesRemaining() {
        return MinesweeperGame.getTtlMns() - flgPlcd;
    }

    public int getScore() {
        return gmeScre;
    }

    public void handleFlag(int rw, int cl) {
        Cell c = brd.getCell(rw, cl);
        if (c == null || c.isRevealed()) {
            return;
        }

        brd.flagCell(rw, cl);

        if (c.isFlagged()) {
            flgPlcd++;
        } else {
            flgPlcd--;
            if (flgPlcd < 0) {
                flgPlcd = 0;
            }
        }
    }
}