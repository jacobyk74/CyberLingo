package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LectureController implements Initializable {

    @FXML
    private Text titleText, lectureTitleText, lectureText;
    @FXML
    private ImageView lectureImage;

    private String username;
    private Lesson lesson;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void loadLectureInfo(Lesson currentLesson, String username) {
        this.username = username;
        this.lesson = currentLesson;
        titleText.setText(currentLesson.getLessonTitle());

        Lecture lecture = currentLesson.getCurrLecture();
        lectureTitleText.setText(lecture.getTopic());
        lectureText.setText(lecture.getLectureText());

        try {
            lectureImage.setImage(new Image(getClass().getResourceAsStream(lecture.getImageFilePath())));
        } catch (Exception ignored) {
            System.out.println("file path is: " + lecture.getImageFilePath());
            ignored.printStackTrace();
        }
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

    public void next(ActionEvent event) throws IOException {
        // TODO

        // check if there are more lectures first
        if (lesson.nextLecture()) {
            FXMLLoader loader;
            Parent root;
            loader = new FXMLLoader(getClass().getResource("lecture.fxml"));
            root = loader.load();
            LectureController lectureController = loader.getController();
            lectureController.loadLectureInfo(lesson, username);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            return;
        }

        // if no more lectures, start loading questions
        Question question = lesson.getCurrQuestion();
        FXMLLoader loader;
        Parent root;
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

}
