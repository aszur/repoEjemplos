package es.tta.example.model;

/**
 * Created by asier on 13/01/17.
 */

public class User {
    private int id;
    private String user;
    private int lessonNumber;
    private String lessonTitle;
    private int nextText;
    private int nextExercise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public int getNextText() {
        return nextText;
    }

    public void setNextText(int nextText) {
        this.nextText = nextText;
    }

    public int getNextExercise() {
        return nextExercise;
    }

    public void setNextExercise(int nextExercise) {
        this.nextExercise = nextExercise;
    }
}
