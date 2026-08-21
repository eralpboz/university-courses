package lab6;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the seed number:");
        String seedNumber = scanner.next();

        System.out.print("How many numbers to generate?");
        int quantity = scanner.nextInt();

        BigInteger seed = new BigInteger(seedNumber);
        BigInteger[] randomNumbers = generateNumbers(seed, quantity);

        boolean running = true;
        int[] significantDigit= new int[10];
        while (running) {

            System.out.print(
                    "Choose a sorting algorithm:\n" +
                            "1. Bubble Sort\n" +
                            "2. Merge Sort \n" +
                            "3. Quick Sort \n" +
                            "4. Quicksort with a random element as pivot\n" +
                            "5. Quicksort with median of first, middle, and last elements as pivot\n" +
                            "6. Histogram\n"+
                            "0. Exit\n" +
                            "Selection: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                running = false;
            }
            System.out.println("Numbers without sorted");
            for (int i = 0; i < Math.min(randomNumbers.length, 50); i++) {
                System.out.println(randomNumbers[i]);
            }

            BigInteger[] listToSort = new BigInteger[randomNumbers.length];
            for (int i = 0; i < randomNumbers.length; i++) {
                listToSort[i] = randomNumbers[i];
            }

            long startTime = System.currentTimeMillis();
            if (choice == 1) {
                bubbleSort(listToSort);
            } else if (choice == 2) {
                mergeSort(listToSort, 0, listToSort.length - 1);
            } else if (choice == 3) {
                quickSort(listToSort, 0, listToSort.length - 1);
            } else if (choice == 4) {
                quickSortRandom(listToSort, 0, listToSort.length - 1);
            } else if (choice == 5) {
                quickSortMedian(listToSort, 0, listToSort.length - 1);
            } else if(choice ==6){
                for(BigInteger x:randomNumbers){
                    int bigIntegerLength= x.getNumbers().length;
                    int firsNumber=x.getNumbers()[bigIntegerLength-1];
                    significantDigit[firsNumber]++;
                }
                for(int i=0;i<10;i++){
                System.out.println(i+". " +significantDigit[i]);    
                }
                
            }else{
                System.out.println("Invalid Selection");
            }
            long endTime = System.currentTimeMillis();
            double timePassed = (endTime - startTime) / 1000.0;
            if(!(choice==6)){
            System.out.println("\n Sorting Results");
            System.out.println("Time taken: " + timePassed + " seconds");

            boolean sorted = isSorted(listToSort);
            if (sorted) {
                System.out.println("(Sorted)");
            } else {
                System.out.println("(Not Sorted)");
            }

            System.out.println("\nFirst 50 numbers (Sorted):");
            int printCount = Math.min(listToSort.length, 50);

            for (int i = 0; i < printCount; i++) {
                System.out.print(listToSort[i] + " ");

                System.out.println();
            }
            System.out.println("\n");}
        }

    }

    public static <T extends Comparable<T>> void bubbleSort(T[] list) {
        if (list == null || list.length <= 1)
            return;

        int unsortedPartitionIndex = list.length - 1;
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;

            for (int k = 0; k < unsortedPartitionIndex; k++) {

                if (list[k].compareTo(list[k + 1]) > 0) {

                    T tempHolder = list[k];
                    list[k] = list[k + 1];
                    list[k + 1] = tempHolder;

                    isSorted = false;
                }
            }

            unsortedPartitionIndex--;
        }
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static BigInteger[] generateNumbers(BigInteger seed, int bigIntegerSize) {
        BigInteger[] numbers = new BigInteger[bigIntegerSize];
        int n = seed.toString().length();

        BigInteger current = seed;

        for (int i = 0; i < bigIntegerSize; i++) {

            BigInteger newNumber = current.multiply(current).add(current);

            String newString = newNumber.toString();

            while (newString.length() < 2 * n) {
                newString = newString+"0";
            }

            int start = (newString.length() - n) / 2;
            int end = start + n;
            
            String middleNumber = newString.substring(start, end);

            int first = start;
            while (middleNumber.startsWith("0") && first > 0) {
                first--;
                middleNumber = newString.substring(first, first + n);

            }

            current = new BigInteger(middleNumber);
            numbers[i] = current;
        }

        return numbers;
    }

    public static <E extends Comparable<E>> void mergeSort(E[] inputArray, int first, int last) {
        if (first < last) {

            int middlePoint = (first + last) / 2;

            mergeSort(inputArray, first, middlePoint);
            mergeSort(inputArray, middlePoint + 1, last);

            mergeParts(inputArray, first, middlePoint, last);
        }
    }

    private static <E extends Comparable<E>> void mergeParts(E[] array, int start, int mid, int end) {

        int sizeLeft = mid - start + 1;
        int sizeRight = end - mid;

        E[] lSide = (E[]) new Comparable[sizeLeft];
        E[] rSide = (E[]) new Comparable[sizeRight];

        for (int i = 0; i < sizeLeft; i++) {
            lSide[i] = array[start + i];
        }
        for (int j = 0; j < sizeRight; j++) {
            rSide[j] = array[mid + 1 + j];
        }

        int idxLeft = 0;
        int idxRight = 0;
        int idxMerged = start;

        while (idxLeft < sizeLeft && idxRight < sizeRight) {

            if (lSide[idxLeft].compareTo(rSide[idxRight]) <= 0) {
                array[idxMerged] = lSide[idxLeft];
                idxLeft++;
            } else {

                array[idxMerged] = rSide[idxRight];
                idxRight++;
            }
            idxMerged++;
        }

        while (idxLeft < sizeLeft) {
            array[idxMerged] = lSide[idxLeft];
            idxLeft++;
            idxMerged++;
        }

        while (idxRight < sizeRight) {
            array[idxMerged] = rSide[idxRight];
            idxRight++;
            idxMerged++;
        }
    }

  
    public static <E extends Comparable<E>> void quickSort(E[] arr, int head, int tail) {
        if (head < tail) {

            E pivotValue = arr[tail];
        int smallerElementIdx = (head - 1);

        for (int scanner = head; scanner < tail; scanner++) {

            if (arr[scanner].compareTo(pivotValue) <= 0) {
                smallerElementIdx++;

                swapElements(arr, smallerElementIdx, scanner);
            }
        }

        swapElements(arr, smallerElementIdx + 1, tail);

            int partitionIndex = smallerElementIdx + 1;

            quickSort(arr, head, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, tail);
        }
    }

    public static <E extends Comparable<E>> void quickSortRandom(E[] arr, int head, int tail) {
        if (head < tail) {

            Random rng = new Random();
            int randomIdx = head + rng.nextInt(tail - head + 1);
            swapElements(arr, randomIdx, tail);

            E pivotValue = arr[tail];
        int smallerElementIdx = (head - 1);

        for (int scanner = head; scanner < tail; scanner++) {

            if (arr[scanner].compareTo(pivotValue) <= 0) {
                smallerElementIdx++;

                swapElements(arr, smallerElementIdx, scanner);
            }
        }

        swapElements(arr, smallerElementIdx + 1, tail);

      
            int partitionIndex = smallerElementIdx + 1;

            quickSortRandom(arr, head, partitionIndex - 1);
            quickSortRandom(arr, partitionIndex + 1, tail);
        }
    }

    

    public static <E extends Comparable<E>> void quickSortMedian(E[] arr, int head, int tail) {
        if (head < tail) {
            int center = (head + tail) / 2;

            if (arr[head].compareTo(arr[center]) > 0) {
                swapElements(arr, head, center);
            }

            if (arr[head].compareTo(arr[tail]) > 0) {
                swapElements(arr, head, tail);
            }

            if (arr[center].compareTo(arr[tail]) > 0) {
                swapElements(arr, center, tail);
            }
            swapElements(arr, center, tail);

            E pivotValue = arr[tail];
        int smallerElementIdx = (head - 1);

        for (int scanner = head; scanner < tail; scanner++) {

            if (arr[scanner].compareTo(pivotValue) <= 0) {
                smallerElementIdx++;

                swapElements(arr, smallerElementIdx, scanner);
            }
        }

        swapElements(arr, smallerElementIdx + 1, tail);

        
            int partitionIdx = smallerElementIdx + 1;
            quickSortMedian(arr, head, partitionIdx - 1);
            quickSortMedian(arr, partitionIdx + 1, tail);
        }
    }

    public static <E> void swapElements(E[] arr, int idx1, int idx2) {
        E tempStore = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tempStore;
    }
}