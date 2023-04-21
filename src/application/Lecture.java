package application;

public class Lecture {

    private final String lectureText;
    private final String topic;
    private final String imageFilePath;

    public Lecture(String lectureText, String topic, String imageFilePath) {
        this.lectureText = lectureText;
        this.topic = topic;
        this.imageFilePath = imageFilePath;
    }

    public String getLectureText() {
        return lectureText;
    }

    public String getTopic() {
        return topic;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }
}
