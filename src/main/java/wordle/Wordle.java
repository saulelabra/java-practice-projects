package wordle;

import java.util.*;

public class Wordle {
    final private String secretWord;
    final private int attempts;

    Wordle(String secretWord, int maxAttempts) {
        this.secretWord = secretWord;
        this.attempts = maxAttempts;
    }

    private class Attempt {
        String word;
        ArrayList<ArrayList<Boolean>> coincidences;

        Attempt(String word) {
            this.word = word;
            coincidences = new ArrayList<>();
        }

        public ArrayList<ArrayList<Boolean>> checkCoincidences() {
            //Convert secret word String into map for constant access time
            Map<Character, HashSet<Integer>> secretWordMap = new HashMap<>();
            for (int i = 0; i < secretWord.length(); i++) {
                //If char already registered in map add new char position
                if (secretWordMap.get(secretWord.charAt(i)) != null) {
                    secretWordMap.get(secretWord.charAt(i)).add(i);
                } else {
                    //Else create new HashSet to store char positions
                    HashSet<Integer> newHashSet = new HashSet<>();
                    newHashSet.add(i);
                    secretWordMap.put(secretWord.charAt(i), newHashSet);
                }
            }

            //Check for character coincidences
            for (int i = 0; i < word.length(); i++) {
                ArrayList<Boolean> charAndPosCoincidence;

                if (secretWordMap.containsKey(word.charAt(i))) {
                    if (secretWordMap.get(word.charAt(i)).contains(i)) {
                        //If char is in word and in the same position
                        charAndPosCoincidence = new ArrayList<>(Arrays.asList(true, true));
                        coincidences.add(charAndPosCoincidence);
                    } else {
                        //If char is in word but different position
                        charAndPosCoincidence = new ArrayList<>(Arrays.asList(true, false));
                        coincidences.add(charAndPosCoincidence);
                    }
                } else {
                    //If there is not any coincidence of char nor position
                    charAndPosCoincidence = new ArrayList<>(Arrays.asList(false, false));
                    coincidences.add(charAndPosCoincidence);
                }
            }

            //Return correct chars and if position was correctly guessed
            return coincidences;
        }

        public String getWord() {
            return word;
        }
    }

    private void printFeedback(String attemptWord, ArrayList<ArrayList<Boolean>> coincidences) {
        for (int i = 0; i < 11; i++) {
            System.out.print(" ");
        }
        System.out.println(attemptWord);

        for (int i = 0; i < 11; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < attemptWord.length(); i++) {
            System.out.print("^");
        }
        System.out.print("\n");

        for (int i = 0; i < 11; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < attemptWord.length(); i++) {
            System.out.print("|");
        }
        System.out.print("\n");

        //Print character coincidences
        System.out.print("Character: ");
        for (ArrayList<Boolean> coincidence : coincidences) {
            if (coincidence.get(0)) {
                System.out.print("X");
            } else {
                System.out.print("0");
            }
        }
        System.out.print("\n");

        //Print position coincidences
        System.out.print("Position:  ");
        for (ArrayList<Boolean> coincidence : coincidences) {
            if (coincidence.get(1)) {
                System.out.print("X");
            } else {
                System.out.print("0");
            }
        }
        System.out.print("\n");
        System.out.println("---------------------------------------------------------------");
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < attempts; i++) {
            //Prompt the user to introduce a word
            System.out.println("Enter your guess:");
            Attempt currAttempt = new Attempt(scanner.nextLine());

            //Check for coincidences
            ArrayList<ArrayList<Boolean>> coincidences = currAttempt.checkCoincidences();

            //Give feedback to the user
            printFeedback(currAttempt.getWord(), coincidences);

            //If the correct word is guessed end the game
            if (currAttempt.getWord().equals(secretWord)) {
                System.out.println("*********************You got it!*********************");
                return;
            }
        }
        System.out.println("*********************Better luck next time!*********************");
    }
}
