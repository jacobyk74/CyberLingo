package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private ListView<String> lessonList;

    String[] lessons = {"Lesson 1", "Lesson 2", "Lesson 3"};
    String currentLesson;

//    public static void loadLessonList() {
//        ObservableList<String> exampleLessons = FXCollections.observableArrayList(
//                "Lesson 1",
//                "Lesson 2",
//                "Lesson 3",
//                "Lesson 4");
//        lessonList.setItems(exampleLessons);
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // use LessonLoader to load all lessons and get their titles
        // load titles into lessonList
        lessonList.getItems().addAll(lessons);

        // to detect what item is selected
        lessonList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentLesson = lessonList.getSelectionModel().getSelectedItem();
                // here it could change to the lesson screen once selected
            }

        });
    }
}
