package application;

import java.util.ArrayList;
import java.util.List;

public class Lesson {

    private List<Question> questions = new ArrayList<Question>();
    private List<Lecture> lectures = new ArrayList<Lecture>(); // not sure if this is gonna work
    private String lessonTitle;
    private final int possiblePoints;
    private int currQuestionIndex;
    private int currLectureIndex;
    private int currScore;
    private final int id;

    public Lesson(List<Question> questions, List<Lecture> lectures, int possiblePoints, int id, String lessonTitle) {
        //TODO
        this.questions = questions;
        this.lectures = lectures;
        this.possiblePoints = possiblePoints;
        this.id = id;
        this.lessonTitle = lessonTitle;

        this.currQuestionIndex = 0;
        this.currLectureIndex = 0;
        this.currScore = 0;
    }

    public Question getCurrQuestion() {
        //TODO
        return this.questions.get(currQuestionIndex);
    }

    public Lecture getCurrLecture() {
        // TODO
        return this.lectures.get(currLectureIndex);
    }

    public int getCurrScore() {
        //TODO
        return this.currScore;
    }

    public int getPossiblePoints() {
        //TODO
        return this.possiblePoints;
    }

    public int getCurrQuestionIndex() {
        //TODO
        return this.currQuestionIndex;
    }

    public int getId() {
        // TODO
        return this.id;
    }

    public String getLessonTitle() {
        return this.lessonTitle;
    }

    public boolean nextQuestion() {
        //TODO
        if (questions.size() == currQuestionIndex + 1) {
            return false;
        }
        this.currQuestionIndex++;
        return true;
    }

    public boolean nextLecture() {
        // TODO
        // need to figure out later how to know when to show the next lecture
        if (lectures.size() == currLectureIndex + 1) {
            return false;
        }
        this.currLectureIndex++;
        return true;
    }

    public void incrementScore() {
        //TODO
        this.currScore++;
    }

}
