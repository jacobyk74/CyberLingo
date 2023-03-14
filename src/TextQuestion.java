import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TextQuestion extends Question {

    private String correctAnswer;

    public TextQuestion(String prompt, String imageFilePath, String correctAnswer) {
        //TODO
        this.setPrompt(prompt);
        this.setImageFilePath(imageFilePath);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkIfCorrect(List<String> answers) {
        //TODO
        return this.correctAnswer.toLowerCase().equals(answers.get(0).toLowerCase());
    }

    @Override
    public List<String> getPossibleAnswers() {
        //TODO
        return new ArrayList<String>(); // will need to check for empty list when calling this method
    }

    public void setCorrectAnswer(String correctAnswer) {
        //TODO, may not be necessary
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        //TODO
        return this.correctAnswer;
    }
}
