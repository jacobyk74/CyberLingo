package application;

import com.mysql.cj.log.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    @FXML
    private TextField emailField, usernameField;
    @FXML
    private PasswordField passwordField, confirmPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void createAccount(ActionEvent event) {
        // check for empty fields
        if (emailField.getText() == null || emailField.getText().trim().isEmpty()) {
            raiseAlertBox("The email field is empty.");
            return;
        }
        else if (usernameField.getText() == null || usernameField.getText().trim().isEmpty()) {
            raiseAlertBox("The username field is empty.");
            return;
        }
        else if (passwordField.getText() == null || passwordField.getText().trim().isEmpty()) {
            raiseAlertBox("The password field is empty.");
            return;
        }
        else if (confirmPasswordField.getText() == null || confirmPasswordField.getText().trim().isEmpty()) {
            raiseAlertBox("The confirm password field is empty.");
            return;
        }

        // check if password and confirm password match
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            raiseAlertBox("Password fields do not match.");
            return;
        }

        // do account creation
        if (LoginHelper.createNewUser(emailField.getText(), passwordField.getText(), usernameField.getText())) {
            // successfully created account, back to login screen

            // first raise special alert box
            Alert successMessage = new Alert(Alert.AlertType.INFORMATION);
            successMessage.setTitle("Success!");
            successMessage.setHeaderText(null);
            successMessage.setContentText("Your account has been successfully created.\nYou will now be returned to the login screen.");

            // once they close alert box, back to login screen
            successMessage.setOnCloseRequest(closeEvent -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login-screen.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            });

            successMessage.show();
        }
        else {
            // account creation failed
            raiseAlertBox("Account creation failed.\nEmail or Username is already in use.");
        }
    }

    private void raiseAlertBox(String alertText) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account creation failed");
        alert.setHeaderText(null);
        alert.setContentText(alertText);

        alert.show();
    }
}
