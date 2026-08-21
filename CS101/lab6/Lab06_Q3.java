package lab6;

import java.util.Scanner;

public class Lab06_Q3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rowA, colA, rowB, colB;
        int matrixA[][], matrixB[][];
        do {
            System.out.print("Enter rows for Matrix A: ");
            rowA = in.nextInt();
            System.out.print("Enter columns for Matrix A: ");
            colA = in.nextInt();
            matrixA = new int[rowA][colA];

            System.out.println("Enter elements for Matrix A: ");
            for (int i = 0; i < matrixA.length; i++) {
                for (int j = 0; j < matrixA[i].length; j++) {
                    matrixA[i][j] = in.nextInt();
                }
                System.out.println();
            }

            System.out.print("Enter rows for Matrix B: ");
            rowB = in.nextInt();
            System.out.print("Enter columns for Matrix B: ");
            colB = in.nextInt();
            matrixB = new int[rowB][colB];
            System.out.println("Enter elements for Matrix B: ");
            for (int i = 0; i < matrixB.length; i++) {
                for (int j = 0; j < matrixB[i].length; j++) {
                    matrixB[i][j] = in.nextInt();
                }
                System.out.println();
            }

            if (colA != rowB) {
                System.out.println("Error: Dimensions do not match for multiplication.");
            }
        } while (colA != rowB);

        int result[][] = matrixMultiply(matrixA, matrixB);
        System.out.println("Result: ");

        for (int a = 0; a < result.length; a++) {
            for (int b = 0; b < result[a].length; b++) {
                System.out.print(result[a][b] + " ");
            }
            System.out.println();
        }
    }

    static int[][] matrixMultiply(int[][] matA, int[][] matB) {
        int rowsA = matA.length;
        int colsA = matA[0].length;
        int colsB = matB[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matA[i][k] * matB[k][j];
                }
            }
        }
        return result;
    }

}
