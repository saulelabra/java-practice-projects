package trivia;

import java.util.Scanner;

public class TriviaGame {
    public static void play(Question[] questions){
        Scanner scanner = new Scanner(System.in);
        int possiblePoints = questions.length;
        int totalPoints = 0;
        System.out.println("*********************Welcome to Trivia game*********************\n");

        for(Question question : questions){
            System.out.println(question.getQuestion());
            for(int i=0; i<question.getOptions().length; i++){
                System.out.println(i + ". " + question.getOptions()[i]);
            }

            System.out.println();
            int response = Integer.parseInt(scanner.nextLine());

            if(question.isCorrectAnswer(response)){
                totalPoints++;
            }
        }

        System.out.println("You got " + totalPoints + " of " + possiblePoints + " points!");
    }

    public static void main(String[] args) {
        Question[] questions = new Question[3];

        String[] options0 = {"Madrid", "Paris", "Berlin", "Lisbon"};
        questions[0] = new Question("What is the capital city of France?", options0, 1);

        String[] options1 = {"2.123", "0.5772", "3.1416", "3600"};
        questions[1] = new Question("What is the value of Pi?", options1, 2);

        String[] options2 = {"Thomas Alva Edison", "Alan Turing", "Charles Babbage", "Ada Lovelace"};
        questions[2] = new Question("Who cracked the enigma code?", options2, 1);

        play(questions);
    }
}
