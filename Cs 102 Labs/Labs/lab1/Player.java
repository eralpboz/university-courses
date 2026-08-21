import java.util.Random;

public class Player {
    private String name;
    private int id;
    private int row;
    private int col;
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

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void eleminate() {
        this.alive = false;
    }

    public void addPoints(int p) {
        this.points += p;
    }

    public String toString() {
        return "Player " + id + " " + name + "'s point is: " + points;
    }

    public void move(Board board, String direction, int number, Game game) {
        Random rand = new Random();
        int randomX=0;
        int randomY=0;
        boolean didHit = false;
        int newRow = row;
        int newCol = col;
        int cell;
        int wantedRow = 0;
        int wantedCol = 0;
        boolean x=true;
        if (direction.equals("north")||direction.equals("n")) {
            wantedRow = row - number;
            wantedCol = col;
        }
        if (direction.equals("south")||direction.equals("s")) {
            wantedRow = row + number;
            wantedCol = col;
        }
        if (direction.equals("east")||direction.equals("e")) {
            wantedRow = row;
            wantedCol = col + number;
        }
        if (direction.equals("west")||direction.equals("w")) {
            wantedRow = row;
            wantedCol = col - number;
        }

        for (int i = 0; i < number; i++) {
            if (direction.equals("north")||direction.equals("n")) {
                newRow--;

            }
            if (direction.equals("south")||direction.equals("s")) {
                newRow++;

            }
            if (direction.equals("east")||direction.equals("e")) {
                newCol++;

            }
            if (direction.equals("west")||direction.equals("w")) {
                newCol--;

            }
            if (newRow < 0 || newRow >= board.getSize() || newCol < 0 || newCol >= board.getSize()) {
                System.out.println("You can't go out of map. Try different way or step.");
                return;
            }
            cell = board.getCell(newRow, newCol);

            if (cell == 5) {

                System.out.println("You hit a block! Try different way or step.");
                didHit = true;
                return;

            }

            if (cell >= 1 && cell <= 4 && cell != this.id) {
                if (newRow == wantedRow && newCol == wantedCol) {
                    System.out.println("\nYou ate a player!");
                    int eatenId = cell;
                    Player eatenPlayer = null;

                    for (int j = 0; j < game.getPlayerNumber(); j++) {
                        Player p = game.getPlayers().get(j);
                        if (p.getId() == eatenId) {
                            eatenPlayer = p;
                            
                        }
                    }                   
                    if (eatenPlayer != null) {
                        eatenPlayer.eleminate(); 
                        game.removePlayer(eatenPlayer); 
                        System.out.println("Player " +eatenPlayer.getName() + " has been eliminated!");
                    }

                    
                    board.setCell(row, col, 0);
                    board.setCell(newRow, newCol, id);
                    row = newRow;
                    col = newCol;
                    addPoints(5);

                    return;
                } else {
                    System.out.println("You hit another player! Try different way or step.");
                    didHit = true;
                    return;
                }
            }

        }
        newRow = row;
        newCol = col;
        for (int i = 0; i < number && !(didHit); i++) {
            if (direction.equals("north")||direction.equals("n")) {
                newRow--;
            }
            if (direction.equals("south")||direction.equals("s")) {
                newRow++;
            }
            if (direction.equals("east")||direction.equals("e")) {
                newCol++;
            }
            if (direction.equals("west")||direction.equals("w")) {
                newCol--;
            }
        }
        cell = board.getCell(newRow, newCol);

        if (cell == 6) {
            System.out.println("1 point object collected.");
            
            addPoints(1);
        }
        if (cell == 7) {
            System.out.println("2 point object collected.");
            
            addPoints(2);

        }
        if (cell == 8){
            
            System.out.println("Teleport object collected.");
            while(x){
            randomX = rand.nextInt(0,board.getSize());
            randomY = rand.nextInt(0,board.getSize());
            if(board.checkBetween(randomX,randomY)){
                x=false;

            }
        }
        }
        if (!(didHit)) {
            if(!x){
                board.setCell(row, col, 0);
                board.setCell(randomX, randomY, id);
                board.setCell(newRow, newCol, 0);
                row = randomX;
                col = randomY;
            }else{
            board.setCell(row, col, 0);
            board.setCell(newRow, newCol, id);
            row = newRow;
            col = newCol;}
        }
    }

}
