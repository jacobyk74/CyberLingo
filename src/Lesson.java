import java.util.ArrayList;
import java.util.List;

public class Lesson {

    private List<Question> questions = new ArrayList<Question>();
    private List<Lecture> lectures = new ArrayList<Lecture>(); // not sure if this is gonna work
    private int possiblePoints;
    private int currQuestionIndex;
    private int currScore;
    private int id;

    public Lesson() {
        //TODO
    }

    public Question getCurrQuestion() {
        //TODO
        return null;
    }

    public int getCurrScore() {
        //TODO
        return -1;
    }

    public int getPossiblePoints() {
        //TODO
        return -1;
    }

    public int getCurrQuestionIndex() {
        //TODO
        return -1;
    }

    public void nextQuestion() {
        //TODO
    }

    public void incrementScore() {
        //TODO
    }

}
