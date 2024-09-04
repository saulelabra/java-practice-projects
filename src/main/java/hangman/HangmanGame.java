package hangman;

public class HangmanGame {
    public static void main(String[] args) {
        Hangman game = new Hangman("Secret", 3);
        game.play();
    }
}
