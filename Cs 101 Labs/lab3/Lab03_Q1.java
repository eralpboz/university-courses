/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 03
*/
package lab3;

import java.util.Scanner;

public class Lab03_Q1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final double ImaxPrice = 18;
        final double RegularPrice = 10;
        final double ThreeDPrice = 15;
        

        double orginalPrice;
        double price;
        boolean extra3 = false;

        System.out.print("Enter customer age please: ");
        int age = in.nextInt();

        System.out.print("Enter movie type please (regular/3D/IMAX): ");
        String type = in.next();

        System.out.print("Enter show time please (matinee/evening): ");
        String time = in.next();

        System.out.print("Are you premium member? (true/false): ");
        boolean premimum = in.nextBoolean();
        System.out.print("\n");

        if (age < 5 && type.equals("IMAX")) {
            System.out.println("Error: Children under 5 cannot watch IMAX movies.");
        } else {
            System.out.print("Base Price: $");
            if (type.equals("regular")) {
                orginalPrice=RegularPrice;
                price = RegularPrice;
                System.out.println(RegularPrice);
            } else if (type.equals("3D")) {
                price = ThreeDPrice;
                orginalPrice=ThreeDPrice;
                System.out.println(ThreeDPrice);
            } else {
                price = ImaxPrice;
                System.out.println(ImaxPrice);
                orginalPrice=ImaxPrice;
            }

            if (age < 12) {
                price = price * 0.6;
                System.out.println("Children Discount Applied: 40%");
            } else if (age < 26) {
                price = price * 0.75;
                System.out.println("Student Discount Applied: 25%");
            } else if (age < 65) {
                System.out.println("Adults Don't Have Discount");
            } else {
                price = price * 0.65;
                System.out.println("Seniors Discount Applied: 35%");
            }

            if (time.equals("matinee")) {
                System.out.println("Matinee Discount Applied: 20%");
                price = price * 8/10;
            } else {
                if (type.equals("IMAX")) {
                    extra3 = true;
                    System.out.println("Evening IMAX Surcharge Applied: $3.00");
                }
            }
            if(premimum==true){
                price=price*0.9;
                System.out.println("Premium Member Discount Applied: 10%");
            }

            if(orginalPrice*0.4>price){
                price=orginalPrice*0.4;
            }
            if(extra3==true){
                price+=3;
            }

            System.out.printf("Final Price: $%.2f \n",price);
            System.out.printf("Total Savings: $%.2f \n", (orginalPrice-price));
            System.out.println("Enjoy your movie!");
        }

        in.close();
    }
}
