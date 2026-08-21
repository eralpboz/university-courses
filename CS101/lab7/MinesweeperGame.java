package lab7;
/*
*Author: Eralp Yiğit Boz (22403188)
*Cs 101 Lab 07
*/
import java.util.Scanner;

public class MinesweeperGame {


    private static int rws = 10;
    private static int cls = 8;
    private static int ttlMns = 12;
    private static String hddnSym = "#";
    private static String mnSym = "O";
    private static String flgSym = ">";
    private static String mtySym = " ";
    private static String vctryMsg = "Victory! You cleared the minefield!";
    private static String dftMsg = "Defeat! You hit a mine!";

    private static GameController gmeCtrl;
    private static Scanner inpt;

    public static void main(String[] args) {
        initializeVariables();
        playGame();
        handleGameEnding();
    }

    public static void setRws(int val) {
        rws = val;
    }

    public static int getCls() {
        return cls;
    }

    public static String getMnSym() {
        return mnSym;
    }

    public static int getRws() {
        return rws;
    }

    public static int getTtlMns() {
        return ttlMns;
    }

    public static String getMtySym() {
        return mtySym;
    }

    public static String getVctryMsg() {
        return vctryMsg;
    }

    public static void setTtlMns(int val) {
        ttlMns = val;
    }

    public static String getHddnSym() {
        return hddnSym;
    }

    public static String getDftMsg() {
        return dftMsg;
    }

    public static String getFlgSym() {
        return flgSym;
    }

    public static void setCls(int val) {
        cls = val;
    }

    public static void initializeVariables() {
        inpt = new Scanner(System.in);
        gmeCtrl = new GameController();
    }

    public static void handlePlayerAction() {
        System.out.print("Action (R=Reveal, F=Flag): ");
        String act = inpt.next().toUpperCase();

        while (!act.equals("R") && !act.equals("F")) {
            System.out.print("Invalid action. Action (R=Reveal, F=Flag): ");
            act = inpt.next().toUpperCase();
        }

        int rwChoice = getValidInput("Enter row (0 to " + (getRws() - 1) + "): ", 0, getRws() - 1);
        int clChoice = getValidInput("Enter column (0 to " + (getCls() - 1) + "): ", 0, getCls() - 1);

        if (act.equals("R")) {
            gmeCtrl.handleReveal(rwChoice, clChoice);
        } else if (act.equals("F")) {
            gmeCtrl.handleFlag(rwChoice, clChoice);
        }
    }

    public static void debugPrintMines() {
        Cell[][] tGrd = gmeCtrl.getBoard().getGrid();
        for (int a = 0; a < getRws(); a++) {
            for (int b = 0; b < getCls(); b++) {
                if (tGrd[a][b].isMine()) {
                    System.out.print(getMnSym() + " ");
                } else {
                    System.out.print(getHddnSym() + " ");
                }
            }
            System.out.println();
        }
    }

    public static void renderBoard() {
        System.out.print("  ");
        int idx = 0;
        while (idx < getCls()) {
            System.out.print(idx + " ");
            idx++;
        }
        System.out.println();

        Cell[][] tGrd = gmeCtrl.getBoard().getGrid();
        int a = 0;
        while (a < getRws()) {
            System.out.print(a + " ");
            int b = 0;
            while (b < getCls()) {
                System.out.print(tGrd[a][b].display() + " ");
                b++;
            }
            System.out.println();
            a++;
        }
    }

    public static int getValidInput(String prmpt, int mn, int mx) {
        int val = -1;
        boolean isVld = false;

        while (!isVld) {
            System.out.print(prmpt);
            if (inpt.hasNextInt()) {
                val = inpt.nextInt();
                if (val >= mn && val <= mx) {
                    isVld = true;
                } else {
                    System.out.println("Invalid input. Try again.");
                }
            } else {
                System.out.println("Invalid input. Try again.");
                inpt.next();
            }
        }
        return val;
    }

    public static void renderGameInformation() {
        System.out.println("\nScore: " + gmeCtrl.getScore() +
                " | Flags: " + gmeCtrl.getFlagsPlaced() +
                " | Mines remaining: " + gmeCtrl.getMinesRemaining());
    }

    public static void playGame() {
        while (!gmeCtrl.isGameOver()) {
            renderGameInformation();
            renderBoard();
            handlePlayerAction();
        }
    }

    public static void handleGameEnding() {
        renderBoard();
        System.out.println();
        if (gmeCtrl.isVictory()) {
            System.out.println(getVctryMsg());
        } else {
            System.out.println(getDftMsg());
        }
        System.out.println("Final Score: " + gmeCtrl.getScore());
        inpt.close();
    }
}