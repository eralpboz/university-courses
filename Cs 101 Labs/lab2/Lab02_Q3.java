package lab2;
/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 02
*/
import java.util.Scanner;

public class Lab02_Q3 {
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);

      String book;
      String title;
      String author;
      String year;
      String publisher;

      System.out.print("Enter book information: ");
      book = in.nextLine();

      title = book.substring(0, book.indexOf("{") - 1);
      author = book.substring(book.indexOf("{") + 2, book.indexOf("}") - 1);
      year = book.substring(book.indexOf("[") + 2, book.indexOf("]") - 1);
      publisher = book.substring(book.indexOf("_") + 1);

      System.out.println("The book \""+title+"\" was written by "+ author+" in "+year+" and published by "+publisher );

   }
}
