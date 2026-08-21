import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        String filename;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter file name: ");
        filename = in.next();

        WordList uniqueWordList = new WordList();
        Boolean isFinish = false;
        try {

            Scanner sc = new Scanner(new File(filename));

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                String[] wordsInArray = line.split("\\s+");
                for (String word : wordsInArray) {
                    words.add(word);
                }
            }
            sc.close();
            uniqueWordList.addUniqueWords(words);
            int choice;
            while (!(isFinish)) {
                System.out.print(
                        "\n1. Print random 10 words\n2. Show unique letter occurrence ratio\n3. Show word  length  ratio\n4. Show proceeding letter ratio\n5. Generate word\n6. Exit\nEnter your choice: ");
                choice = in.nextInt();
                if (choice == 1) {
                    uniqueWordList.printExamples();
                } else if (choice == 2) {
                    uniqueWordList.printUniqeLetterRatio();
                } else if (choice == 3) {
                    uniqueWordList.printLenghtRatio();
                } else if (choice == 4) {
                    System.out.print("Enter letter you want to see: ");
                    String let = in.next().toLowerCase();

                    int[][] counts = uniqueWordList.proceedingPairRatio();
                    int[] sum = uniqueWordList.sumTwoLetters(counts);

                    int index = -1;
                    for (int i = 0; i < uniqueWordList.letters.length; i++) {
                        if (uniqueWordList.letters[i].equals(let)) {
                            index = i;
                            break; 
                        }
                    }

                   
                    if (index != -1 && sum[index] > 0) {
                        for (int i = 0; i < counts[index].length; i++) {
                          
                            if (counts[index][i] > 0) {
                                double ratio = (double) counts[index][i] / sum[index];

                                
                                String secondLetter = uniqueWordList.letters[i];
                                System.out.printf("Ratio of %s%s: %.5f%n", let, secondLetter, ratio);
                            }
                        }
                    } 

                } else if(choice==5){
                    System.out.print("Enter your word length: ");
                    int length=in.nextInt();
                    int[][] counts = uniqueWordList.proceedingPairRatio();
                    int[] sum = uniqueWordList.sumTwoLetters(counts);
                    double[][] letterRatio= uniqueWordList.findLetterRatio(counts, sum);
                    ArrayList<String> firstLetters= new ArrayList<>();
                    firstLetters=uniqueWordList.firstLetter();
                    String word = uniqueWordList.generateWord(length, firstLetters, letterRatio);
                    System.out.println(word);

                    
                } else if (choice == 6) {
                    isFinish = true;
                } else {
                    System.out.println("Invalid input try again.");
                }
            }

        } catch (FileNotFoundException e) {

            System.out.println(filename + " not exist!");
        }

        in.close();
    }
}