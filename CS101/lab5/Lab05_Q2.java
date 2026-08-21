package lab5;

import java.util.Scanner;

/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 05
*/
public class Lab05_Q2 {

    static boolean isLowerAlphabet(char charIsAlphbt) {
        if (((charIsAlphbt >= 'a' && charIsAlphbt <= 'z'))) {
            return true;
        }
        return false;
    }

    static boolean isUpperAlphabet(char charIsAlphbt) {
        if (((charIsAlphbt >= 'A' && charIsAlphbt <= 'Z'))) {
            return true;
        }
        return false;
    }

    public static String shiftCipher(String text, int shift, boolean encrypt) {
        String output = "";

        if (encrypt) {
            // ŞİFRELEME (İleri sarma)
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);

                if (Lab05_Q2.isLowerAlphabet(c)) {

                    int asci = (c - 97 + shift) % 26;
                    output += (char) (97 + asci);
                } else if (Lab05_Q2.isUpperAlphabet(c)) {

                    int asci = (c - 65 + shift) % 26;
                    output += (char) (65 + asci);
                } else {

                    output += c;
                }
            }
        } else {
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);

                if (Lab05_Q2.isLowerAlphabet(c)) {
                    int asci = ((c - 97 - shift) % 26 + 26) % 26;
                    output += (char) (97 + asci);
                } else if (Lab05_Q2.isUpperAlphabet(c)) {
                    int asci = ((c - 65 - shift) % 26 + 26) % 26;
                    output += (char) (65 + asci);
                } else {
                    output += c;
                }
            }
        }
        return output;

    }

    public static String reverseAlphabetCipher(String text) {
        String output = "";
        for (int i = 0; i < text.length(); i++) {
            if (Lab05_Q2.isUpperAlphabet(text.charAt(i))) {
                int asci = 90 - text.charAt(i);
                output += (char) (65 + asci);
            } else if (Lab05_Q2.isLowerAlphabet(text.charAt(i))) {

                output += (char) ('z' - (text.charAt(i) - 'a'));
            } else {
                output += text.charAt(i);
            }
        }

        return output;
    }

    public static String reverseString(String text) {
        StringBuilder stb = new StringBuilder();

        for (int i = text.length() - 1; i >= 0; i--) {
            stb.append(text.charAt(i));
        }

        return stb.toString();
    }

    public static void main(String[] args) {
        String inpuTxt;
        Scanner in = new Scanner(System.in);
        int selectedOption = -1;
        while (selectedOption != 4) {

            System.out.println("=== Encoding Toolkit ===");
            System.out.println("1) Shift Cipher \n2) Reverse Alphabet Cipher \n3) Reverse Text \n4) Exit ");
            System.out.print("Choose an option (1-4): ");
            selectedOption = in.nextInt();
            in.nextLine();
            if (selectedOption == 1) {
                System.out.print("Enter text: ");
                inpuTxt = in.nextLine();
                System.out.print("Enter shift amount: ");
                int amount = in.nextInt();
                System.out.print("Type 'e' to encode or 'd' to decode: ");
                String a = in.next();

                if (a.equals("e")) {
                    System.out.println("Result: " + Lab05_Q2.shiftCipher(inpuTxt, amount, true));
                } else if (a.equals("d")) {
                    System.out.println("Result: " + Lab05_Q2.shiftCipher(inpuTxt, amount, false));
                }

            } else if (selectedOption == 2) {
                System.out.print("Enter text: ");
                inpuTxt = in.nextLine();
                System.out.println("Result: " + Lab05_Q2.reverseAlphabetCipher(inpuTxt));
            } else if (selectedOption == 3) {
                System.out.print("Enter text: ");
                inpuTxt = in.nextLine();
                System.out.println("Result: " + Lab05_Q2.reverseString(inpuTxt));
            } else if (selectedOption == 4) {
                System.out.println("Goodbye!");
            }
            System.out.println();
        }
    }
}
