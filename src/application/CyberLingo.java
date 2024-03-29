package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class CyberLingo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login-screen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("CyberLingo: The Cyber Security Learning Application");
        stage.setScene(scene);

        Image icon = new Image(getClass().getResourceAsStream("CyberLingo-3-removebg-preview.png"));
        stage.getIcons().add(icon);

        stage.show();
    }
}
