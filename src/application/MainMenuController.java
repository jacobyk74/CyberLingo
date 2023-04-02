package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private ListView<String> lessonList;
    @FXML
    private Button logoutButton;
    @FXML
    private Label usernameLabel;

//    String[] lessons = {"Lesson 1", "Lesson 2", "Lesson 3"};
    Lesson currentLesson;
    String username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // use LessonLoader to load all lessons and get their titles\
        List<Lesson> lessonObjects = LessonLoader.loadLessons();
        ArrayList<String> lessons = new ArrayList<>();
        for (Lesson lesson : lessonObjects) {
            lessons.add(lesson.getLessonTitle());
        }

        // load titles into lessonList
        lessonList.getItems().addAll(lessons);

        // to detect what item is selected
        lessonList.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() >= 2) {
                currentLesson = lessonObjects.get(lessonList.getSelectionModel().getSelectedIndex());
//                System.out.println(currentLesson + " has been double-clicked");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("lecture.fxml"));
                    Parent root = loader.load();
                    LectureController lectureController = loader.getController();
                    // here will call a method to initialize with chosen lesson's content in next scene
                    // by passing lesson object
                    lectureController.loadLectureInfo(currentLesson, this.username);

                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // initialize the leaderboards and rewards based on user email
    }

    public void displayUsername(String username) {
        this.username = username;
        usernameLabel.setText("Hello, "+ username + "!");
    }

    public void logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-screen.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
}
