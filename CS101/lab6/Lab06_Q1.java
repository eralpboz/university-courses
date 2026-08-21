package lab6;

import java.util.Scanner;

public class Lab06_Q1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows = in.nextInt(); 
        System.out.print("Enter number of columns: ");
        int cols = in.nextInt(); 
        

        int[][] intArray = new int[rows][cols]; 
        
        System.out.println("Enter elevations: ");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                intArray[i][j] = in.nextInt();
            }
        }

        System.out.println("\nTerrain Map:");
        doubleArrayPrinter(intArray);
        
        boolean peak = false;
        System.out.println("\nPeaks found:");

  
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
  
                if (isPeak(intArray, i, j)) {
                    System.out.println("Peak at (" + i + ", " + j + ") with elevation " + intArray[i][j]);
                    peak = true;
                }
            }
        }
        
        if (!peak) {
            System.out.println("No peaks detected."); 
        }
    }

    static void doubleArrayPrinter(int[][] intArray) {
        for (int a = 0; a < intArray.length; a++) {
            for (int b = 0; b < intArray[a].length; b++) {
                System.out.print(intArray[a][b] + " ");
            }
            System.out.println();
        }
    }

    static boolean isPeak(int[][] arr, int selectedRow, int selectedCol) {
   
        int[] dx = { 0, 0, -1, 1 }; 
        int[] dy = { -1, 1, 0, 0 }; 
        
        int currentVal = arr[selectedRow][selectedCol];

        for (int i = 0; i < 4; i++) {
            int newRow = selectedRow + dy[i];
            int newCol = selectedCol + dx[i];
          
            if (newRow >= 0 && newRow < arr.length && newCol >= 0 && newCol < arr[0].length) {
                
                if (currentVal <= arr[newRow][newCol]) {
                    return false;
                }
            }
        }
        return true;
    }
}