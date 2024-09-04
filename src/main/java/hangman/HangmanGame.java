package hangman;

public class HangmanGame {
    public static void main(String[] args) throws Exception {
        Hangman game = new Hangman("Secret", 8);
        game.play();
    }
}
