package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MultipleAnswerController implements Initializable {

    @FXML
    private CheckBox answerBox0, answerBox1, answerBox2, answerBox3;
    @FXML
    private Text titleText, questionPromptText;
    @FXML
    private BorderPane borderPane;
    @FXML
    private GridPane rightGridPane;
    @FXML
    private Button submitButton;

    private String username;
    private Lesson lesson;
    private List<CheckBox> boxes;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
        boxes = new ArrayList<CheckBox>(Arrays.asList(answerBox0, answerBox1, answerBox2, answerBox3));
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
        // checking for correct
        List<String> givenAnswers = new ArrayList<String>();
        for (CheckBox box : boxes) {
            if (box.isSelected()) {
                givenAnswers.add(box.getText());
            }
        }
        // prepare final message
        VBox newVBox = new VBox();
        Text finalText = new Text();

        finalText.setTextAlignment(TextAlignment.CENTER);
        newVBox.getChildren().add(finalText);
        VBox.setVgrow(finalText, Priority.ALWAYS);
        newVBox.setAlignment(Pos.CENTER);

        if (lesson.getCurrQuestion().checkIfCorrect(givenAnswers)) {
            finalText.setStyle("-fx-fill: #488e3f; -fx-font-size: 50; -fx-font-weight: bold");
            finalText.setText("Correct!");
            lesson.incrementScore();
        } else {
            finalText.setStyle("-fx-fill: red; -fx-font-size: 50; -fx-font-weight: bold");
            finalText.setText("Incorrect");
            String finalTextString = "\nThe correct answers are:";
            List<String> correctAnswers = ((MultipleAnswerQuestion) lesson.getCurrQuestion()).getCorrectAnswers();
            for (String s : correctAnswers) {
                finalTextString += "\n" + s;
            }
            Text finalTextCorrectAnswers = new Text(finalTextString);
            finalTextCorrectAnswers.setStyle("-fx-fill: #488e3f; -fx-font-size: 50; -fx-font-weight: bold");
            finalTextCorrectAnswers.setTextAlignment(TextAlignment.CENTER);
            newVBox.getChildren().add(finalTextCorrectAnswers);
            // TODO buttons being weird with this VBox, need to fix
        }
        borderPane.setCenter(newVBox);

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


        // after checking answers and notifying whether answers were correct or not
//        FXMLLoader loader;
//        Parent root;
//        if (!lesson.nextQuestion()) { // load end of lesson screen when out of questions
//            loader = new FXMLLoader(getClass().getResource("end-of-lesson.fxml"));
//            root = loader.load();
//            EndLessonController endLessonController = loader.getController();
//            endLessonController.loadEndOfLessonInfo(lesson, username);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//
//            return;
//        }
//        Question question = lesson.getCurrQuestion();
//        if (question instanceof  MultipleChoiceQuestion) {
//            loader = new FXMLLoader(getClass().getResource("multiple-choice-question.fxml"));
//            root = loader.load();
//            MultipleChoiceController multipleChoiceController = loader.getController();
//            multipleChoiceController.loadQuestionInfo(this.lesson, this.username);
//        }
//        else if (question instanceof  MultipleAnswerQuestion) {
//            loader = new FXMLLoader(getClass().getResource("multiple-answer-question.fxml"));
//            root = loader.load();
//            MultipleAnswerController multipleAnswerController = loader.getController();
//            multipleAnswerController.loadQuestionInfo(this.lesson, this.username);
//        }
//        else { // text question
//            loader = new FXMLLoader(getClass().getResource("text-question.fxml"));
//            root = loader.load();
//            TextQuestionController textQuestionController = loader.getController();
//            textQuestionController.loadQuestionInfo(this.lesson, this.username);
//        }
//
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    private void loadNext(ActionEvent event) throws IOException {
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
