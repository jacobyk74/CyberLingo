import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {

    private String correctAnswer;
    private List<String> possibleAnswers = new ArrayList<String>();

    public MultipleChoiceQuestion() {
        //TODO
    }

    @Override
    public boolean checkIfCorrect(String answer) {
        //TODO
        return false;
    }

    @Override
    public List<String> getPossibleAnswers() {
        //TODO
        return null;
    }

    public void setCorrectAnswer() {
        //TODO, may not be necessary
    }

    public String getCorrectAnswer() {
        //TODO
        return "";
    }
}
