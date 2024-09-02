package wordle;

import java.util.*;

public class Wordle {
    private String secretWord;
    private int attempts;

    Wordle(String secretWord, int maxAttempts){
        this.secretWord = secretWord;
        this.attempts = maxAttempts;
    }

    private class Attempt {
        String word;
        ArrayList<ArrayList<Boolean>> coincidences;

        Attempt(String word){
            this.word = word;
            coincidences = new ArrayList<>(word.length());
        }

        public ArrayList<ArrayList<Boolean>> checkCoincidences() {
            //Convert secret word String into map for constant access time
            Map<Character, HashSet<Integer>> secretWordMap = new HashMap<>();
            for(int i=0; i<secretWord.length(); i++){
                //If char already registered in map add new char position
                if(secretWordMap.get(secretWord.charAt(i)) != null){
                    secretWordMap.get(secretWord.charAt(i)).add(i);
                }else{
                    //Else create new HashSet to store char positions
                    HashSet<Integer> newHashSet = new HashSet<>();
                    newHashSet.add(i);
                    secretWordMap.put(secretWord.charAt(i), newHashSet);
                }
            }

            System.out.println("SecretWordMap:");
            System.out.println(secretWordMap);

            //Check for character coincidences
            for(int i=0; i<word.length(); i++){
                //If char is in word
                if(secretWordMap.containsKey(word.charAt(i))){
                    //If char is in word and in the same position
                    if(secretWordMap.get(word.charAt(i)).contains(i)){
                        coincidences.set(i, new ArrayList<Boolean>(Arrays.asList(true, true)));
                    }else{
                        coincidences.set(i, new ArrayList<Boolean>(Arrays.asList(true, false)));
                    }
                }
            }

            //Return correct chars and if position was correctly guessed
            return coincidences;
        }

        public String getWord(){
            return word;
        }
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i<attempts; i++){
            System.out.println("Enter your guess:");
            Attempt currAttempt = new Attempt(scanner.nextLine());
            ArrayList<ArrayList<Boolean>> coincidences = currAttempt.checkCoincidences();

            //Return correct chars and if position was correctly guessed

            //Return feedback
            System.out.println("Good guess!");
        }
    }
}
