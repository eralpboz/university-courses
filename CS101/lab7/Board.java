package lab7;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private Cell[][] grd;

    public Board() {
        grd = new Cell[MinesweeperGame.getRws()][MinesweeperGame.getCls()];
        initializeGrid();
        placeMines();
        computeNeighborCounts();
    }

    public void flagCell(int rw, int cl) {
        if (rw >= 0 && rw < MinesweeperGame.getRws() && cl >= 0 && cl < MinesweeperGame.getCls()) {
            grd[rw][cl].toggleFlag();
        }
    }

    public void computeNeighborCounts() {
        for (int a = 0; a < MinesweeperGame.getRws(); a++) {
            for (int b = 0; b < MinesweeperGame.getCls(); b++) {

                if (!grd[a][b].isMine()) {
                    int mnCnt = 0;
                    ArrayList<Cell> nghbrs = getNeighbors(a, b);

                    for (int k = 0; k < nghbrs.size(); k++) {
                        if (nghbrs.get(k).isMine()) {
                            mnCnt++;
                        }
                    }
                    grd[a][b].setNeighborMineCount(mnCnt);
                }
            }
        }
    }

    public boolean revealCell(int rw, int cl) {
        Cell c = grd[rw][cl];

        if (c.isMine()) {
            return false;
        }

        if (!c.isRevealed() && !c.isFlagged()) {

            if (c.getNeighborMineCount() == 0) {
                floodReveal(rw, cl);
            } else {
                c.reveal();
            }
        }
        return true;
    }

    public void placeMines() {

        Random rnd = new Random();

        int idx = 0;

        while (idx < MinesweeperGame.getTtlMns()) {
            int rndR = rnd.nextInt(MinesweeperGame.getRws());
            int rndC = rnd.nextInt(MinesweeperGame.getCls());
            if (!grd[rndR][rndC].isMine()) {
                grd[rndR][rndC].setMine(true);
                idx++;
            }

        }

    }

    public ArrayList<Cell> getNeighbors(int rw, int cl) {
        ArrayList<Cell> nghbrs = new ArrayList<>();
        int[] dX = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dY = { -1, 0, 1, -1, 1, -1, 0, 1 };

        int i = 0;
        while (i < 8) {
            int nRw = rw + dY[i];
            int nCl = cl + dX[i];

            if (nRw >= 0 && nRw < MinesweeperGame.getRws() && nCl >= 0 && nCl < MinesweeperGame.getCls()) {
                nghbrs.add(grd[nRw][nCl]);
            }
            i++;
        }
        return nghbrs;
    }

    public boolean allSafeCellsRevealed() {
        int a = 0;
        while (a < MinesweeperGame.getRws()) {
            int b = 0;
            while (b < MinesweeperGame.getCls()) {
                if (!grd[a][b].isMine() && !grd[a][b].isRevealed()) {
                    return false;
                }
                b++;
            }
            a++;
        }
        return true;
    }

    public Cell getCell(int rw, int cl) {
        if (rw >= 0 && rw < MinesweeperGame.getRws() && cl >= 0 && cl < MinesweeperGame.getCls()) {
            return grd[rw][cl];
        }
        return null;
    }

    public Cell[][] getGrid() {
        return grd;
    }

    public void floodReveal(int rw, int cl) {
        // Güvenlik kontrolü
        if (rw < 0 || rw >= MinesweeperGame.getRws() || cl < 0 || cl >= MinesweeperGame.getCls())
            return;

        Cell startCell = grd[rw][cl];
        if (startCell.isRevealed() || startCell.isFlagged() || startCell.isMine())
            return;

        ArrayList<int[]> queue = new ArrayList<>();
        queue.add(new int[] { rw, cl });
        startCell.reveal();

        int queueIndex = 0;
        while (queueIndex < queue.size()) {
            int[] pos = queue.get(queueIndex++);
            int r = pos[0];
            int c = pos[1];

            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr == 0 && dc == 0)
                        continue;

                    int nr = r + dr;
                    int nc = c + dc;

                    // Sınır kontrolünde getRws() ve getCls() kullanıldı
                    if (nr < 0 || nr >= MinesweeperGame.getRws() || nc < 0 || nc >= MinesweeperGame.getCls())
                        continue;

                    // grid yerine grd kullanıldı
                    Cell neighbor = grd[nr][nc];
                    if (neighbor.isRevealed() || neighbor.isMine() || neighbor.isFlagged())
                        continue;

                    neighbor.reveal();
                    if (neighbor.getNeighborMineCount() == 0) {
                        queue.add(new int[] { nr, nc });
                    }
                }
            }
        }
    }

    public void revealAllMines() {
        int a = 0;
        while (a < MinesweeperGame.getRws()) {
            int b = 0;
            while (b < MinesweeperGame.getCls()) {
                if (grd[a][b].isMine()) {
                    grd[a][b].reveal();
                }
                b++;
            }
            a++;
        }
    }

    public void initializeGrid() {
        for (int a = 0; a < MinesweeperGame.getRws(); a++) {
            for (int b = 0; b < MinesweeperGame.getCls(); b++) {
                grd[a][b] = new Cell();
            }
        }
    }
}