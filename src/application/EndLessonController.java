package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class EndLessonController implements Initializable {

    @FXML
    private Text scoreText;

    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize scoreText
    }

    public void backToMainMenu() {
        // TODO
    }
}
