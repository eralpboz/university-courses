package lab3;

import java.util.Random;
/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 03
*/
import java.util.Scanner;

public class Lab03_Q3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter your username please: ");
        String username = in.next();

        if (username.equals("instructor")) {

            System.out.print("Enter your password please: ");
            String userPassword = in.next();
            if (userPassword.equals("teach2024")) {

                String currentStudens = "AliceJohnson, BobWilliams, CarolDavis, ";

                System.out.println("1- Add student\n2- Remove student\n3- Add course\n4- Remove Course\n5- Logout");
                System.out.print("Select an operation: ");
                int optnChoice = in.nextInt();

                String courses = "COURSE101:IntroToPython COURSE102:WebDevelopment ";

                if (optnChoice == 1) {

                    System.out.println("-- Add Student --");
                    System.out.print("Enter Student name: ");
                    String newStudent = in.next();

                    if (!(currentStudens.contains(newStudent))) {
                        System.out.println("New student " + newStudent + " is added!");
                        currentStudens += newStudent + ", ";
                        System.out.println("Your students: (" + currentStudens + " )");
                    } else {
                        System.out.println("This student is already enrolled!");
                    }

                } else if (optnChoice == 2) {

                    System.out.println("-- Remove Student --");
                    System.out.print("Enter student name which you want to delete: ");
                    String removeStudent = in.next();
                    if (currentStudens.contains(removeStudent)) {
                        currentStudens = currentStudens.replace(removeStudent + ", ", "");
                        System.out.println(removeStudent + " is deleted successfully from students!");
                    } else {
                        System.out.println("No student found with this name!");
                    }
                    System.out.println("Your students: ( " + currentStudens + " )");
                }  else if (optnChoice == 3) {

                    System.out.println("-- Add Course --");

                    System.out.print("Enter course name: ");
                    in.nextLine();

                    String courseName = in.nextLine();

                    Random rand = new Random();
                    int newCourse = rand.nextInt(100, 1000);
                    String newCourseCode = String.valueOf(newCourse);

                    if (courses.contains(newCourseCode)) {
                        System.out.println("A course with code " + newCourseCode + " already exists, cannot add!");
                    } else {
                        System.out.println("New course with code " + newCourseCode + " is added!");
                        courses += "COURSE" + newCourseCode + ":" + courseName + " ";
                        System.out.println("Your courses: " + courses);
                    }

                } else if (optnChoice == 4) {
                    System.out.println("-- Remove Course --");
                    System.out.print("Enter course code which you want to delete: ");
                    String removeCourse = in.next();

                    if (courses.contains(removeCourse)) {

                        int sIndx = courses.indexOf("COURSE" + removeCourse);
                        int eIndx = courses.indexOf(" ", sIndx);

                        String courseToRemove = courses.substring(sIndx, eIndx + 1);
                        courses = courses.replace(courseToRemove, "");

                        System.out.println("The course with code " + removeCourse + " is deleted successfully!");
                    } else {
                        System.out.println("No course found with this code!");
                    }

                    System.out.println("Your courses: " + courses);
                } else if (optnChoice == 5) {

                    System.out.println("Logged out successfully!");

                } else {

                    System.out.println("There is no such operation! Goodbye!");

                }
            } else {
                System.out.println("Incorrect password! Goodbye!");
            }
        } else {
            System.out.println("Username not found! Goodbye!");
        }
        in.close();
    }
}
