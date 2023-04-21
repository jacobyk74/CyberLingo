package application;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {

    private String correctAnswer;
    private List<String> possibleAnswers = new ArrayList<String>();

    public MultipleChoiceQuestion(String prompt, String imageFilePath, List<String> possibleAnswers, String correctAnswer) {
        this.setPrompt(prompt);
        this.setImageFilePath(imageFilePath);
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkIfCorrect(List<String> answers) {
        return this.correctAnswer.toLowerCase().equals(answers.get(0).toLowerCase());
    }

    @Override
    public List<String> getPossibleAnswers() {
        return this.possibleAnswers;
    }

    public void setCorrectAnswer(String answer) {
        this.correctAnswer = answer;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }
}
