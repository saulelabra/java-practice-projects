package trivia;

public class Question {
    private String question;
    private String[] options;
    private int correctAnswer;

    Question(String question, String[] options, int correctAnswer){
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer(int answer){
        return answer == correctAnswer;
    }

    public String getQuestion(){
        return question;
    }

    public String[] getOptions(){
        return options;
    }
}
