import java.util.List;

public abstract class Question {

    private String prompt;
    private boolean isCorrect;
    private String imageFilePath;

    public abstract boolean checkIfCorrect(String answer);
    public abstract List<String> getPossibleAnswers();

    public void setPrompt(String prompt) {
        //TODO
        this.prompt = prompt;
    }

    public boolean getIsCorrect() {
        //TODO
        return this.isCorrect;
    }

    public String getPrompt() {
        //TODO
        return this.prompt;
    }

    public void setImageFilePath(String filePath) {
        this.imageFilePath = filePath;
    }

    public String getImageFilePath() {
        return this.imageFilePath;
    }

}
