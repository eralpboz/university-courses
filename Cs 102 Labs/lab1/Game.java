import java.util.*;

public class Game {

    private ArrayList<Player> players;;
    private int playerNumber;
    private Scanner in;
    ArrayList<Player> sorted;
    private int size;
    

    public Game() {
        in = new Scanner(System.in);
        players = new ArrayList<>();
    }

    public void start() {
        System.out.print("Enter map size: ");
        size= in.nextInt();
        this.setPlayer(size);
        sorted = new ArrayList<>(players);
        Board gameBoard = new Board(this, size);
        
        String movement;
        String direction;
        int numberOfStepInt;
        boolean didMove;
        int row;
        int col;
        boolean x = false;
        int playerCount2 = playerNumber;
        for (int i = 0; !(x); i++) {
            didMove = true;
            row = players.get(i % playerNumber).getRow();
            col = players.get(i % playerNumber).getCol();
            while (didMove) {
                System.out.println("\nIt's player " + players.get(i % playerNumber).getId() + " "
                        + players.get(i % playerNumber).getName() + "'s turn");
                gameBoard.printBoard(this);
                for (int a = 0; a < players.size(); a++) {
                    System.out.println(players.get(a));
                }

                System.out.print(
                        "\nEnter which direction(north(n), south(s), east(e), west(w)) you will go, then put a dot and enter how many steps you will take: ");
                movement = in.next();
                direction = movement.substring(0, movement.indexOf("."));
                if (!(direction.equals("north")) && !(direction.equals("south")) && !(direction.equals("east"))
                        && !(direction.equals("west")) && !(direction.equals("n")) && !(direction.equals("s"))
                        && !direction.equals("e") && !direction.equals("w")) {
                    System.out.println("Invalid input try again");
                }
                numberOfStepInt = Integer.valueOf(movement.substring(movement.indexOf(".") + 1));

                players.get(i % playerNumber).move(gameBoard, direction, numberOfStepInt, this);
                if (gameBoard.getCell(row, col) == players.get(i % playerNumber).getId()) {

                } else {
                    didMove = false;
                    if (playerCount2 > players.size()) {
                        playerCount2 = players.size();
                        i = i - 1; 
                    }
                }

            }
            x = isGameOver(gameBoard);
        }
        this.finishGame();

    }

    public void setPlayer(int boardSize) {
        boolean x = true;

        while (x) {
            System.out.print("Enter number of players (2-4): ");

            if (in.hasNextInt()) {
                playerNumber = in.nextInt();
                in.nextLine();

                if (playerNumber >= 2 && playerNumber <= 4) {
                    x = false;
                } else {
                    System.out.println("Invalid range! Please enter between 2 and 4.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                in.next();
            }
        }
        players = new ArrayList<>();

        for (int i = 1; i <= playerNumber; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = in.nextLine();

            int startRow = 0, startCol = 0;

            if (i == 1) {
                startRow = 0;
                startCol = 0;
            } else if (i == 2) {
                startRow = 0;
                startCol= boardSize-1;
            } else if (i == 3) {
                startRow = boardSize-1;
                startCol = 0;
            } else if (i == 4) {
                startRow = boardSize-1;
                startCol = boardSize-1;
            }

            Player p = new Player(name, i, startRow, startCol);
            players.add(p);
        }
        Collections.shuffle(players);

        System.out.println("\nPlayer Order");
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ". " + players.get(i).getName());
        }

    }

    public boolean isGameOver(Board board) {
        int pickableObjectCounter = board.getNumberOfElement("pickableObject");
        int playerCounter = board.getNumberOfElement("player");
        if (pickableObjectCounter == 0 || playerCounter <= 1) {
            return true;
        } else 
            return false;
        
    }

    public void finishGame() {

        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = i + 1; j < sorted.size(); j++) {
                if (sorted.get(i).getPoints() < sorted.get(j).getPoints()) {
                    Player temp = sorted.get(i);
                    sorted.set(i, sorted.get(j));
                    sorted.set(j, temp);
                }
            }
        }

        System.out.println("\n---------------");
        System.out.println("   Game Over");
        System.out.println("---------------\n");

        for (int i = 0; i < sorted.size(); i++) {
            System.out.println((i + 1) + ". " + sorted.get(i).getName() +
                    " - " + sorted.get(i).getPoints() + " points");
        }
        System.out.println("\nWinner: " + sorted.get(0).getName() +
                " (" + sorted.get(0).getPoints() + " points)");
    }

    public int getPlayerNumber() {
        playerNumber = players.size();
        return playerNumber;
    }

    public void removePlayer(Player p) {
        players.remove(p);
        playerNumber = players.size();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

}
