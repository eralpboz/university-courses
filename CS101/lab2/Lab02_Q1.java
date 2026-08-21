package lab2;
/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 02
*/
import java.util.Scanner;

public class Lab02_Q1{
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);

        double base;
        double slant;
        double height;
        double volume;
        double surface;

        System.out.print("Enter the base edge length: ");
        base=in.nextDouble();

        System.out.print("Enter the slant height: ");
        slant=in.nextDouble();

        height=Math.sqrt(Math.pow(slant, 2)-Math.pow(base/2, 2));
        volume=height*base*base/3;
        surface=(base*base)+(2*(base*slant));

        System.out.println("The height of pyramid is:\t"+height);
        System.out.println("The volume of pyramid is:\t"+volume);
        System.out.println("The total surface area is:\t"+surface);



    }
}