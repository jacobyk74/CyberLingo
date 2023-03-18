package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        boolean login = LoginHelper.login("test@test.com", "test");
//
//        System.out.println(login);

        List<Lesson> lessons = LessonLoader.loadLessons();

//        for (Lesson l : lessons) {
//            System.out.println(l.getCurrQuestion().getPrompt());
//            System.out.println(l.getCurrLecture().getLectureText());
//            l.nextQuestion();
//            System.out.println(l.getCurrQuestion().getPrompt());
//            l.nextQuestion();
//            MultipleAnswerQuestion ma = (MultipleAnswerQuestion) l.getCurrQuestion();
//            List<String> possibleAnswers = ma.getPossibleAnswers();
//            for (String a : possibleAnswers) {
//                System.out.println(a);
//            }
//            List<String> correctAnswers = ma.getCorrectAnswers();
//            for (String a :  correctAnswers) {
//                System.out.println(a);
//            }
//        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to CyberLingo!");
        System.out.println("Please login by entering your username and password.");
        while (true) {
            System.out.println("Username: ");
            String username = scanner.next();
            System.out.println("Password: ");
            String password = scanner.next();
            if (LoginHelper.login(username, password)) {
                System.out.println("Successfully logged in.");
                break;
            }
            System.out.println("Incorrect username or password, try again.");
        }

        System.out.println("Here are the list of lessons available to complete:");
        for (int i = 0; i < lessons.size(); i++) {
            System.out.println((i+1) + ": " + lessons.get(i).getLessonTitle());
        }
        System.out.println("Please enter the number of the lesson you would like to start: ");
        int index = scanner.nextInt() - 1;
        Lesson currentLesson = lessons.get(index);

        do {
            Lecture lec = currentLesson.getCurrLecture();
            System.out.println(lec.getTopic());
            System.out.println(lec.getLectureText());
        } while (currentLesson.nextLecture());
        
        do {
            Question q = currentLesson.getCurrQuestion();
            System.out.println(q.getPrompt());
            List<String> answers = q.getPossibleAnswers();
            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i+1) + ": " + answers.get(i));
            }
            List<String> givenAnswers = new ArrayList<>();
            if (q instanceof MultipleAnswerQuestion) {
                while (true) {
                    System.out.println("This question can have multiple answers. Enter the number of your next answer here, or enter 0 to stop: ");
                    int choice = scanner.nextInt();
                    if (choice > answers.size()) {
                        System.out.println("Invalid choice.");
                        continue;
                    }
                    if (choice == 0) {
                        break;
                    }
                    if (givenAnswers.contains(answers.get(choice-1))) {
                        System.out.println("You have already picked this choice, choose another.");
                        continue;
                    }
                    givenAnswers.add(answers.get(choice-1));
                }
            }
            else if (q instanceof MultipleChoiceQuestion){
                while (true) {
                    System.out.println("Enter the number of the correct answer: ");
                    int choice = scanner.nextInt();
                    if (choice > answers.size() || choice < 1) {
                        System.out.println("Invalid choice.");
                    }
                    else {
                        givenAnswers.add(answers.get(choice-1));
                        break;
                    }
                }
            }
            else {
                System.out.println("Enter the correct answer: ");
                String given = scanner.next();
                scanner.nextLine();
                givenAnswers.add(given);
            }

            if (q.checkIfCorrect(givenAnswers)) {
                System.out.println("Your answer was correct!");
            }
            else {
                System.out.println("Your answer was incorrect.");
            }
        } while (currentLesson.nextQuestion());

        System.out.println();
        System.out.println("Thanks for joining me for this demo!");

    }
}
