package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MultipleChoiceController implements Initializable {

    @FXML
    private RadioButton answerButton0, answerButton1, answerButton2, answerButton3;

    @FXML
    private Button backButton, submitButton;

    @FXML
    private ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    private Text titleText, questionPromptText;

    private Lesson lesson;
    private String username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
    }

    public void back() {
        // TODO
    }

    public void submit(ActionEvent event) throws IOException {
        // TODO
        // should show a pop-up that says whether they got it right or wrong
        // if wrong, should say correct answer and why

        // after checking answers and notifying whether answers were correct or not
        FXMLLoader loader;
        Parent root;
        if (!lesson.nextQuestion()) { // load end of lesson screen when out of questions
            loader = new FXMLLoader(getClass().getResource("end-of-lesson.fxml"));
            root = loader.load();
            EndLessonController endLessonController = loader.getController();
            endLessonController.loadEndOfLessonInfo(lesson, username);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            return;
        }
        Question question = lesson.getCurrQuestion();
        if (question instanceof  MultipleChoiceQuestion) {
            loader = new FXMLLoader(getClass().getResource("multiple-choice-question.fxml"));
            root = loader.load();
            MultipleChoiceController multipleChoiceController = loader.getController();
            multipleChoiceController.loadQuestionInfo(this.lesson, this.username);
        }
        else if (question instanceof  MultipleAnswerQuestion) {
            loader = new FXMLLoader(getClass().getResource("multiple-answer-question.fxml"));
            root = loader.load();
            MultipleAnswerController multipleAnswerController = loader.getController();
            multipleAnswerController.loadQuestionInfo(this.lesson, this.username);
        }
        else { // text question
            loader = new FXMLLoader(getClass().getResource("text-question.fxml"));
            root = loader.load();
            TextQuestionController textQuestionController = loader.getController();
            textQuestionController.loadQuestionInfo(this.lesson, this.username);
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loadQuestionInfo(Lesson currentLesson, String username) {
        // TODO
        this.lesson = currentLesson;
        this.username = username;

        titleText.setText(lesson.getLessonTitle());
        MultipleChoiceQuestion question = (MultipleChoiceQuestion) lesson.getCurrQuestion();
        questionPromptText.setText(question.getPrompt());
        List<String> possibleAnswers = question.getPossibleAnswers();

        try {
            answerButton0.setText(possibleAnswers.get(0));
            answerButton1.setText(possibleAnswers.get(1));
            answerButton2.setText(possibleAnswers.get(2));
            answerButton3.setText(possibleAnswers.get(3));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

}
