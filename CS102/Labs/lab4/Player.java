public class Player {

    private String name;
    private int id;
    private int row, col;
    private int points;
    private boolean alive;

    public Player(String name, int id, int row, int col) {
        this.name = name;
        this.id = id;
        this.row = row;
        this.col = col;
        this.points = 0;
        this.alive = true;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getPoints() {
        return points;
    }

    public boolean isActive() {
        return alive;
    }

    public void setRow(int r) {
        row = r;
    }

    public void setCol(int c) {
        col = c;
    }

    public void eleminate() {
        alive = false;
    }

    public void addPoints(int p) {
        points += p;
    }

    public String toString() {
        return name + " (" + points + " pts)";
    }
}
