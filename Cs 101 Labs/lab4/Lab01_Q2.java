package lab4;
/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 04
*/
import java.util.Scanner;

public class Lab01_Q2 {
    public static void main(String[] args) {
        Scanner inn = new Scanner(System.in);
        boolean isContinue = true;

        while (isContinue) {
            System.out.print("Enter a string: ");
            String palindromeString = inn.next();

            boolean isPalindrome = true;
            int lengthOfString = palindromeString.length();

            if (palindromeString.equals("0")) {
                isContinue = false;
            }

            for (int a = 0; (a < lengthOfString / 2) && !(palindromeString.equals("0")); a++) {
                if (palindromeString.charAt(a) != palindromeString.charAt(lengthOfString - a - 1)) {
                    isPalindrome = false;
                }
            }
            if (palindromeString.equals("0")) {
                System.out.println("Program terminated. ");
            } else if (isPalindrome) {
                System.out.println("It is a palindrome.");
            } else {
                System.out.println("It is not a palindrome. ");
            }
            isPalindrome = false;
        }
    }
}
