package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TextQuestionController implements Initializable {

    @FXML
    private TextField inputTextField;
    @FXML
    private Text titleText, questionPromptText;
    @FXML
    private Button submitButton;
    @FXML
    private VBox mainBox;
    @FXML
    private ImageView imageView;

    private Lesson lesson;
    private String username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        String givenAnswer = inputTextField.getText();

        // prepare final message
        Text finalText = new Text();
        finalText.setTextAlignment(TextAlignment.CENTER);
        VBox.setVgrow(finalText, Priority.ALWAYS);
        mainBox.getChildren().clear();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().add(finalText);

        if (lesson.getCurrQuestion().checkIfCorrect(List.of(givenAnswer))) {
            finalText.setStyle("-fx-fill: #488e3f; -fx-font-size: 50; -fx-font-weight: bold");
            finalText.setText("Correct!");
            lesson.incrementScore();
        } else {
            finalText.setStyle("-fx-fill: red; -fx-font-size: 50; -fx-font-weight: bold");
            finalText.setText("Incorrect");
            String correctAnswer = ((TextQuestion) lesson.getCurrQuestion()).getCorrectAnswer();
            Text finalTextCorrectAnswers = new Text("\nThe correct answer is:\n" + correctAnswer);
            finalTextCorrectAnswers.setStyle("-fx-fill: #488e3f; -fx-font-size: 30; -fx-font-weight: bold");
            finalTextCorrectAnswers.setTextAlignment(TextAlignment.CENTER);
            mainBox.getChildren().add(finalTextCorrectAnswers);
            mainBox.setPadding(new Insets(100));
            // TODO buttons being weird with this VBox when text goes to the edges, need to fix
        }

        // change submit button to next button that goes to next question
        submitButton.setText("Next");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loadNext(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void loadNext(ActionEvent event) throws IOException {
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
        TextQuestion question = (TextQuestion) lesson.getCurrQuestion();
        questionPromptText.setText(question.getPrompt());

        try {
            imageView.setImage(new Image(getClass().getResourceAsStream(question.getImageFilePath())));
        } catch (Exception ignored) {

        }
    }
}
