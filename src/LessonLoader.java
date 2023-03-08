import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LessonLoader {

    public static List<Lesson> loadLessons() {
        List<Lesson> lessons = new ArrayList<>();

        File[] lessonFiles = new File("../data").listFiles();

        for (File file : lessonFiles) {
            if (file.isDirectory()) break;

            try (Scanner scanner = new Scanner(file)) {
                List<Lecture> lectures = new ArrayList<>();
                List<Question> questions = new ArrayList<>();

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    processLine(line, lectures, questions);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    private static void processLine(String line, List<Lecture> lectures, List<Question> questions) {

        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");

            if (!rowScanner.hasNext()) {
                return;
            }
            if (rowScanner.next().equals("Lecture")) {
                String lectureText = rowScanner.next();
                String topic = rowScanner.next();
                String imageFilePath = rowScanner.next();
                lectures.add(new Lecture(lectureText, topic, imageFilePath));
                return;
            }
            questions.add(processQuestion(rowScanner));

        }

    }

    private static Question processQuestion(Scanner rowScanner) {

        String questionType = rowScanner.next();

        switch (questionType) {
            case "MC":
                // TODO may need try-catch blocks in these
                // return new MultipleChoiceQuestion
                break;
            case "MA":
                // TODO
                // return new MultipleAnswerQuestion
                break;
            case "T":
                // TODO
                // return new TextQuestion
        }

        return null;
    }
}
