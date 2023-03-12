import java.util.List;

public class Main {

    public static void main(String[] args) {

//        boolean login = LoginHelper.login("test@test.com", "test");
//
//        System.out.println(login);

        List<Lesson> lessons = LessonLoader.loadLessons();

        for (Lesson l : lessons) {
            System.out.println(l.getCurrQuestion().getPrompt());
            System.out.println(l.getCurrLecture().getLectureText());
        }

    }
}
