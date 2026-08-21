import java.util.*;

public class WordList {

    public String[] letters = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        };

    private ArrayList<String> uniqueWords;

    public WordList() {
        this.uniqueWords = new ArrayList<>();

    }

    public void addUniqueWords(ArrayList<String> words) {
        String word;
        String originalWord;
        for (int i = 0; i < words.size(); i++) {
            originalWord = words.get(i);
            word = originalWord.toLowerCase().replaceAll("[^a-zA-Z]", "");
            if (!word.isEmpty() && !uniqueWords.contains(word)) {
                uniqueWords.add(word);
            }
        }

    }

    public int getSize() {
        return uniqueWords.size() ;
    }

    

    public void printExamples() {
        

        ArrayList<Integer> usedIntegers = new ArrayList<>();
        Random rand = new Random();
        int randomNumber;
        for (int i = 0; i < 10;) {
            randomNumber = rand.nextInt(uniqueWords.size());

            if (!usedIntegers.contains(randomNumber)) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(uniqueWords.get(randomNumber));

                usedIntegers.add(randomNumber);
                i++;
            }
        }
        System.out.println();
    }

    public void printLenghtRatio() {
        int maxLength = 0;
        for (String word : uniqueWords) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }

        int[] lengthCounter = new int[maxLength + 1];

        for (int i = 0; i < uniqueWords.size(); i++) {

            lengthCounter[uniqueWords.get(i).length()]++;
        }

        for (int i = 1; i < lengthCounter.length; i++) {
            double ratio = (double) lengthCounter[i] / (uniqueWords.size() );
            if (i < 10) {
                System.out.printf("Words with size %d ratio:  %.5f%n", i, ratio);
            } else {
                System.out.printf("Words with size %d ratio: %.5f%n", i, ratio);
            }

        }
    }

    public void printUniqeLetterRatio() {
        
        int[] counter = new int[26];
        for (int i = 0; i < uniqueWords.size(); i++) {
            String word = uniqueWords.get(i);
            for (int j = 0; j < letters.length; j++) {
                if (word.contains(letters[j])) {
                    counter[j]++;
                }
            }
        }
        for (int i = 0; i < counter.length; i++) {
            double ratio = (double) counter[i] / (uniqueWords.size() );
            System.out.printf("Ratio of %s ratio: %.5f%n", letters[i], ratio);
        }
    }

    public int[][] proceedingPairRatio() {
        
        int[][] counter = new int[26][26];
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < letters.length; j++) {
                String twoLetter = letters[i] + letters[j];
                for (int k = 0; k < uniqueWords.size(); k++) {
                    String word = uniqueWords.get(k);
                    int count = counterForTwoLetters(word, twoLetter);
                    if (count > 0) {
                        counter[i][j] += count;
                    }
                }
            }
        }
        return counter;
    }
    public int[] sumTwoLetters(int[][] letters){
        int[] sum = new int[letters.length];
        for(int i=0;i<letters.length;i++){
            for(int j =0; j<letters[i].length;j++){
                sum[i]+=letters[i][j];
            }
        }
        return sum;
    }

    public int counterForTwoLetters(String word, String letters) {
        int count = 0;
        int index = 0;
        while ((index = word.indexOf(letters, index)) != -1) {
            count++;
            index++;
        }
        return count;
    }

    public double[][] findLetterRatio(int[][] count, int[] sum) {
        double[][] letterRatio = new double[26][26];
        for (int i = 0; i < count.length; i++) {
            
            if (sum[i] > 0) { 
                for (int j = 0; j < count[i].length; j++) { 
                    double ratio = (double) count[i][j] / sum[i];
                    letterRatio[i][j] = ratio;
                }
            }
        }
        return letterRatio;
    }
    public ArrayList<String> firstLetter(){
        ArrayList<String> firstLetters = new ArrayList<>();
        for(int i=0;i<letters.length;i++){
           for(int j=0;j<uniqueWords.size();j++) {
            if(letters[i].equals(uniqueWords.get(j).substring(0,1))){
                firstLetters.add(letters[i]);
            }
           }
           
        }
        
        return firstLetters;
    }
        public String generateWord(int length, ArrayList<String> firstLetters, double[][] letterRatio ){
            String word="";
        
            Random rand = new Random();
            int randomNumber = rand.nextInt(0,firstLetters.size());
            String firstLetter = firstLetters.get(randomNumber);
            word+= firstLetter;
            double random;
            double prob=0;
            String lastLetter=firstLetter;
            for(int i=0; i<length-1;i++){
                prob=0;
            
                random = rand.nextDouble();

                int index = -1;
         
                for (int o = 0; o < letters.length;o++) {
                    if (letters[o].equals(lastLetter)) {
                            index = o;
                            break; 
                        }
                    }

                for(int j=0; j<26;j++){
                    prob += letterRatio[index][j];
                    if (random <= prob) {
                        lastLetter= letters[j];   
                        word += lastLetter;         
                        break;
                }
            }
        
        }
        return  word;
    }
}