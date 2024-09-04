package hangman;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Hangman{
    final private String secretWord;
    private HashMap<Character, ArrayList<Integer>> secretWordMap;
    private int maxAttempts;
    private int attempts;
    private StringBuilder guessedWord;
    private HashSet<Character> guessedLetters;
    private StringBuilder hangmanDrawing;

    Hangman(String secretWord, int maxAttempts) throws IOException{
        this.secretWord = secretWord.toLowerCase();
        this.maxAttempts = maxAttempts;
        this.attempts = maxAttempts;
        guessedWord = new StringBuilder();
        guessedWord.append("_".repeat(secretWord.length()));
        guessedLetters = new HashSet<>();
        hangmanDrawing = new StringBuilder();

        //Initialize secretWordMap to store indexes of chars
        secretWordMap = new HashMap<>();
        for(int i=0; i<secretWord.length(); i++){
            if(secretWordMap.containsKey(this.secretWord.charAt(i))){
                secretWordMap.get(this.secretWord.charAt(i)).add(i);
            }else{
                secretWordMap.put(this.secretWord.charAt(i), new ArrayList<>(Arrays.asList(i)));
            }
        }

        //Initialize String for hangman drawing from txt file
        try (FileReader reader = new FileReader("./src/main/java/hangman/hangmanPicture.txt")){
            int data ;
            while((data = reader.read()) != -1){
                hangmanDrawing.append((char)data);
            }
        }catch (IOException e){
            throw(e);
        }
    }

    public void printHangman(){
        int totalChars = 30;
        int totalLineBreaks = 5;
        int lineBreakCounter = 0;

        //Get number of parts to print in each iteration (floored)
        int partsPerAttempt = (int)Math.floor((double) totalChars /maxAttempts);
        int attemptsUsed = maxAttempts-attempts;
        int partsToPrint;

        //If division is not exact sum the difference
        if(totalChars%maxAttempts != 0){
            partsToPrint = partsPerAttempt*attemptsUsed+(totalChars%maxAttempts);
        }else{
            partsToPrint = partsPerAttempt*attemptsUsed;
        }

        //Print hangman
        for(int i=0; i<partsToPrint; i++){
            if(hangmanDrawing.charAt(i) == '\n'){
                lineBreakCounter++;
            }

            System.out.print(hangmanDrawing.toString().charAt(i));
        }

        //Print placeholder line breaks
        for(int i=0; i<totalLineBreaks-lineBreakCounter+1; i++) System.out.print('\n');
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);
        char currentGuess;

        System.out.println("********************Welcome to Hangman********************");

        while(attempts > 0){
            System.out.println("**********************************************************");
            //Print attempts remaining
            System.out.println("Attempts: " + attempts);

            //Print hangman
            printHangman();

            //Show game status (blank spaces and already guessed letters)
            System.out.println(guessedWord.toString());

            //Prompt the player to introduce his guess
            do {
                System.out.println("Enter your guess:");
                currentGuess = scanner.nextLine().toLowerCase().charAt(0);

                //If letter already guessed prompt again
                if(guessedLetters.contains(currentGuess)) System.out.println("Letter already attempted, try again!");
            } while (guessedLetters.contains(currentGuess));

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
            if(!guessedWord.toString().contains("_")) {
                System.out.println("You won!");
                return;
            }
            System.out.println("**********************************************************");
        }

        //End game if attempts are finished

        System.out.println("**********************************************************");
        //Print attempts remaining
        System.out.println("Attempts: " + attempts);

        //Print hangman
        printHangman();

        //Show game status (blank spaces and already guessed letters)
        System.out.println(guessedWord.toString());
        System.out.println("You ran out of attempts. Better luck next time!");
        System.out.println("**********************************************************");
    }
}
