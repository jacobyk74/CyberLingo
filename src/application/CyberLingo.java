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
//        FXMLLoader fxmlLoader = new FXMLLoader(CyberLingo.class.getResource("login-screen.fxml"));
//        Scene loginScreen = new Scene(fxmlLoader.load());
        Parent root = FXMLLoader.load(getClass().getResource("login-screen.fxml"));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add("main-menu.css");
        stage.setTitle("CyberLingo: The Cyber Security Learning Application");
        stage.setScene(scene);

        Image icon = new Image(getClass().getResourceAsStream("CyberLingo-3-removebg-preview.png"));
        stage.getIcons().add(icon);

        stage.show();
//        MainMenuController.loadLessonList();
//        LoginHelper.updateScore("test@test.com", 5);
//        HashMap<String, Integer> scores = LoginHelper.getScores();
//        for (String key : scores.keySet()) {
//            System.out.println("Username: " + key + "  Score: " + scores.get(key));
//        }
    }
}
