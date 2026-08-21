package lab2;
/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 02
*/
import java.util.Scanner;

public class Lab02_Q2 { 
public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    
    final double earth=9.807;
    final double moon=1.620;
    final double mars=3.711;
    final double jupiter=24.790;

    double probe1;
    double probe2;

    double earthWeight1;
    double moonWeight1;
    double marsWeight1;
    double jupiterWeight1;
    double earthWeight2;
    double moonWeight2;
    double marsWeight2;
    double jupiterWeight2;


    System.out.print("Enter the mass of the first probe (kg): ");
    probe1=in.nextDouble();
    System.out.print("Enter the mass of the second probe (kg): ");
    probe2=in.nextDouble();

    earthWeight1=probe1*earth;
    moonWeight1=probe1*moon;
    marsWeight1=probe1*mars;
    jupiterWeight1=probe1*jupiter;
    earthWeight2=probe2*earth;
    moonWeight2=probe2*moon;
    marsWeight2=probe2*mars;
    jupiterWeight2=probe2*jupiter;

    System.out.printf("\t\t\t EARTH\tMOON\tMARS\tJUPITER\n");
System.out.printf("PROBE ONE (%.1f)\t%.1f\t%.1f\t%.1f\t%.1f\n",
        probe1, earthWeight1, moonWeight1, marsWeight1, jupiterWeight1);

System.out.printf("PROBE TWO (%.2f)\t%.1f\t%.1f\t%.1f\t%.1f\n",
        probe2, earthWeight2, moonWeight2, marsWeight2, jupiterWeight2);

}
}
