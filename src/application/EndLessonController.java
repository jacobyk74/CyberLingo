package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndLessonController implements Initializable {

    @FXML
    private Text scoreText, titleText;
    @FXML
    private Button backButton;

    private Lesson lesson;
    private String username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize scoreText
    }

    public void loadEndOfLessonInfo(Lesson lesson, String username) {
        int score = lesson.getCurrScore();
        int possibleScore = lesson.getPossiblePoints();
        titleText.setText(lesson.getLessonTitle());
        scoreText.setText(score + "/" + possibleScore);

        // TODO after normal loading, update score in database
    }

    public void backToMainMenu(ActionEvent event) {
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
}
