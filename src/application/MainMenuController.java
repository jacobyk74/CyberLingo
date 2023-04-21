package application;

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
import java.util.*;

public class MainMenuController implements Initializable {

    @FXML
    private ListView<String> lessonList, leaderboardView;
    @FXML
    private Button logoutButton;
    @FXML
    private Label usernameLabel, currentPointsLabel;

//    String[] lessons = {"Lesson 1", "Lesson 2", "Lesson 3"};
    private Lesson currentLesson;
    private String email, username, userScore;

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
                    lectureController.loadLectureInfo(currentLesson, this.email);

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
        HashMap<String, Integer> userScores = LoginHelper.getScores();
        // sort it
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(userScores.entrySet());
        entries.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        // store sorted entries in a list
        List<String> sortedUserScores = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedUserScores.add(entry.getKey() + ": " + entry.getValue() + " points");
        }
        // initialize ListView with the sorted user scores
        leaderboardView.getItems().addAll(sortedUserScores);
    }

    public void displayUsername(String email) {
        this.email = email;
//        usernameLabel.setText("Hello, "+ email + "!");

        List<String> temp = LoginHelper.getSpecificScore(email);
        this.username = temp.get(0);
        this.userScore = temp.get(1);

        usernameLabel.setText("Hello, "+ this.username + "!");
        currentPointsLabel.setText(this.userScore);
        currentPointsLabel.setStyle("-fx-font-weight: bold");
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
