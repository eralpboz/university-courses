/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 03
*/
package lab3;

import java.util.Scanner;

public class Lab03_Q2 {
    public static void main(String[] args) {


        Scanner inputs = new Scanner(System.in);

        int point = 50;
        System.out.print("Enter customer age please: ");
        int travelerAge = inputs.nextInt();

        System.out.print("Enter annual travel budget please: ");
        double budgetForTravel = inputs.nextDouble();

        System.out.print("Enter number of previous trips with company please: ");
        int tripNumber = inputs.nextInt();

        System.out.print("Enter preferred destination type please (beach/mountain/city/adventure): ");
        String type = inputs.next();

        System.out.print("Enter travel season please (summer/winter/spring/fall): ");
        String travelSeason = inputs.next();

        System.out.print("Enter number of travelers in group please: ");
        int numberTravelers = inputs.nextInt();
        System.out.println();

        // Budget

        if (budgetForTravel <= 15000) {
            point += budgetForTravel / 1000 * 10;
        } else {
            point += 150;
        }

        // Age

        if (travelerAge >= 18) {
            if (travelerAge < 31) {
                point += 15;
            } else if (travelerAge < 46) {
                point += 25;
            } else if (travelerAge < 61) {
                point += 20;
            } else if (travelerAge < 76) {
                point += 10;
            }
        }

        // Preferance

        if (type.equals("beach") && travelSeason.equals("summer")) {
            point += 15;
        } else if (type.equals("adventure") && (travelerAge >= 18 && travelerAge <= 45)) {
            point += 25;
        } else if (type.equals("mountain") && travelSeason.equals("winter")) {
            point += 15;
        }

        // Trip NUmber

        if (tripNumber > 2) {
            if (tripNumber < 6) {
                point += 30;
            } else if (tripNumber < 11) {
                point += 60;
            } else {
                point += 100;
            }
        }

        // Season
        if (travelSeason.equals("summer") && (type.equals("beach") || type.equals("adventure"))) {
            point += 20;

        } else if (travelSeason.equals("winter") && type.equals("mountain")) {
            point += 20;

        } else if ( type.equals("city")&&travelSeason.equals("spring") ) {
            point += 15;
        } else if ((type.equals("mountain") || type.equals("city"))&&travelSeason.equals("fall") ) {
            point += 15;

        }

        // Group Number
        point -= numberTravelers * 5;

        System.out.println("Total eligibility score: " + point+"\n");
        

        if (budgetForTravel >= 2000&& point >= 150 && travelerAge >= 18  && numberTravelers <= 8) {
            System.out.println("The applicant is approved for the travel package.");
            System.out.print("Package tier: ");
            if (point < 200) {
                System.out.println("Silver Package");
            } else if (point < 250) {
                System.out.println("Gold Package");
            } else {
                System.out.println("Platinum Package");
            }
        } else {
            System.out.println("The applicant is not approved for the travel package. Reason:");

            if (travelerAge < 18) {
                System.out.println("Age is below 18.");
            }
            if (budgetForTravel < 2000) {
                System.out.println("Annual budget is below $2,000.");
            }
            if (numberTravelers > 8) {
                System.out.println("Group size exceeds 8 travelers.");
            }
            if (point < 150) {
                System.out.println("Total eligibility score is below 150 points.");
            }
        }
    }
}
