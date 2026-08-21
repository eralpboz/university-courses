package lab6;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab06_Q2 {

    static int capacity;

    static String songsArray[];
    static int ratingsArray[];
    static int location = 0;
    static boolean isList = false;

    static ArrayList<String> songsArrList = new ArrayList<>();
    static ArrayList<Integer> ratingArrList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter initial playlist capacity: ");
        capacity = in.nextInt();
        in.nextLine(); 

        songsArray = new String[capacity];
        ratingsArray = new int[capacity];

        String title;
        int rating;
        boolean a = true;
        
        while (a) {
            System.out.println("Playlist Manager");
            System.out.println("1. Add Song\n2. Remove Song\n3. Update Song Rating\n4. Search Song\n5. View All Songs\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = in.nextInt();

            if (choice == 1) {
                System.out.print("Enter song title: ");
                title = in.next();
                System.out.print("Enter rating (1-5): ");
                rating = in.nextInt();
                addSong(title, rating);
                
            } else if (choice == 2) {
                System.out.print("Enter song title to remove: ");
                title = in.next();
                removeSong(title);

            } else if (choice == 3) {
                System.out.print("Enter song title to update: ");
                title = in.next();
                
                if (isList) {
                    while (!songsArrList.contains(title)) {
                        System.out.print("Invalid input try again: ");
                        title = in.next();
                    }
                    for (int i = 0; i < songsArrList.size(); i++) {
                        if (title.equals(songsArrList.get(i))) {
                            System.out.println("Current rating: " + ratingArrList.get(i));
                        }
                    }
                } else {
                    boolean x = true;
                    
                    for (int i = 0; i < location; i++) {
                        if (title.equals(songsArray[i])) {
                            x = false;
                            System.out.println("Current Rating: " + ratingsArray[i]);
                        }
                    }
                    while (x) {
                        System.out.print("Invalid input try again: ");
                        title = in.next();
                        for (int i = 0; i < location; i++) {
                            if (title.equals(songsArray[i])) {
                                x = false;
                                System.out.println("Current Rating: " + ratingsArray[i]);
                            }
                        }
                    }
                }
                
                System.out.print("Enter new Song Rating: ");
                rating = in.nextInt();

                boolean y = true;
                while (y) {
                    while (rating < 1 || rating > 5) {
                        System.out.print("Invalid input try again: ");
                        rating = in.nextInt();
                    }
                    
                    boolean isSame = false;
                    if (isList) {
                        for (int i = 0; i < songsArrList.size(); i++) {
                            if (title.equals(songsArrList.get(i))) {
                                if (ratingArrList.get(i) == rating) {
                                    isSame = true;
                                } 
                            }
                        }
                    } else {
                        for (int i = 0; i < location; i++) { 
                            if (title.equals(songsArray[i])) {
                                if (ratingsArray[i] == rating) {
                                    isSame = true;
                                } 
                            }
                        }
                    }
                    
                    if (isSame) {
                        System.out.print("New rating cannot be the same. Enter new rating: ");
                        rating = in.nextInt();
                    } else {
                        y = false;
                    }
                }
                updateSongRating(title, rating);

            } else if (choice == 4) {
                System.out.print("Enter song title to search: ");
                title = in.next();
                searchSong(title);
                
            } else if (choice == 5) {
                viewAllSongs();
                
            } else if (choice == 6) {
                System.out.println("Goodbye!");
                a = false;
            }
        }
    }

    static void viewAllSongs() {
        if (location == 0 && songsArrList.size() == 0) {
            System.out.println("Playlist is empty.");
        } else {
            if (isList) {
                for (int i = 0; i < songsArrList.size(); i++) {
                    System.out.println((i + 1) + ". " + songsArrList.get(i) + " (Rating: " + ratingArrList.get(i) + ")");
                }
            } else {
                for (int i = 0; i < location; i++) { 
                    System.out.println((i + 1) + ". " + songsArray[i] + " (Rating: " + ratingsArray[i] + ")");
                }
            }
        }
    }

    static void addSong(String title, int rating) {
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid title or rating!");
        } else {
            boolean exists = false;
            if (isList) {
                for (int i = 0; i < songsArrList.size(); i++) {
                    if (title.equals(songsArrList.get(i))) {
                        exists = true;
                    }
                }
            } else {
                for (int i = 0; i < location; i++) {
                    if (title.equals(songsArray[i])) {
                        exists = true;
                    }
                }
            }

            if (exists) {
                System.out.println("Song already exists!");
            } else {
                if (!isList && location == capacity) {
                    System.out.println("\nArray full! Switching to dynamic ArrayList.\n");
                    isList = true;
                    for (int i = 0; i < songsArray.length; i++) {
                        songsArrList.add(songsArray[i]);
                        ratingArrList.add(ratingsArray[i]);
                    }
                }

                if (isList) {
                    songsArrList.add(title);
                    ratingArrList.add(rating);
                } else {
                    songsArray[location] = title;
                    ratingsArray[location] = rating;
                    location++;
                }
                
                System.out.println("Song added successfully!");
            }
        }
    }

    static void updateSongRating(String title, int rating) {
        if (isList) {
            for (int i = 0; i < songsArrList.size(); i++) {
                if (title.equals(songsArrList.get(i))) {
                    ratingArrList.set(i, rating);
                }
            }
        } else {
            for (int i = 0; i < location; i++) {
                if (title.equals(songsArray[i])) {
                    ratingsArray[i] = rating;
                }
            }
        }
        System.out.println("Rating updated.");
    }

    static void searchSong(String title) {
        boolean found = false;
        if (isList) {
            for (int i = 0; i < songsArrList.size(); i++) {
                if (title.equals(songsArrList.get(i))) {
                    System.out.println("Song: " + songsArrList.get(i) + " - Rating: " + ratingArrList.get(i));
                    found = true;
                }
            }
        } else {
            for (int i = 0; i < location; i++) {
                if (title.equals(songsArray[i])) {
                    System.out.println("Song: " + songsArray[i] + " - Rating: " + ratingsArray[i]);
                    found = true;
                }
            }
        }
        
        if (!found) {
            System.out.println("Song not found!");
        }
    }

    static void removeSong(String title) {
        boolean isFound = false;
        int targetIndex = -1;

        if (isList) {
            // İSTEK: break kullanılmadı, o yüzden sadece ilk bulduğunu silecek mantık kuruldu
            for (int i = 0; i < songsArrList.size(); i++) {
                if (title.equals(songsArrList.get(i)) && !isFound) {
                    targetIndex = i;
                    isFound = true;
                }
            }
            
            if (isFound) {
                songsArrList.remove(targetIndex);
                ratingArrList.remove(targetIndex);
                System.out.println("Song removed.");
            } else {
                System.out.println("Song not found!");
            }
            
        } else {
            int index = -1;
            for (int i = 0; i < location; i++) {
          
                if (title.equals(songsArray[i])) {
                    index = i;
                }
            }

            if (index == -1) {
                System.out.println("Song not found!");
            } else {
                for (int i = index; i < location - 1; i++) {
                    songsArray[i] = songsArray[i + 1];
                    ratingsArray[i] = ratingsArray[i + 1];
                }
                location--;
                System.out.println("Song removed.");
            }
        }
    }
}