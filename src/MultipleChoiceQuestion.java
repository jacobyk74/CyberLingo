import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {

    private String correctAnswer;
    private List<String> possibleAnswers = new ArrayList<String>();

    public MultipleChoiceQuestion(String prompt, String imageFilePath, List<String> possibleAnswers, String correctAnswer) {
        //TODO
        this.setPrompt(prompt);
        this.setImageFilePath(imageFilePath);
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkIfCorrect(String answer) {
        //TODO
        return (this.correctAnswer.equals(answer));
    }

    @Override
    public List<String> getPossibleAnswers() {
        //TODO
        return this.possibleAnswers;
    }

    public void setCorrectAnswer(String answer) {
        //TODO, may not be necessary
        this.correctAnswer = answer;
    }

    public String getCorrectAnswer() {
        //TODO
        return this.correctAnswer;
    }
}
