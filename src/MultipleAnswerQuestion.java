import java.util.ArrayList;
import java.util.List;

public class MultipleAnswerQuestion extends Question {

    private List<String> correctAnswers = new ArrayList<String>();
    private List<String> possibleAnswers = new ArrayList<String>();

    public MultipleAnswerQuestion(String prompt, String imageFilePath, List<String> possibleAnswers, List<String> correctAnswers) {
        //TODO
        this.setPrompt(prompt);
        this.setImageFilePath(imageFilePath);
        this.possibleAnswers = possibleAnswers;
        this.correctAnswers = correctAnswers;
    }

    @Override
    public boolean checkIfCorrect(List<String> answers) {
        //TODO

        // put all possible answers in lowercase for checking later
        List<String> correctAnswersCopy = new ArrayList<>();
        for (String correctAnswer : this.correctAnswers) {
            correctAnswersCopy.add(correctAnswer.toLowerCase());
        }

        for (String a : answers) {
            if (!correctAnswersCopy.contains(a.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getPossibleAnswers() {
        //TODO
        return this.possibleAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        //TODO, may not be necessary
        this.correctAnswers = correctAnswers;
    }

    public List<String> getCorrectAnswers() {
        //TODO
        return this.correctAnswers;
    }
}
