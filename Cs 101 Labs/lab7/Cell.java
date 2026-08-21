package lab7;

public class Cell {
    private boolean isMn;
    private boolean isRvld;
    private boolean isFlgd;
    private int nghbrMnCnt;

    public Cell() {
        isMn = false;
        isRvld = false;
        isFlgd = false;
        nghbrMnCnt = 0;
    }

    public void reveal() {
        if (!isFlgd) {
            isRvld = true;
        }
    }

    public void toggleFlag() {
        if (!isRvld) {
            isFlgd = !isFlgd;
        }
    }

    public boolean isMine() {
        return isMn;
    }

    public void setMine(boolean mn) {
        this.isMn = mn;
    }

    public boolean isRevealed() {
        return isRvld;
    }

    public boolean isFlagged() {
        return isFlgd;
    }

    public int getNeighborMineCount() {
        return nghbrMnCnt;
    }

    public void setNeighborMineCount(int cnt) {
        this.nghbrMnCnt = cnt;
    }

    public String display() {
        if (isFlgd) {
            return MinesweeperGame.getFlgSym();
        }
        if (!isRvld) {
            return MinesweeperGame.getHddnSym();
        }
        if (isMn) {
            return MinesweeperGame.getMnSym();
        }
        if (nghbrMnCnt > 0) {
            return String.valueOf(nghbrMnCnt);
        }
        return MinesweeperGame.getMtySym();
    }
}