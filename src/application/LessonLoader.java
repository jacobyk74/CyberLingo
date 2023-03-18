package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LessonLoader {

    public static List<Lesson> loadLessons() {
        List<Lesson> lessons = new ArrayList<>();

        File[] lessonFiles = new File("data").listFiles();

        int id = 0;
        for (File file : lessonFiles) {
            if (file.isDirectory()) break;

            try (Scanner scanner = new Scanner(file)) {
                List<Lecture> lectures = new ArrayList<>();
                List<Question> questions = new ArrayList<>();
                String lessonTitle = scanner.nextLine();


                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    processLine(line, lectures, questions);
                }
                lessons.add(new Lesson(questions, lectures, 0, ++id, lessonTitle)); // 0 is temp for possible points

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return lessons;
    }

    private static void processLine(String line, List<Lecture> lectures, List<Question> questions) {

        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");

            if (!rowScanner.hasNext()) {
                return;
            }
            if (rowScanner.next().equals("L")) {
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
                try {
                    String prompt = rowScanner.next();
                    String answer1 = rowScanner.next();
                    String answer2 = rowScanner.next();
                    String answer3 = rowScanner.next();
                    String answer4 = rowScanner.next();
                    String correctAnswer = rowScanner.next();
                    String imageFilePath = rowScanner.next();
                    List<String> answers = new ArrayList<String>(List.of(answer1, answer2, answer3, answer4));
                    return new MultipleChoiceQuestion(prompt, imageFilePath, answers, correctAnswer);
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                    break;
                }
            case "MA":
                try {
                    String prompt = rowScanner.next();
                    String answer1 = rowScanner.next();
                    String answer2 = rowScanner.next();
                    String answer3 = rowScanner.next();
                    String answer4 = rowScanner.next();
                    List<String> answers = new ArrayList<String>(List.of(answer1, answer2, answer3, answer4));
                    String imageFilePath = rowScanner.next();

                    List<String> correctAnswers = new ArrayList<>();
                    while (rowScanner.hasNext()) { // so there can be variable nums of correct answers
                        correctAnswers.add(rowScanner.next());
                    }

                    return new MultipleAnswerQuestion(prompt, imageFilePath, answers, correctAnswers);
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                    break;
                }
            case "T":
                try {
                    String prompt = rowScanner.next();
                    String correctAnswer = rowScanner.next();
                    String imageFilePath = rowScanner.next();
                    return new TextQuestion(prompt, imageFilePath, correctAnswer);
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                    break;
                }
        }

        return null;
    }
}
