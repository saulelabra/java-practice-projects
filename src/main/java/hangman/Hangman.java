package hangman;

import java.util.*;

public class Hangman {
    final private String secretWord;
    private HashMap<Character, ArrayList<Integer>> secretWordMap;
    private int attempts;
    private StringBuilder guessedWord;
    private HashSet<Character> guessedLetters;

    Hangman(String secretWord, int maxAttempts){
        this.secretWord = secretWord.toLowerCase();
        this.attempts = maxAttempts;
        guessedWord = new StringBuilder();
        guessedWord.append("_".repeat(secretWord.length()));
        guessedLetters = new HashSet<>();

        //Initialize secretWordMap to store indexes of chars
        secretWordMap = new HashMap<>();
        for(int i=0; i<secretWord.length(); i++){
            if(secretWordMap.containsKey(this.secretWord.charAt(i))){
                secretWordMap.get(this.secretWord.charAt(i)).add(i);
            }else{
                secretWordMap.put(this.secretWord.charAt(i), new ArrayList<>(Arrays.asList(i)));
            }
        }
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);
        char currentGuess;

        System.out.println("********************Welcome to Hangman********************");

        while(attempts > 0){
            //Show game status (blank spaces and already guessed letters)
            System.out.println(guessedWord.toString());

            //Prompt the player to introduce his guess
            do {
                System.out.println("Enter your guess:");
                currentGuess = scanner.nextLine().toLowerCase().charAt(0);

                //If letter already guessed prompt again
                if(guessedLetters.contains(currentGuess)) System.out.println("Letter already attempted, try again!");
            } while (guessedLetters.contains(currentGuess));

            //Register guess in game status-----------

            //Register guess in guessedLetters
            guessedLetters.add(currentGuess);

            //Look for the indexes in secretWord where the char exists
            if(secretWordMap.containsKey(currentGuess)){
                //Replace the underscores in guessedWord with the char in the corresponding indexes
                for(int index : secretWordMap.get(currentGuess)){
                    guessedWord.replace(index, index+1, Character.toString(currentGuess));
                }
            }else{
                //If the char is not found subtract one attempt
                attempts--;
            }

            //If word is guessed completely return
            
            //System.out.println("You won!");
            //return;
        }

        //End game if attempts are finished
        System.out.println("You ran out of attempts. Better luck next time!");
    }
}
