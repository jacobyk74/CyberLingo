package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class MultipleChoiceController implements Initializable {

    @FXML
    private RadioButton answerButton0;
    private RadioButton answerButton1;
    private RadioButton answerButton2;
    private RadioButton answerButton3;

    @FXML
    private Button backButton;
    private Button submitButton;

    @FXML
    private ToggleGroup toggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
    }

    public void back() {
        // TODO
    }

    public void submit() {
        // TODO
        // should show a pop-up that says whether they got it right or wrong
        // if wrong, should say correct answer and why
    }

}
