import java.util.List;

public abstract class Question {

    private String prompt;
    private boolean isCorrect;
    private String imageFilePath;

    public abstract boolean checkIfCorrect(String answer);
    public abstract List<String> getPossibleAnswers();

    public void setPrompt(String prompt) {
        //TODO
    }

    public boolean getIsCorrect() {
        //TODO
        return false;
    }

    public String getPrompt() {
        //TODO
        return "";
    }

}
