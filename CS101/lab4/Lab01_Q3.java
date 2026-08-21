package lab4;
/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 04
*/
public class Lab01_Q3 {
    public static void main(String[] args) {
        final double GOAL = 1000;

        double studentMoneyAmountA = 100;
        double studentMoneyAmountB = 300;
        double studentMoneyAmountC = 0;

        double increaseA = 50;
        double increaseB = 30;
        double increaseC = 75;

        int winner = 0;

        boolean isGameFinish = true;
        int counter = 1;

        System.out.println("Welcome to the Savings Race! ");
        System.out.println("Target: " + GOAL);

        while (isGameFinish) {
            System.out.println("Month " + counter);
            counter++;
            studentMoneyAmountA += increaseA;
            studentMoneyAmountB += increaseB;
            studentMoneyAmountC += increaseC;

            System.out.println("Student A: " + studentMoneyAmountA + "\t|\tStudent B: " + studentMoneyAmountB
                    + "\t|\tStudent C: " + studentMoneyAmountC);

            if ((studentMoneyAmountA > GOAL)) {
                isGameFinish = false;
                winner = 1;
            }
            if ((studentMoneyAmountB > GOAL)) {
                isGameFinish = false;
                winner = 2;
            }
            if ((studentMoneyAmountC > GOAL)) {
                isGameFinish = false;
                winner = 3;
            }
        }

        System.out.println("\nRace Finished!");
        System.out.println("Total months: " + counter + "\nFinal Balances:");
        System.out.println("Student A: " + studentMoneyAmountA + "\nStudent B: " + studentMoneyAmountB + "\nStudent C: "
                + studentMoneyAmountC);
        System.out.println();
        System.out.println("Winner:");

        if (winner == 1) {
            System.out.println("Student A");
        } else if (winner == 2) {
            System.out.println("Student B");
        } else {
            System.out.println("Student C");
        }
    }
}
