package lab4;
/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 04
*/
import java.util.Scanner;

public class Lab01_Q1 {
    public static void main(String[] args) {
        Scanner inner= new Scanner(System.in);
        boolean isTrueValue=true;
        int inputNumber=0;
        while(isTrueValue){
            System.out.print("Enter an odd number for the hourglass pattern: ");
            inputNumber=inner.nextInt();

            if(inputNumber%2==1&&inputNumber>0){
                isTrueValue=false;
            }else{
                System.out.println("Invalid input, try again. ");
            }
        }

        for (int a = 0; a < inputNumber / 2; a++) {
            for (int b = a; b > 0; b--) {
                System.out.print(" ");
            }
            for (int j = inputNumber - 2 * a; j > 0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 0; i < inputNumber / 2 + 1; i++) {
            
            for (int b = 0; b < inputNumber / 2 - i; b++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

}
