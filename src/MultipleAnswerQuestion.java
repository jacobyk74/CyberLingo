import java.util.ArrayList;
import java.util.List;

public class MultipleAnswerQuestion extends Question {

    private List<String> correctAnswers = new ArrayList<String>();
    private List<String> possibleAnswers = new ArrayList<String>();

    public MultipleAnswerQuestion() {
        //TODO
    }

    @Override
    public boolean checkIfCorrect(String answer) {
        //TODO
        // this needs to take an array, may need to make superclass method also take array
        return false;
    }

    @Override
    public List<String> getPossibleAnswers() {
        //TODO
        return null;
    }

    public void setCorrectAnswers() {
        //TODO, may not be necessary
    }

    public List<String> getCorrectAnswers() {
        //TODO
        return null;
    }
}
