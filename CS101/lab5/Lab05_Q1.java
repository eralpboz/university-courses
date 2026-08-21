package lab5;

/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 05
*/
import java.util.Scanner;

public class Lab05_Q1 {
    static boolean isAlphabetic(char charIsAlphbt) {
        if ((charIsAlphbt >= 'A' && charIsAlphbt <= 'Z') || (charIsAlphbt >= 'a' && charIsAlphbt <= 'z')) {
            return true;
        }
        return false;
    }

    static char toUpper(char charIsLowrCase) {
        if (charIsLowrCase >= 'a' && charIsLowrCase <= 'z') {
            return (char) (charIsLowrCase - ('a' - 'A'));
        }
        return charIsLowrCase;
    }

    static boolean isSeparator(char characterIsSeperator) {
        boolean a = characterIsSeperator == ' ' || characterIsSeperator == '\t' || characterIsSeperator == '\n';
        return a;
    }

    static boolean isNumeric(char charIsNmrc) {
        boolean a = (charIsNmrc >= '0' && charIsNmrc <= '9');
        return a;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int selectedOption = -1;
        String inpuString1;
        String inpuString2;

        while (selectedOption != 5) {
            System.out.println("=== String Toolkit === ");
            System.out.println("1) Mirror Text \n2) Compare Letters \n3) Token Count \n4) Generate Tag \n5) Exit");
            System.out.print("Select an option (1-5): ");
            selectedOption = in.nextInt();
            in.nextLine();

            if (selectedOption == 1) {
                System.out.print("Enter text: ");
                inpuString1 = in.nextLine();
                System.out.println("Result: " + Lab05_Q1.isMirrorText(inpuString1));
            } else if (selectedOption == 2) {

                System.out.print("Enter First String: ");
                inpuString1 = in.nextLine();
                System.out.print("Enter Second String: ");
                inpuString2 = in.nextLine();

                System.out.println("Result: " + Lab05_Q1.haveSameLetters(inpuString1, inpuString2));

            } else if (selectedOption == 3) {
                System.out.print("Enter text: ");
                inpuString1 = in.nextLine();
                System.out.println("Token Count: " + Lab05_Q1.tokenCount(inpuString1));

            } else if (selectedOption == 4) {
                System.out.print("Enter text: ");
                inpuString1 = in.nextLine();
                System.out.println("Tag: " + Lab05_Q1.generateTag(inpuString1));
            } else if (selectedOption == 5) {
                System.out.println("Goodbye!");
            }
            System.out.println();
        }
        in.close();
    }

    public static boolean haveSameLetters(String str1, String str2) {
        String newString1 = "";
        String newString2 = "";
        for (int i = 0; i < str1.length(); i++) {
            if ((Lab05_Q1.isAlphabetic(str1.charAt(i)))) {

                newString1 += Lab05_Q1.toUpper(str1.charAt(i));
            }
        }
        for (int i = 0; i < str2.length(); i++) {
            if ((Lab05_Q1.isAlphabetic(str2.charAt(i)))) {

                newString2 += Lab05_Q1.toUpper(str2.charAt(i));
            }
        }

        if (newString1.length() != newString2.length()) {
            return false;
        }
        StringBuilder st = new StringBuilder(newString2);
        for (int i = 0; i < newString1.length(); i++) {
            char c = newString1.charAt(i);
            boolean a = true;
            for (int j = 0; j < st.length() && a; j++) {
                if (c == st.charAt(j)) {
                    st.deleteCharAt(j);
                    a = false;
                }
            }
        }
        newString2 = st.toString();
        if (newString2.length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isMirrorText(String isMirror) {
        String newString = "";
        for (int i = 0; i < isMirror.length(); i++) {
            if (!(Lab05_Q1.isSeparator(isMirror.charAt(i)))) {

                newString += Lab05_Q1.toUpper(isMirror.charAt(i));
            }
        }

        for (int a = 0; (a < newString.length() / 2); a++) {
            if (newString.charAt(a) != newString.charAt(newString.length() - a - 1)) {
                return false;
            }
        }
        return true;
    }

    public static String generateTag(String strng) {
        String lowerCaseStr = strng.toLowerCase();

        StringBuilder strb = new StringBuilder();
        boolean isLastSpace = false;

        for (int i = 0; i < lowerCaseStr.length(); i++) {
            char c = lowerCaseStr.charAt(i);

            if (Lab05_Q1.isAlphabetic(c) || Lab05_Q1.isNumeric(c)) {
                strb.append(c);
                isLastSpace = false;
            } else if (Lab05_Q1.isSeparator(c)) {
                if (!isLastSpace && strb.length() > 0) {
                    strb.append('_');
                    isLastSpace = true;
                }
            }
        }

        if (strb.length() > 0 && strb.charAt(strb.length() - 1) == '_') {
            strb.deleteCharAt(strb.length() - 1);
        }

        return strb.toString();
    }

    public static int tokenCount(String stringToBeCounted) {
        int count = 0;

        for (int i = 0; i < stringToBeCounted.length(); i++) {

            if (!Lab05_Q1.isSeparator(stringToBeCounted.charAt(i)) &&
                    (i == 0 || Lab05_Q1.isSeparator(stringToBeCounted.charAt(i - 1)))) {

                count++;
            }
        }

        return count;
    }

}
