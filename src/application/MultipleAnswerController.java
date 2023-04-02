package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MultipleAnswerController implements Initializable {

    @FXML
    private CheckBox answerBox0, answerBox1, answerBox2, answerBox3;
    @FXML
    private Text titleText, questionPromptText;

    private String username;
    private Lesson lesson;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
    }

    public void back(ActionEvent event) {
        // load back to main menu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainMenuController mainMenuController = loader.getController();
        // now call method to display the user email in next scene (if needed)
        mainMenuController.displayUsername(this.username);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void submit(ActionEvent event) throws IOException {

        // after checking answers and notifying whether answers were correct or not
        FXMLLoader loader;
        Parent root;
        if (!lesson.nextQuestion()) { // load end of lesson screen when out of questions
            loader = new FXMLLoader(getClass().getResource("end-of-lesson.fxml"));
            root = loader.load();
            EndLessonController endLessonController = loader.getController();
            // TODO call things for loading into end lesson screen here
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
        MultipleAnswerQuestion question = (MultipleAnswerQuestion) lesson.getCurrQuestion();
        questionPromptText.setText(question.getPrompt());
        List<String> possibleAnswers = question.getPossibleAnswers();

        try {
            answerBox0.setText(possibleAnswers.get(0));
            answerBox1.setText(possibleAnswers.get(1));
            answerBox2.setText(possibleAnswers.get(2));
            answerBox3.setText(possibleAnswers.get(3));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

}
