package application;

import java.util.List;

public abstract class Question {

    private String prompt;
    private boolean isCorrect;
    private String imageFilePath;

    public abstract boolean checkIfCorrect(List<String> answers);
    public abstract List<String> getPossibleAnswers(); // will need to check for empty list when calling this method

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public boolean getIsCorrect() {
        return this.isCorrect;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public void setImageFilePath(String filePath) {
        this.imageFilePath = filePath;
    }

    public String getImageFilePath() {
        return this.imageFilePath;
    }

}
