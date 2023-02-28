import java.util.List;

public class TextQuestion extends Question {

    private String correctAnswer;

    public TextQuestion() {
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
