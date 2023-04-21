package application;

import java.util.ArrayList;
import java.util.List;

public class TextQuestion extends Question {

    private String correctAnswer;

    public TextQuestion(String prompt, String imageFilePath, String correctAnswer) {
        this.setPrompt(prompt);
        this.setImageFilePath(imageFilePath);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkIfCorrect(List<String> answers) {
        return this.correctAnswer.toLowerCase().equals(answers.get(0).toLowerCase());
    }

    @Override
    public List<String> getPossibleAnswers() {
        return new ArrayList<String>(); // will need to check for empty list when calling this method
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }
}
