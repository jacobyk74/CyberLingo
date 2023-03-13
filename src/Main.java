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
            l.nextQuestion();
            System.out.println(l.getCurrQuestion().getPrompt());
            l.nextQuestion();
            MultipleAnswerQuestion ma = (MultipleAnswerQuestion) l.getCurrQuestion();
            List<String> possibleAnswers = ma.getPossibleAnswers();
            for (String a : possibleAnswers) {
                System.out.println(a);
            }
            List<String> correctAnswers = ma.getCorrectAnswers();
            for (String a :  correctAnswers) {
                System.out.println(a);
            }
        }

    }
}
